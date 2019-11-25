package com.github.haliibobo.learn.java.collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;



public class CollectionUtil {

    public static void main(String[] args){
        List<String>  list=Collections.singletonList("string");
        Optional<Map<String,Double>> optional =Optional.ofNullable(getRbcidMap());
        optional.ifPresent(System.out::println);

        System.out.println(optional.get());
    }


    public static Map<String, Double> getRbcidMap() {
        List<LevelTwo> list = Lists.newArrayList();
        LevelTwo levelTwo1 = new LevelTwo();
        LevelTwo levelTwo2 = new LevelTwo();
        levelTwo1.setProper("1");
        levelTwo2.setProper("2");
        levelTwo1.setValue(1);
        levelTwo2.setValue(2);
        list.add(levelTwo1);
        list.add(levelTwo2);


        return (Optional.ofNullable(list).orElseGet(ImmutableList::of))
            .stream()
            .collect(Collectors.toMap(
                LevelTwo::getProper,
                LevelTwo::getValue
            ));
    }

    public static class LevelTwo {
        private String proper;
        private double value;

        public void setProper(String proper) {
            this.proper = proper;
        }

        public String getProper() {
            return proper;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }
}
