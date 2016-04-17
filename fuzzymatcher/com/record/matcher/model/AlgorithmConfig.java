// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.model;

import com.record.matcher.algorithms.Algorithm;

public class AlgorithmConfig
{
    private Algorithm algorithm;
    private double minTokenThreshold;
    private double minMatchThreshold;
    
    public AlgorithmConfig() {
    }
    
    public AlgorithmConfig(final Algorithm algorithm, final double minTokenThreshold, final double minMatchThreshold) {
        this.algorithm = algorithm;
        this.minTokenThreshold = minTokenThreshold;
        this.minMatchThreshold = minMatchThreshold;
    }
    
    public Algorithm getAlgorithm() {
        return this.algorithm;
    }
    
    public void setAlgorithm(final Algorithm algorithm) {
        this.algorithm = algorithm;
    }
    
    public double getMinTokenThreshold() {
        return this.minTokenThreshold;
    }
    
    public void setMinTokenThreshold(final double minTokenThreshold) {
        this.minTokenThreshold = minTokenThreshold;
    }
    
    public double getMinMatchThreshold() {
        return this.minMatchThreshold;
    }
    
    public void setMinMatchThreshold(final double minMatchThreshold) {
        this.minMatchThreshold = minMatchThreshold;
    }
}
