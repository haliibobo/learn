package com.github.haliibobo.learn.java;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {


    /**
     * 过滤重复出现的元素.
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 过滤重复出现的元素.
     */
    public static <T> Predicate<T> distinctByKeySynch(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = Sets.newHashSet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    /**
     * 用于生成 ImmutableMap 的 Collector.
     * 对应 Collectors.toMap
     */
    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(
        final Function<? super T, ? extends K> keyMapper,
        final Function<? super T, ? extends V> valueMapper) {
        return Collectors.collectingAndThen(
            Collectors.toMap(keyMapper, valueMapper),
            ImmutableMap::copyOf);
    }

    /**
     * 用于生成 ImmutableMap 的 Collector.
     * 对应 Collectors.toMap
     */
    public static <T, K, U> Collector<T, ?, Map<K, U>> toImmutableMap(
        Function<? super T, ? extends K> keyMapper,
        Function<? super T, ? extends U> valueMapper,
        BinaryOperator<U> mergeFunction) {
        return Collectors.collectingAndThen(
            Collectors.toMap(keyMapper, valueMapper, mergeFunction),
            ImmutableMap::copyOf);
    }

    /**
     * 用于生成 ImmutableList 的 Collector.
     * 对应 Collectors.toList
     */
    public static <T> Collector<T, ?, ImmutableList<T>> toImmutableList() {
        return Collector.of(
            Builder<T>::new,
            Builder::add,
            (b1, b2) -> b1.addAll(b2.build()),
            Builder::build
        );
    }

    /**
     * Returns a stream in which each element is the result of
     * passing the corresponding element of each of streamA and
     * streamB to function.
     */
    public static <A, B, C> Stream<C> zip(Stream<? extends A> a,
        Stream<? extends B> b,
        BiFunction<? super A, ? super B, ? extends C> zipper) {
        Objects.requireNonNull(zipper);
        Spliterator<? extends A> spliteratorA = Objects.requireNonNull(a).spliterator();
        Spliterator<? extends B> spliteratorB = Objects.requireNonNull(b).spliterator();

        // Zipping looses DISTINCT and SORTED characteristics
        int characteristics = spliteratorA.characteristics() & spliteratorB.characteristics()
            & (Spliterator.DISTINCT | Spliterator.SORTED);

        long zipSize = ((characteristics & Spliterator.SIZED) != 0)
            ? Math.min(spliteratorA.getExactSizeIfKnown(), spliteratorB.getExactSizeIfKnown())
            : -1;

        Iterator<A> iteratorA = Spliterators.iterator(spliteratorA);
        Iterator<B> iteratorB = Spliterators.iterator(spliteratorB);
        Iterator<C> iteratorC = new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return iteratorA.hasNext() && iteratorB.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(iteratorA.next(), iteratorB.next());
            }
        };

        Spliterator<C> split = Spliterators.spliterator(iteratorC, zipSize, characteristics);
        return (a.isParallel() || b.isParallel())
            ? StreamSupport.stream(split, true)
            : StreamSupport.stream(split, false);
    }

    /**
     * iterableMap.
     */
    public static <F, T> Iterable<T> iterableMap(
        Iterable<F> iterable, Function<? super F, ? extends T> mapper) {
        return () -> new Iterator<T>() {
            final Iterator<F> parent = iterable.iterator();

            @Override
            public boolean hasNext() {
                return parent.hasNext();
            }

            @Override
            public T next() {
                return mapper.apply(parent.next());
            }
        };
    }

    /**
     * 集合深拷贝.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> deepCopy(List<T> src) {

        List<T> dest = new ArrayList<>(src.size());
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List<T>) in.readObject();

        } catch (Exception e) {
        }
        return dest;
    }

    /**
     * 将 Iterator 转为 Stream.
     */
    public static <T> Stream<T> toStream(Iterator<T> iterator, boolean parallel) {
        Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }
}
