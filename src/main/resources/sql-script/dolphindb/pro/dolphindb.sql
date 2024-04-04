login("admin","123456")
dbPath="dfs://stock"
yearRange=date(1990.01M + 12*0..99)
if(existsDatabase(dbPath)){
    dropDatabase(dbPath)
}
columns1=`code`time`open`high`low`close`volume
type1=`SYMBOL`TIMESTAMP`DOUBLE`DOUBLE`DOUBLE`DOUBLE`DOUBLE
db=database(dbPath,RANGE,yearRange,engine="TSDB")
hushen_daily_line=db.createPartitionedTable(keyedTable(`code`time,100000000:0,columns1,type1),`daily_line,`time,keepDuplicates=LAST,sortColumns=`code`time)