day_1=`date -d "-1 day" +%Y-%m-%d`
day_7=`date -d "-7 day" +%Y-%m-%d`
t=`date "+%Y%m%d%H%M"`
set hive.merge.mapfiles = true;
set hive.merge.mapredfiles = true;
set hive.exec.reducers.max = 30;
set hive.merge.size.per.task = 256000000;
set hive.merge.smallfiles.avgsize = 160000000;
set mapred.max.split.size=256000000;
set mapred.min.split.size.per.node=100000000;
set mapred.min.split.size.per.rack=100000000;
set hive.input.format=org.apache.hadoop.hive.ql.io.CombineHiveInputFormat;
set hive.exec.compress.output=true;
set mapred.output.compression.codec= org.apache.hadoop.io.compress.SnappyCodec;
INSERT OVERWRITE TABLE db.tname partition(dt='2020-06-30')
  SELECT id, concat_ws(',',sort_array(collect_set(xxid))) as xxids
  FROM db2.tname2
  WHERE dt = '2020-06-30' AND xxxid = 3 AND xxid is NOT null GROUP BY id;