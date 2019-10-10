package com.github.haliibobo.learn.java.collection;


import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lizibo
 * @version 1.0
 * @date 2019-03-14 14:22
 * @description TODO
 */
public class GroupBy {

    public static void main(String[] args) {

        List<Fruit> fruitList = Lists.newArrayList(new Fruit("apple", 6D),
            new Fruit("apple", 6D),
            new Fruit("banana", 7D), new Fruit("banana", 7D),
            new Fruit("banana", 7D), new Fruit("grape",8D));
        Map<String, List<Double>> groupMap =
            fruitList.stream().collect(Collectors.groupingBy(Fruit::getName,
                Collectors.mapping(Fruit::getPrice, Collectors.toList())));
        System.out.println(groupMap);
    }
    static class Fruit {

        private String name;
        private Double price;

        public Fruit(String name, Double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Fruit fruit = (Fruit) o;
            return java.util.Objects.equals(name, fruit.name) &&
                java.util.Objects.equals(price, fruit.price);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(name, price);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
}
