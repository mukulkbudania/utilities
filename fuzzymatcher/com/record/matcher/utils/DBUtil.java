// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.utils;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.record.matcher.configuration.MatcherContext;

public class DBUtil
{
    private static MatcherContext context;
    private static Map<String, List<String>> verticalDB;
    
    static {
        DBUtil.context = MatcherContext.getInstance();
        DBUtil.verticalDB = DBUtil.context.getVerticalDatabase();
    }
    
    public static String getValue(final String attributeName, final int record1) {
        return DBUtil.verticalDB.get(attributeName).get(record1);
    }
    
    public static String getValue(final int record1) {
        final StringBuilder result = new StringBuilder();
        for (final String attribute : DBUtil.verticalDB.keySet()) {
            result.append(getValue(attribute, record1));
            result.append(" \t ");
        }
        result.substring(0, result.lastIndexOf("\t") - 1);
        return result.toString();
    }
    
    public static String getValue(final int record1, final List<String> attributesOrder) {
        final StringBuilder result = new StringBuilder();
        for (final String attribute : attributesOrder) {
            result.append(getValue(attribute, record1));
            result.append(" \t ");
        }
        result.substring(0, result.lastIndexOf("\t") - 1);
        return result.toString();
    }
}
