package com.record.matcher.rules;

import java.util.List;
import java.util.ArrayList;

public class NullMatchRule extends SingleAttributeRule
{
    public NullMatchRule() {
    }
    
    public NullMatchRule(final String attribute) {
        final List<String> attributes = new ArrayList<String>();
        attributes.add(attribute);
        this.setAttributes(attributes);
    }
    
    public boolean match(final String s1, final String s2) {
        return (!"null".equals(s1) || !"null".equals(s2)) && ("null".equals(s1) || "null".equals(s2));
    }
    
    @Override
    protected double score(final String a, final String b) {
        return 0.0;
    }
}
