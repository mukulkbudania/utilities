// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.rules;

import com.wcohen.ss.api.StringDistance;
import com.record.matcher.configuration.MatcherContext;
import com.record.matcher.model.MatchConfig;

public class SoftMatchRule extends SingleAttributeRule implements SoftRule
{
    private MatchConfig config;
    
    public SoftMatchRule() {
        this.config = null;
    }
    
    public SoftMatchRule(final String attribute) {
        super(attribute);
        this.config = null;
    }
    
    public SoftMatchRule(final MatchConfig config) {
        super(config.getAttributeName());
        this.config = null;
        this.config = config;
    }
    
    @Override
    protected boolean match(final String s1, final String s2) {
        if (("null".equals(s1) && "null".equals(s2)) || ("".equals(s1) && "".equals(s2))) {
            return false;
        }
        final StringDistance distance = MatcherContext.getInstance().getTrainedDistance(this.config);
        return distance.score(s1, s2) > this.config.getAlgoConfig().getMinMatchThreshold();
    }
    
    @Override
    public double score(final String s1, final String s2) {
        if ("null".equals(s1) && "null".equals(s2)) {
            return 0.0;
        }
        final StringDistance distance = MatcherContext.getInstance().getTrainedDistance(this.config);
        return distance.score(s1, s2);
    }
}
