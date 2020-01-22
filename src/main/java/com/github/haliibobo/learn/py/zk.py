# pip install zkpython
# pip install kazoo
import json
from kazoo.client import KazooClient
from kazoo.client import KazooState
from kazoo.retry import KazooRetry


# init
def init():
  client = KazooClient(hosts='127.0.0.1:2181')
  client.start()
  f = open('./init.json', 'rb')
  content = f.read()
  items = json.loads(content).items()
  for key, value in items:
    if (not client.exists('/init/' + key)):
      client.create('/init/' + key,
                    value.encode(encoding="utf-8"), makepath=True)

if __name__ == '__main__':
  init()