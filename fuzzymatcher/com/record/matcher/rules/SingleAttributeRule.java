// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.rules;

import com.record.matcher.utils.DBUtil;
import com.record.matcher.model.MatchRecord;
import java.util.List;
import java.util.ArrayList;

public abstract class SingleAttributeRule extends Rule
{
    public SingleAttributeRule() {
    }
    
    public SingleAttributeRule(final String attribute) {
        final List<String> attributes = new ArrayList<String>();
        attributes.add(attribute);
        this.setAttributes(attributes);
    }
    
    @Override
    protected boolean isValid(final List<String> attributes) {
        return attributes != null && attributes.size() == 1;
    }
    
    @Override
    public boolean match(final MatchRecord mRecord) {
        final String s1 = DBUtil.getValue(this.attributes.get(0), mRecord.getRecord1());
        final String s2 = DBUtil.getValue(this.attributes.get(0), mRecord.getRecord2());
        return this.match(s1, s2);
    }
    
    @Override
    public double score(final MatchRecord mRecord) {
        final String s1 = DBUtil.getValue(this.attributes.get(0), mRecord.getRecord1());
        final String s2 = DBUtil.getValue(this.attributes.get(0), mRecord.getRecord2());
        return this.score(s1, s2);
    }
}
