package com.tsp.context;

import com.tsp.algorithm.Algorithm;

public class Context {
    private Algorithm algorithm;


    public void play() {
        algorithm.run();
    }


    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }
}
