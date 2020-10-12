
--1. 用户登录表  login_info
--uid,login_time,logout_time
--窗口函数 sum() over()
--统计一天最高在线人数和时间

select
max(max_index)
from
(
  select sum(index) over(order by `timestamp`) as max_index --排序后第一行到本行的和
  from
  (
    select uid, unix_timestamp(login_time) as `timestamp`, 1 as index
    from  login_info
    where dt = '2019-01-01'
    union all
    select uid, unix_timestamp(logout_time) as `timestamp`, -1 as index
    from  login_info
    where dt = '2019-01-01'
  )a  --将登录时间和登出时间多列成多行
)b;
--2. 用户开关表
--uid,optime, switch（0/1）
--操作按钮，只能 开关，关开，不能出现连续的开开，关关，找出异常记录
---3.一个月用户登录表
--uid,logintime,logout,dt
--找出活跃用户，连续登录10天


-- 按照用户id进行分组，求出每个用户连续登陆的最大天数
select uid, max(ct) as max_ct
from
(
-- 第二步：按照用户id和持续的日期进行分组，求出每个用户所有持续日期的持续天数
  select uid, count(distinct date_) as ct
  from
  (
  -- 第一步：先使用日期函数将日期date_sub’归元’，得到持续的日期都有哪些
    select uid,date_sub(dt,row_number() over (
    --partition by uid order by dt asc
    distribute by uid sort by dt
    )) as date_ from  login_info where dt >= '2020-08-01' and dt <= '2020-08-31'
         ) t1
  group by uid,date_) t2
 group by uid;


