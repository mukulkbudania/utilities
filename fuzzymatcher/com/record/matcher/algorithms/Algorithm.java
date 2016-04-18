package com.record.matcher.algorithms;

import java.util.HashMap;
import java.util.Map;

public enum Algorithm
{
    SOUNDEX("SOUNDEX", 0, "Soundex"), 
    SOFT_TF_IDF("SOFT_TF_IDF", 1, "Soft Tf-Idf");
    
    private String value;
    private static final Map<String, Algorithm> lookup;
    
    static {
        lookup = new HashMap<String, Algorithm>();
        Algorithm[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final Algorithm d = values[i];
            Algorithm.lookup.put(d.getValue(), d);
        }
    }
    
    private Algorithm(final String s, final int n, final String algorithm) {
        this.value = algorithm;
    }
    
    String getValue() {
        return this.value;
    }
    
    public static Algorithm get(final String value) {
        return Algorithm.lookup.get(value);
    }
}
