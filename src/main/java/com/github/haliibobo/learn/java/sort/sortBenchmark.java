package com.github.haliibobo.learn.java.sort;/*
package com.jd.java8.sort;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class sortBenchmark {

    private static final Random random = new Random("SEED".hashCode());
    private static final int nodeMaxLength = 6;
    private static final int pathMaxNodes = 10;
    private static final int totalPath = 100000;
    private static final char[] alphaBate = new char[]{
        'a', 'b', 'c', 'd', 'e', 'f', '0', '1',
        '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private static final List<String> pathList = createPathes();

    private static String createNode() {
        StringBuilder builder = new StringBuilder();
        int length = random.nextInt(nodeMaxLength) + 1;
        for (int i=0;i<length;i++) {
            builder.append(alphaBate[random.nextInt(alphaBate.length)]);
        }
        return builder.toString();
    }

    private static List<String> createPathes() {
        List<String> pathList = new LinkedList<>();
        List<String> pathNow = new LinkedList<>();
        for (int i=0;i<totalPath;i++) {
            boolean backspace = random.nextBoolean()
                && !pathNow.isEmpty() || (pathNow.size() >= pathMaxNodes);
            if (backspace) {
                pathNow.remove(pathNow.size() - 1);
            }
            pathNow.add(createNode());
            pathList.add(Joiner.on(".").join(pathNow));
        }
        Collections.shuffle(pathList);
        return pathList;
    }

    public static class Pair<A, B> {
        final A a;
        final B b;
        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A getA() {
            return a;
        }
        public B getB() {
            return b;
        }
    }

    public static int compareStringArray(String[] parts1, String[] parts2) {
        int maxLength = Math.max(parts1.length, parts2.length);
        for (int i = 0; i < maxLength; i++) {
            if (i >= parts1.length) {
                return -1;
            } else if (i >= parts2.length) {
                return 1;
            }
            int compareResult = parts1[i].compareTo(parts2[i]);
            if (compareResult != 0) {
                return compareResult;
            }
        }
        return 0;
    }

    public static <E, K extends Comparable<K>> List<E> sorted(Collection<E> arr, KeyGenerator<E, K> gen) {
        return arr.parallelStream().map(e -> new Pair<>(e, gen.generate(e)))
            .sorted(Comparator.comparing(Pair::getB))
            .map(Pair::getA).collect(Collectors.toList());
    }

    @Benchmark
    public void measureName() {
    }

    @FunctionalInterface
    public interface KeyGenerator<E, K extends Comparable<K>> {
        K generate(E elem);
    }

    public static class ComparableSequence<T extends Comparable<T>> implements Comparable<ComparableSequence<T>> {

        final Iterable<T> elements;

        public ComparableSequence(Iterable<T> elements) {
            this.elements = elements;
        }

        @Override
        public int compareTo(ComparableSequence<T> o) {
            Iterator<T> a = elements.iterator();
            Iterator<T> b = o.elements.iterator();
            while (a.hasNext() || b.hasNext()) {
                if (!a.hasNext()) {
                    return -1;
                } else if (!b.hasNext()) {
                    return 1;
                }
                int r = a.next().compareTo(b.next());
                if (r != 0) {
                    return r;
                }
            }
            return 0;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void earlyEvalSorting() throws InterruptedException {
        pathList.parallelStream().map(key -> new Pair<>(key, key.split("\\.")))
            .sorted((o1, o2) -> compareStringArray(o1.getB(), o2.getB())).map(Pair::getA)
            .collect(Collectors.toList());
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void splitOnCompareTo() throws InterruptedException {
        pathList.sort((o1, o2) -> {
            String[] parts1 = o1.split("\\.");
            String[] parts2 = o2.split("\\.");
            int maxLength = Math.max(parts1.length, parts2.length);
            for (int i=0;i<maxLength;i++) {
                if (i>=parts1.length) {
                    return -1;
                } else if(i>=parts2.length) {
                    return 1;
                }
                int compareResult = parts1[i].compareTo(parts2[i]);
                if (compareResult != 0) {
                    return compareResult;
                }
            }
            return 0;
        });
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void splitSortJoin() throws InterruptedException {
        final Joiner joiner = Joiner.on(".");
        pathList.parallelStream().map(s -> s.split("\\."))
            .sorted(sortBenchmark::compareStringArray)
            .map(joiner::join)
            .collect(Collectors.toList());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void complexImpl() throws InterruptedException {
        sorted(pathList, s -> new ComparableSequence<>(Arrays.asList(s.split("\\."))));
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(sortBenchmark.class.getSimpleName())
            .forks(1)
            .build();

        new Runner(opt).run();
    }
}
*/
