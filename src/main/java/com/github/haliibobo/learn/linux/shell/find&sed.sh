#!/usr/bin/env bash
#将 git的http源头替换为ssh
find ./*/.git  -name 'config' |xargs grep  -ri "http://lizibo@git.xx.com/a" -l | xargs sed -i '' "s/http:\/\/lizibo@git.xx.com\/a/ssh:\/\/lizibo@git.xx.com:443/g"

grep  -rl 'http://lizibo@git.xx.com/a' ./*/.git/config | xargs sed -i '' "s/http:\/\/lizibo@git.xx.com\/a/ssh:\/\/lizibo@git.xx.com:443/g"
which sed
man sed
sort np.res  ob.res  ob.res  | uniq -u > np-ob

sort a b | uniq > c   # c 是 a 并 b
sort a b | uniq -d > c   # c 是 a 交 b
sort a b b | uniq -u > c   # c 是 a - b


sed -i 's/test/lf/g' spring-config-zk-lf.xml
多行匹配
brew install pcre
pcregrep -M  'abc = \[(\n).*\n.*\]' ./*/*.conf
