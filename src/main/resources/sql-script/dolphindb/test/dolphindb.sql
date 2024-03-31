-- dolphindb 建内存表
t = table(10000:0,`cstring`cint`ctimestamp`cdouble,[STRING,INT,TIMESTAMP,DOUBLE])
share t as sharedTable

-- dolphindb 建内存表
-- 主键由sym和id字段组成
-- 重复添加只会更新主键的记录
t=keyedTable(`sym`id,1:0,`sym`id`val,(SYMBOL,INT,INT))
share t as sharedTable1
-- select * from sharedTable1

-- dolphindb 建分布式表
dbPath = 'dfs://testDatabase3'
tbName = 'tb1'
if (existsDatabase(dbPath)) {
    dropDatabase(dbPath)
}
db = database(dbPath, RANGE, 2024.01.01..2024.12.31)
t=table(1:0,`sym`id`ctimestamp`val,(SYMBOL,INT,TIMESTAMP,INT))
db.createPartitionedTable(t, tbName, 'ctimestamp')
-- select * from loadTable("dfs://testDatabase3","tb1")

-- dolphindb 建分布式表

dbPath = 'dfs://testDatabase10'
tbName = 'tb1'
if (existsDatabase(dbPath)) {
    dropDatabase(dbPath)
}
db = database(dbPath, RANGE, 2024.01.01..2024.12.31,engine="TSDB")
t=keyedTable(`ctimestamp`sym,1:0,`sym`id`ctimestamp`val,(SYMBOL,INT,TIMESTAMP,INT))
db.createPartitionedTable(t, tbName, 'ctimestamp',keepDuplicates=LAST,sortColumns=`sym`ctimestamp)
-- select * from loadTable("dfs://testDatabase10","tb1")

-- 数据库按照 VALUE-HASH-HASH 的组合进行三级分区。
t = table(timestamp(1..10)  as date,string(1..10) as sym)
db1=database("",HASH,[DATETIME,10])
db2=database("",HASH,[STRING,5])
if(existsDatabase("dfs://demohash")){
    dropDatabase("dfs://demohash")
}
db =database("dfs://demohash",COMPO,[db2,db1])
pt = db.createPartitionedTable(t,`pt,`sym`date)
-- select * from loadTable("dfs://demohash","pt")