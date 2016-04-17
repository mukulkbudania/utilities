// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.rules;

import java.util.List;
import java.util.ArrayList;

public abstract class DoubleAttributeRule extends Rule
{
    public DoubleAttributeRule() {
    }
    
    public DoubleAttributeRule(final String attribute1, final String attribute2) {
        final List<String> attributes = new ArrayList<String>();
        attributes.add(attribute1);
        attributes.add(attribute2);
        this.setAttributes(attributes);
    }
    
    @Override
    protected boolean isValid(final List<String> attributes) {
        return attributes != null && attributes.size() == 2;
    }
}
