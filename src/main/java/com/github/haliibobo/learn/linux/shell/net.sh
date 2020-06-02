#!/bin/bash
netstat -antp|grep 8080
netstat -tunlp|grep 8080
https://www.cnblogs.com/solohac/p/4154180.html
cat /proc/sys/net/ipv4/ip_local_port_range
https://blog.csdn.net/cc_qjy/article/details/78582337

netstat -nlpt |grep 8080
netstat -nlpt |grep 8080 |  awk '{print $7}' | awk -F '/' '{print $1}'

PORT1=`sed '/port/!d;s/.*=//' config/system.conf | head -n 1 | tr -d '"\t\r\n '`

 `netstat -nlpt |grep 8080 | sed -n '/[0-9]/p;s/.*//'`
 #!/bin/bash
zhanyong=`netstat -nlpt |grep 8080 |  awk '{print $7}' | awk -F '/' '{print $1}' | tr -d '"\t\r\n '`
act=`jps -ml | grep "com.halibobo.app.Application" | awk {'print $1'} | tr -d '"\t\r\n '`
if [[ $zhanyong -eq $act ]];then
    echo "good"
fi
while :
do
  tmp=`cat /export/Logs/mixer/stdout.log | grep "占用"`
  if [ ! -z $tmp ]; then
     echo "failed"
    break
  fi
  sleep 1
  echo
done