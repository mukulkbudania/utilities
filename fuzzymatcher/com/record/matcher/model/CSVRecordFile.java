// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.model;

import java.util.Arrays;
import java.util.List;

public class CSVRecordFile
{
    private String location;
    private int numberOfRecords;
    private List<String> fields;
    private String Delimiter;
    
    public CSVRecordFile(final String location, final String delimiter, final int noOfRecordsInFile, final String[] fields) {
        this.location = location;
        this.Delimiter = delimiter;
        this.numberOfRecords = noOfRecordsInFile;
        this.fields = Arrays.asList(fields);
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(final String location) {
        this.location = location;
    }
    
    public int getNumberOfRecords() {
        return this.numberOfRecords;
    }
    
    public void setNumberOfRecords(final int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }
    
    public List<String> getFields() {
        return this.fields;
    }
    
    public void setFields(final List<String> fields) {
        this.fields = fields;
    }
    
    public String getDelimiter() {
        return this.Delimiter;
    }
    
    public void setDelimiter(final String delimiter) {
        this.Delimiter = delimiter;
    }
}
