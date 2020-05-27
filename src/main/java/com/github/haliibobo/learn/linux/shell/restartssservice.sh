#!/bin/bash
# @Desc 此脚本用于获取一个指定区间且未被占用的随机端口号
# @Author Hellxz <hellxz001@foxmail.com>
PrePort=`awk -F[:,] 'NR==3 {print $2}' /etc/shadowsocks.json`
echo 'start to remove port:'$PrePort' from firewall'
firewall-cmd --zone=public --remove-port=$PrePort/udp --permanent
firewall-cmd --zone=public --remove-port=$PrePort/tcp --permanent
firewall-cmd --reload
echo 'remove port:'$PrePort' from firewall done'
PORT=0
#判断当前端口是否被占用，没被占用返回0，反之1
function Listening {
   TCPListeningnum=`netstat -an | grep ":$1 " | awk '$1 == "tcp" && $NF == "LISTEN" {print $0}' | wc -l`
   UDPListeningnum=`netstat -an | grep ":$1 " | awk '$1 == "udp" && $NF == "0.0.0.0:*" {print $0}' | wc -l`
   (( Listeningnum = TCPListeningnum + UDPListeningnum ))
   if [ $Listeningnum == 0 ]; then
       echo "0"
   else
       echo "1"
   fi
}

#指定区间随机数
function random_range {
   shuf -i $1-$2 -n1
}

#得到随机端口
function get_random_port {
   templ=0
   while [ $PORT == 0 ]; do
       temp1=`random_range $1 $2`
       if [ `Listening $temp1` == 0 ] ; then
              PORT=$temp1
       fi
   done
}
get_random_port 49152 65535;
echo 'start to add port:'$PORT' to firewall'
firewall-cmd --zone=public --add-port=$PORT/udp --permanent
firewall-cmd --zone=public --add-port=$PORT/tcp --permanent
firewall-cmd --reload
echo 'add port:'$PORT' to firewall done'
iptables -L -n
sed -i 's/"server_port":[0-9]\+/"server_port":'"$PORT"'/' /etc/shadowsocks.json
/bin/python /usr/bin/ssserver -c /etc/shadowsocks.json -d restart
echo "resatrt service on port $PORT success"
netstat  -anp |grep $PORT
