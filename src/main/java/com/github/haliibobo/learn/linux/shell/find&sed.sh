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

sed -i 's/55255206925/100014376346/g' jd_740ea83a8e21a_1
sed -i 's/100003353269/100014376348/g' jd_740ea83a8e21a_1
sed -i 's/3896327/100014376350/g' jd_740ea83a8e21a_1
"isLbsStoreGoods":"1"
grep  -rl 100014376350 ./*.data | xargs sed -i 's/100014376350/100014376158/g'