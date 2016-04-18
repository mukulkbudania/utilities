package com.record.matcher.rules;

import com.record.matcher.utils.DBUtil;
import com.record.matcher.model.MatchRecord;
import com.record.matcher.model.MatchConfig;

public class SoftSoftSwapMatchRule extends DoubleAttributeRule
{
    private MatchConfig softConfig1;
    private MatchConfig softConfig2;
    
    public SoftSoftSwapMatchRule() {
    }
    
    public SoftSoftSwapMatchRule(final String attribute1, final String attribute2, final MatchConfig config1, final MatchConfig config2) {
        super(attribute1, attribute2);
        this.softConfig1 = config1;
        this.softConfig2 = config2;
    }
    
    @Override
    public boolean match(final MatchRecord mRecord) {
        final String s1 = DBUtil.getValue(this.attributes.get(0), mRecord.getRecord1());
        final String s2 = DBUtil.getValue(this.attributes.get(1), mRecord.getRecord2());
        final String s3 = DBUtil.getValue(this.attributes.get(0), mRecord.getRecord2());
        final String s4 = DBUtil.getValue(this.attributes.get(1), mRecord.getRecord1());
        return new SoftMatchRule(this.softConfig1).match(s1, s2) && new SoftMatchRule(this.softConfig2).match(s3, s4);
    }
    
    @Override
    protected boolean match(final String a, final String b) {
        return false;
    }
    
    @Override
    protected double score(final String a, final String b) {
        return 0.0;
    }
    
    @Override
    public double score(final MatchRecord mRecord) {
        return 0.0;
    }
}
