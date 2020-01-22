#!/usr/bin/env bash

#撤销上一次commit
git reset --soft HEAD^
#更改commit 消息

git commit --amend删除无用配置文件 #C2019121027956

git add *.properties
git commit -m 'XXXX'