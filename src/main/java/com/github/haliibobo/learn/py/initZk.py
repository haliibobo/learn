# pip install zkpython
# pip install kazoo
import json
from kazoo.client import KazooClient
from kazoo.client import KazooState
from kazoo.retry import KazooRetry


# init business for separate node
def init_business():
  client = KazooClient(hosts='127.0.0.1:2181')
  client.start()
  f = open('./xxx.json', 'rb')
  content = f.read()
  items = json.loads(content).items()
  for key, value in items:
    if (not client.exists('/xxx/xxx/xxxx/' + key)):
      client.create('/xxx/xxxx/xxxx/' + key,
                    value.encode(encoding="utf-8"), makepath=True)


# init degrade for separate node
def init_degrade_sep():
  client = KazooClient(hosts='127.0.0.1:2181')
  client.start()
  f = open('./xxx.json', 'rb')
  content = f.read()
  items = json.loads(content).items()
  for key, value in items:
    if not client.exists('/xxx/mixer/xxx/' + key):
      client.create('/xxx/xxx/xxx/' + key,
                    value.encode(encoding="utf-8"), makepath=True)


# init degrade for one node
def init_degrade():
  client = KazooClient(hosts='127.0.0.1:2181')
  client.start()
  f = open('./xxx.json', 'rb')
  content = f.read()
  if not client.exists('/xxx/xxx/xx'):
    client.create('/x/xx/xxx',
                  content.encode(encoding="utf-8"), makepath=True)

if __name__ == '__main__':
  #init_business()
  init_degrade()