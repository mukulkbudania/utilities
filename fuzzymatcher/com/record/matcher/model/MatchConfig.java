package com.record.matcher.model;

public class MatchConfig
{
    private String AttributeName;
    private AlgorithmConfig algoConfig;
    
    public MatchConfig() {
    }
    
    public MatchConfig(final String attributeName, final AlgorithmConfig config) {
        this.AttributeName = attributeName;
        this.algoConfig = config;
    }
    
    public String getAttributeName() {
        return this.AttributeName;
    }
    
    public void setAttributeName(final String attributeName) {
        this.AttributeName = attributeName;
    }
    
    public AlgorithmConfig getAlgoConfig() {
        return this.algoConfig;
    }
    
    public void setAlgoConfig(final AlgorithmConfig algoConfig) {
        this.algoConfig = algoConfig;
    }
    
    @Override
    public String toString() {
        return "Attribute: " + this.AttributeName + "AlgorithmConfig" + this.getAlgoConfig();
    }
    
    @Override
    public int hashCode() {
        return this.AttributeName.hashCode() * 31 + this.getAlgoConfig().hashCode() * 31;
    }
    
    public boolean equals(final MatchConfig mConfig) {
        return this.AttributeName.equals(mConfig.AttributeName) && this.getAlgoConfig().equals(mConfig.getAlgoConfig());
    }
}
