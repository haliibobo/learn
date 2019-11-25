# learn

1.安装redis
2安装python
3. demo 
import redis
conn = redis.Redis(host='45.76.160.115',port='6379',password='xC_5smdjK)pdbCn6')
conn.set('hello','word')
conn.get('hello')
conn.ttl('hello')
4. redis-cli -host 45.76.160.115
auth xC_5smdjK)pdbCn6