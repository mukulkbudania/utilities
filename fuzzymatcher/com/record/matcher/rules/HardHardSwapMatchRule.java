package com.record.matcher.rules;

import com.record.matcher.utils.DBUtil;
import com.record.matcher.model.MatchRecord;

public class HardHardSwapMatchRule extends DoubleAttributeRule
{
    public HardHardSwapMatchRule() {
    }
    
    public HardHardSwapMatchRule(final String attribute1, final String attribute2) {
        super(attribute1, attribute2);
    }
    
    @Override
    public boolean match(final MatchRecord mRecord) {
        final String s1 = DBUtil.getValue(this.attributes.get(0), mRecord.getRecord1());
        final String s2 = DBUtil.getValue(this.attributes.get(1), mRecord.getRecord2());
        final String s3 = DBUtil.getValue(this.attributes.get(0), mRecord.getRecord2());
        final String s4 = DBUtil.getValue(this.attributes.get(1), mRecord.getRecord1());
        return new HardMatchRule().match(s1, s2) && new HardMatchRule().match(s3, s4);
    }
    
    @Override
    protected boolean match(final String a, final String b) {
        return false;
    }
    
    @Override
    public double score(final MatchRecord mRecord) {
        return 0.0;
    }
    
    @Override
    protected double score(final String a, final String b) {
        return 0.0;
    }
}
