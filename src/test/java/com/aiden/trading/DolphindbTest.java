package com.aiden.trading;

import com.xxdb.DBConnection;
import com.xxdb.SimpleDBConnectionPool;
import com.xxdb.SimpleDBConnectionPoolConfig;
import com.xxdb.data.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DolphindbTest {

    static SimpleDBConnectionPool dbConnectionPool;
    static DBConnection conn;

    static {
        SimpleDBConnectionPoolConfig config = new SimpleDBConnectionPoolConfig();
        config.setHostName("192.168.0.208");
        config.setPort(8031);
        config.setUserId("admin");
        config.setPassword("123456");
        config.setInitialPoolSize(5);
        config.setEnableHighAvailability(false);
        dbConnectionPool = new SimpleDBConnectionPool(config);
        // 从连接池中获取一个连接
        conn = dbConnectionPool.getConnection();
    }

    /**
     * dolphindb 建内存表
     * t = table(10000:0,`cstring`cint`ctimestamp`cdouble,[STRING,INT,TIMESTAMP,DOUBLE])
     * share t as sharedTable
     *
     * @throws IOException
     */
    @Test
    public void testInsert() throws IOException {
        test_save_Insert("sharedTable", "hel2", 2, 22L, 2.2);
    }

    @Test
    public void testBatchInsert() throws IOException {
        test_save_TableInsert("sharedTable", List.of("hel2"), List.of(2), List.of(22L), List.of(2.2));
    }

    @Test
    public void testBasicTableInsert() throws IOException {
        List<String> colNames = List.of("cstring", "cint", "ctimestamp", "cdouble");
        List<Vector> cols = new ArrayList<>();
        Vector cstring = new BasicStringVector(List.of("he22222l2221111"));
        cols.add(cstring);
        Vector cint = new BasicIntVector(List.of(22122222));
        cols.add(cint);
        Vector ctimestamp = new BasicTimestampVector(List.of(1711855160000L));
        cols.add(ctimestamp);
        Vector cdouble = new BasicDoubleVector(List.of(2.2));
        cols.add(cdouble);
        BasicTable table1 = new BasicTable(colNames, cols);
        test_save_table("sharedTable", table1);
    }

    public void test_save_Insert(String memoryTableName, String str, int i, long ts, double dbl) throws IOException {
        conn.run(String.format("insert into %s values('%s',%s,%s,%s)", memoryTableName, str, i, ts, dbl));
    }

    public void test_save_TableInsert(String memoryTableName, List<String> strArray, List<Integer> intArray, List<Long> tsArray, List<Double> dblArray) throws IOException {
        String sql = String.format("tableInsert{%s}", memoryTableName);
        //用数组构造参数
        List<Entity> args = Arrays.asList(new BasicStringVector(strArray), new BasicIntVector(intArray), new BasicTimestampVector(tsArray), new BasicDoubleVector(dblArray));
        conn.run(sql, args);
    }

    /**
     * dolphindb 建内存表
     * 主键由sym和id字段组成
     * t=keyedTable(`sym`id,1:0,`sym`id`val,(SYMBOL,INT,INT))
     * share t as sharedTable1
     * 重复添加只会更新主键的记录
     *
     * @throws IOException
     */
    @Test
    public void testBasicTableInsert2() throws IOException {
        List<String> colNames = List.of("sym", "id", "val");
        List<Vector> cols = new ArrayList<>();
        Vector sym = new BasicSymbolVector(List.of("hel2"));
        cols.add(sym);
        Vector id = new BasicIntVector(List.of(23));
        cols.add(id);
        Vector val = new BasicIntVector(List.of(223));
        cols.add(val);
        BasicTable table1 = new BasicTable(colNames, cols);
        test_save_table("sharedTable1", table1);
    }

    public void test_save_table(String memoryTableName, BasicTable table1) throws IOException {
        List<Entity> args = List.of(table1);
        String sql = String.format("tableInsert{%s}", memoryTableName);
        conn.run(sql, args);
    }

    /**
     * dolphindb 建分布式表
     * dbPath = 'dfs://testDatabase'
     * tbName = 'tb1'
     * <p>
     * if(existsDatabase(dbPath)){dropDatabase(dbPath)}
     * db = database(dbPath,RANGE,2018.01.01..2018.12.31)
     * db.createPartitionedTable(t,tbName,'ctimestamp')
     */
    @Test
    public void testDistributionBasicTableInsert2() throws IOException {
        List<String> colNames = List.of("sym", "id", "ctimestamp", "val");
        List<Vector> cols = new ArrayList<>();
        Vector sym = new BasicSymbolVector(List.of("hel2"));
        cols.add(sym);
        Vector id = new BasicIntVector(List.of(232));
        cols.add(id);
        Vector ctimestamp = new BasicTimestampVector(List.of(1709349560000L));
        cols.add(ctimestamp);
        Vector val = new BasicIntVector(List.of(225));
        cols.add(val);
        BasicTable table1 = new BasicTable(colNames, cols);
        test_save_distribution_table("dfs://testDatabase3", "tb1", table1);
    }

    @Test
    public void testDistributionBasicTableInsert3() throws IOException {
        List<String> colNames = List.of("sym", "id", "ctimestamp", "val");
        List<Vector> cols = new ArrayList<>();
        Vector sym = new BasicSymbolVector(List.of("hel2"));
        cols.add(sym);
        Vector id = new BasicIntVector(List.of(232));
        cols.add(id);
        Vector ctimestamp = new BasicTimestampVector(List.of(1709349560000L));
        cols.add(ctimestamp);
        Vector val = new BasicIntVector(List.of(223));
        cols.add(val);
        BasicTable table1 = new BasicTable(colNames, cols);
        test_save_distribution_table("dfs://testDatabase10", "tb1", table1);
    }

    public void test_save_distribution_table(String dbPath, String tableName, BasicTable table1) throws IOException {
        List<Entity> args = new ArrayList<Entity>(1);
        args.add(table1);
        conn.run(String.format("tableInsert{loadTable('%s','%s')}", dbPath, tableName), args);
    }


}
