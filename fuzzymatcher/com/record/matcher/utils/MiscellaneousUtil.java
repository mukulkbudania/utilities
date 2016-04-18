package com.record.matcher.utils;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class MiscellaneousUtil
{
    public static <K, V extends Comparable> Map<K, V> sortByValues(final Map<K, V> map) {
        final List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(final Map.Entry<K, V> o1, final Map.Entry<K, V> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        final Map<K, V> sortedMap = new LinkedHashMap<K, V>();
        for (final Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
