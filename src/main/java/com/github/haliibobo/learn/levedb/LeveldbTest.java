package com.github.haliibobo.learn.levedb;

import com.google.common.primitives.Ints;
import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class LeveldbTest {

    public static void main(String[] args) throws IOException {
        DBFactory factory = JniDBFactory.factory;
        Options options = new Options();
        options.createIfMissing(true).bitsPerKey(10);
        long s = System.currentTimeMillis();
        int sum = 0;
        try (DB db = factory.open(new File("/Users/halibobo/data/tmp/db"), options)) {
            for (int i = 0; i < 100000; i++) {
                db.put(Ints.toByteArray(i), Ints.toByteArray(i));
            }
            System.out.println("cost: " + (System.currentTimeMillis() - s) );
            // Use the db in here....

            int keySum = 0;
            int valueSum = 0;
            DBIterator iterable = db.iterator();
            iterable.seekToFirst();
            while (iterable.hasNext()) {
                Map.Entry<byte[],byte[]> entry =iterable.next();
                sum++;
                keySum += Arrays.hashCode(entry.getKey());
                valueSum +=  Arrays.hashCode(entry.getValue());
                String checksum = keySum + ":" + valueSum + "\n";
                System.out.println(checksum);
            }
            iterable.close();
        }
        System.out.println(sum);
        // Make sure you close the db to shutdown the
        // database and avoid resource leaks.
    }
}
