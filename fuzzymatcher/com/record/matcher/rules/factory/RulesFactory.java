// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.rules.factory;

public class RulesFactory
{
    private static RulesFactory INSTANCE;
    
    public synchronized RulesFactory getInstance() {
        if (RulesFactory.INSTANCE == null) {
            RulesFactory.INSTANCE = new RulesFactory();
        }
        return RulesFactory.INSTANCE;
    }
}
