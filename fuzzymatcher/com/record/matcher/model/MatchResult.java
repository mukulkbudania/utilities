package com.record.matcher.model;

public class MatchResult
{
    private MatchConfig matchConfig;
    private MatchRecord matchRecord;
    private String Record1;
    private String Record2;
    private double distance;
    private String explaination;
    
    public MatchResult() {
    }
    
    public MatchResult(final MatchConfig mConfig, final MatchRecord mRecord, final String record1, final String record2, final double distance, final String explaination) {
        this.matchConfig = mConfig;
        this.matchRecord = mRecord;
        this.Record1 = record1;
        this.Record2 = record2;
        this.distance = distance;
        this.explaination = explaination;
    }
    
    public MatchConfig getMatchConfig() {
        return this.matchConfig;
    }
    
    public void setMatchConfig(final MatchConfig matchConfig) {
        this.matchConfig = matchConfig;
    }
    
    public MatchRecord getMatchRecord() {
        return this.matchRecord;
    }
    
    public void setMatchRecord(final MatchRecord matchRecord) {
        this.matchRecord = matchRecord;
    }
    
    public double getDistance() {
        return this.distance;
    }
    
    public void setDistance(final double distance) {
        this.distance = distance;
    }
    
    public String getExplaination() {
        return this.explaination;
    }
    
    public void setExplaination(final String explaination) {
        this.explaination = explaination;
    }
    
    public String getRecord1() {
        return this.Record1;
    }
    
    public void setRecord1(final String record1) {
        this.Record1 = record1;
    }
    
    public String getRecord2() {
        return this.Record2;
    }
    
    public void setRecord2(final String record2) {
        this.Record2 = record2;
    }
    
    @Override
    public String toString() {
        return "Match Config: " + this.matchConfig + "\nMatchRecord: " + this.matchRecord + "\nRecord1: " + this.Record1 + "\nRecord2: " + this.Record2 + "\nDistance: " + this.distance + " Explaination: " + this.explaination;
    }
}
