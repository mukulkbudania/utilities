// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.configuration;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.record.matcher.model.MatchRecord;

public abstract class Clue
{
    private String name;
    private int weightage;
    private double score;
    
    public Clue() {
        this.score = -1.0;
    }
    
    public Clue(final String name, final int weightage) {
        this.score = -1.0;
        this.name = name;
        this.weightage = weightage;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public int getWeightage() {
        return this.weightage;
    }
    
    public void setWeightage(final int weightage) {
        this.weightage = weightage;
    }
    
    public abstract double score(final MatchRecord p0);
    
    public abstract void reformulateRules(final double p0);
    
    public Map<MatchRecord, Double> scoreAll(final List<MatchRecord> mRecordList) {
        final Map<MatchRecord, Double> resultsList = new HashMap<MatchRecord, Double>();
        for (final MatchRecord mRecord : mRecordList) {
            resultsList.put(mRecord, this.score(mRecord));
        }
        return resultsList;
    }
    
    public double getScore() {
        return this.score;
    }
    
    public void setScore(final double score) {
        this.score = score;
    }
}
