package com.record.matcher.rules;

import java.util.List;
import java.util.ArrayList;

public class InitialsMatchRule extends SingleAttributeRule
{
    public InitialsMatchRule() {
    }
    
    public InitialsMatchRule(final String attribute) {
        final List<String> attributes = new ArrayList<String>();
        attributes.add(attribute);
        this.setAttributes(attributes);
    }
    
    public boolean match(final String s1, final String s2) {
        return (!"null".equals(s1) || !"null".equals(s2)) && s1 != null && s2 != null && !s1.isEmpty() && !s2.isEmpty() && !"null".equals(s1) && !"null".equals(s2) && (s1.length() <= 2 || s2.length() <= 2) && s1.charAt(0) == s2.charAt(0);
    }
    
    @Override
    protected double score(final String a, final String b) {
        return 0.0;
    }
}
