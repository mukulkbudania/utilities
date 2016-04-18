package com.record.matcher.model;

public class MatchRecord
{
    private int record1;
    private int record2;
    
    public MatchRecord(final int i, final int j) {
        this.record1 = i;
        this.record2 = j;
    }
    
    public int getRecord1() {
        return this.record1;
    }
    
    public void setRecord1(final int record1) {
        this.record1 = record1;
    }
    
    public int getRecord2() {
        return this.record2;
    }
    
    public void setRecord2(final int record2) {
        this.record2 = record2;
    }
    
    @Override
    public String toString() {
        return "Record1: " + this.record1 + "Record2: " + this.record2;
    }
}
