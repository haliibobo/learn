package com.github.haliibobo.learn.java.collection;


import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * @author lizibo
 * @version 1.0
 * @date 2019-01-22 16:34
 * @description TODO
 */
public class TestMax {
    public static void main(String[] args){
        List<History> histories = new ArrayList<>();

        History history = new History();
        history.setProb(0.1111);
        history.setFinished(true);

        histories.add(history);
        OptionalDouble newProb =histories.stream().filter(History::isFinished).mapToDouble(History::getProb).max();
        System.out.println(newProb.orElse(-1));

    }

    public static  class History {

        private double prob;
        private boolean finished;


        public void setProb(double prob) {
            this.prob = prob;
        }

        public double getProb() {
            return prob;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public boolean isFinished() {
            return finished;
        }
    }


}
