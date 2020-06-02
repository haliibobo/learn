#!/usr/bin/env bash
ps  --no-heading -C java -f --width 1000 | grep

SERVER_NAME=`sed '/name/!d;s/.*=//' x.conf | head -n 1 | tr -d '"\t\r\n '`
SERVER_PORT=`sed '/port/!d;s/.*=//' x.conf | head -n 1 | tr -d '"\t\r\n '`
DEPLOY_DIR=`pwd`
TIME_MAX=180
TIME_USE=0
echo -e "start explore port in $TIME_MAX s"

while [[ ${TIME_USE} -lt ${TIME_MAX} ]]; do
    echo -e "use $TIME_USE s...\r\c"
    sleep 1
    PORT_EXIST=`netstat -antl | grep ":$SERVER_PORT" `
    if [[ -n "$PORT_EXIST" ]]; then
        break
    fi
    PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$DEPLOY_DIR" |awk '{print $2}'`
    if [[ -z "$PIDS" ]]; then
      echo "$SERVER_NAME startup failed."
      exit 1
    fi
    ((TIME_USE++))
done
countdown=30
countdown=$(($countdown - 1))