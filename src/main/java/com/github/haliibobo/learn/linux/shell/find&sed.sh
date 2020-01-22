#!/usr/bin/env bash
#将 git的http源头替换为ssh
find ./*/.git  -name 'config' |xargs grep  -ri "http://lizibo@git.xx.com/a" -l | xargs sed -i '' "s/http:\/\/lizibo@git.xx.com\/a/ssh:\/\/lizibo@git.xx.com:443/g"

grep  -rl 'http://lizibo@git.xx.com/a' ./*/.git/config | xargs sed -i '' "s/http:\/\/lizibo@git.xx.com\/a/ssh:\/\/lizibo@git.xx.com:443/g"
which sed
man sed