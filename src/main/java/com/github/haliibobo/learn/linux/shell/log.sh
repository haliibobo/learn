#!/usr/bin/env bash
#查找日志的末尾1000行
tail -f -n 1000 all.log

grep -A 100 -B 100 -i '抓不到我' catalina.out
grep -100 -i 'ERROR   2019-10-11 11:00:00' all.log

at 文件名 | grep -a 'test' 二进制文件
