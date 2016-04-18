package com.record.matcher.configuration;

import java.util.Map;

public abstract class ClueUpdater
{
    private String name;
    
    public abstract boolean condition(final Map<Clue, Double> p0);
    
    public abstract void update();
    
    public ClueUpdater() {
    }
    
    public ClueUpdater(final String name) {
        this.setName(name);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
}
