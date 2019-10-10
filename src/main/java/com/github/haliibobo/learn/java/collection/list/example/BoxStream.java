package com.github.haliibobo.learn.java.collection.list.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author lizibo
 * @version 1.0
 * @date 2019-01-23 21:04
 * @description TODO
 */
public class BoxStream {

    public static void main(String[] args) {
        List<Integer> ints = IntStream.of(1,2,3,4,5)
                .boxed()
                .collect(Collectors.toList());
        ints.removeIf(integer -> integer<10);
    }

}
