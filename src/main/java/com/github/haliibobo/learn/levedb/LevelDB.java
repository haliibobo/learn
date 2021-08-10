package com.github.haliibobo.learn.levedb;

import static org.fusesource.leveldbjni.JniDBFactory.factory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Map;
import org.iq80.leveldb.CompressionType;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;

public class LevelDB {

  public static void main(String[] args) throws InterruptedException, IOException {

    DB database;
    Path db = Paths.get("/tmp/leveldb/database", "account");
    File file = db.toFile();
    org.iq80.leveldb.Options dbOptions = newDefaultLevelDbOptions();
    long s = System.currentTimeMillis();
    database = factory.open(file, dbOptions);
    long e = System.currentTimeMillis();
    System.out.println("open cost :" + (e - s));
    int sum = 0;
    int keySum = 0;
    int valueSum = 0;
    DBIterator iterable = database.iterator();
    iterable.seekToFirst();
    while (iterable.hasNext()) {
      Map.Entry<byte[],byte[]> entry =iterable.next();
      sum++;
      keySum += Arrays.hashCode(entry.getKey());
      valueSum +=  Arrays.hashCode(entry.getValue());
      String checksum = keySum + ":" + valueSum + "\n";
      try {
        Files.write(Paths.get("/tmp/leveldb/database/jni.txt"), checksum.getBytes(), StandardOpenOption.APPEND);
      }catch (IOException eee) {
      }
    }
    long ee = System.currentTimeMillis();
    System.out.println("ite cost: " + (ee - e));
    System.out.println("record:" + sum + ",keySum:" + keySum + ",valueSum:" + valueSum);
    iterable.close();
    database.close();


  }
  private static org.iq80.leveldb.Options newDefaultLevelDbOptions() {
    org.iq80.leveldb.Options dbOptions = new org.iq80.leveldb.Options();
    dbOptions.createIfMissing(true);
    dbOptions.paranoidChecks(true);
    dbOptions.verifyChecksums(true);
    dbOptions.compressionType(CompressionType.SNAPPY);
    dbOptions.blockSize(4 * 1024);
    dbOptions.writeBufferSize(10 * 1024 * 1024);
    dbOptions.cacheSize(10 * 1024 * 1024L);
    dbOptions.maxOpenFiles(1000);
    return dbOptions;
  }
}

