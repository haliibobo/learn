import os

set = set()
list = []
dict = dict()
path ="./"

if __name__ == "__main__" :
  fileList = os.listdir(path)
  for f in fileList:
    print os.path.splitext(f)[1]