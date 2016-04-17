// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.rules;

import com.record.matcher.model.MatchRecord;
import java.util.List;

public abstract class Rule
{
    protected List<String> attributes;
    
    public List<String> getAttributes() {
        return this.attributes;
    }
    
    public void setAttributes(final List<String> attributes) {
        if (this.isValid(attributes)) {
            this.attributes = attributes;
        }
    }
    
    protected abstract boolean isValid(final List<String> p0);
    
    protected abstract boolean match(final String p0, final String p1);
    
    public abstract boolean match(final MatchRecord p0);
    
    protected abstract double score(final String p0, final String p1);
    
    public abstract double score(final MatchRecord p0);
}
