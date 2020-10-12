#!/usr/bin/env bash

SERVER_PORT=`sed '/port/!d;s/.*=//' config.conf | head -n 1 | tr -d '"\t\r\n '`
echo "SERVER_PORT:${SERVER_PORT}"
pid=`netstat -anlp | grep ${SERVER_PORT} | grep LISTEN | awk 'NR==1 {print$7}' | awk -F '/' '{print$1}'`
if [ -n "$pid" ]; then
 echo "pid:${pid}"
 kill -9 ${pid}
 exit 0
fi

echo 'no  to stop!'