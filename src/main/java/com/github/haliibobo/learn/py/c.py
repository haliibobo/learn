#!/usr/bin/env python
import json
import urllib2
import re

fl = open('./jisuban', 'r')
data = json.load(fl)
count = 0
hits = data["responses"][0]["hits"]["hits"]
for f in hits:
  if "url_pin" in f["_source"]:
    url_pin = f["_source"]["url_pin"]
    url = f["_source"]["log-message"]["url"] + "&forcebot=1"
    temp_hi = re.findall(r"hi=(.+?)&", url)
    url = url.replace(temp_hi[0],
                      'hi=%7B%22ci%22%3A%220%22%2C%22pi%22%3A%22topspeedHomePage%22%2C%22page%22%3A1%7D')
    req = urllib2.Request("http://lizibo.jd.com" + url)
    req.add_header('Content-Type', 'application/x-www-form-urlencoded')
    req.add_header('Cookie', 'pin=' + url_pin)
    res_data = urllib2.urlopen(req)
    try:
      count = count + 1
      count1 = 0
      count2 = 0
      count3 = 0
      res = json.load(res_data)
      for d in res["data"]:
        if d["source"] == "4":
          count1 = count1 + 1
        if d["source"] == "0":
          count2 = count2 + 1
        if d["source"] == "1":
          count3 = count3 + 1
      l = len(res["data"])
      if l < 20:
        print count, count1, count2, count3, l
    except Exception:
      print "error"
