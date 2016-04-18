package com.record.matcher.configuration;

import com.record.matcher.model.MatchRecord;
import com.record.matcher.rules.SoftMatchRule;
import com.record.matcher.model.MatchConfig;
import com.record.matcher.model.AlgorithmConfig;
import com.record.matcher.algorithms.Algorithm;
import com.record.matcher.rules.HardMatchRule;
import java.util.HashMap;
import com.record.matcher.rules.Rule;
import java.util.Map;

public class Configuration
{
    private static final boolean debug = false;
    private static String FNAME;
    private static String LNAME;
    private static String MNAME;
    private static String TITLE;
    private static final String FNAME_HARDMATCH_RULE = "FNAME_HARDMATCH_RULE";
    private static final String FNAME_SOFTMATCH_RULE = "FNAME_SOFTMATCH_RULE";
    private static final String LNAME_HARDMATCH_RULE = "LNAME_HARDMATCH_RULE";
    private static final String LNAME_SOFTMATCH_RULE = "LNAME_SOFTMATCH_RULE";
    private static final String MNAME_HARDMATCH_RULE = "MNAME_HARDMATCH_RULE";
    private static final String MNAME_SOFTMATCH_RULE = "MNAME_SOFTMATCH_RULE";
    private static final String TITLE_HARDMATCH_RULE = "TITLE_HARDMATCH_RULE";
    private static final String TITLE_SOFTMATCH_RULE = "TITLE_SOFTMATCH_RULE";
    private static final String NAME_MATCH_CLUE = "NAME_MATCH_CLUE";
    private static final String MNAME_MATCH_CLUE = "MNAME_MATCH_CLUE";
    private static final String TITLE_MATCH_CLUE = "TITLE_MATCH_CLUE";
    private static final int NAME_MATCH_CLUE_WEIGHT = 50;
    private static final int MNAME_MATCH_CLUE_WEIGHT = 10;
    private static final int TITLE_MATCH_CLUE_WEIGHT = 5;
    private Map<String, Rule> rules;
    private Map<String, Clue> clues;
    private Map<String, ClueUpdater> updaters;
    
    static {
        Configuration.FNAME = "GC_DC_FNAME";
        Configuration.LNAME = "GC_DC_LNAME";
        Configuration.MNAME = "GC_DC_MNAME";
        Configuration.TITLE = "GC_DC_TITLE";
    }
    
    public Configuration() {
        this.rules = null;
        this.clues = null;
        this.updaters = null;
    }
    
    public void init() {
        this.setRules(new HashMap<String, Rule>());
        this.getRules().put("FNAME_HARDMATCH_RULE", new HardMatchRule(Configuration.FNAME));
        this.getRules().put("FNAME_SOFTMATCH_RULE", new SoftMatchRule(new MatchConfig(Configuration.FNAME, new AlgorithmConfig(Algorithm.SOFT_TF_IDF, 0.9, 0.6))));
        this.getRules().put("LNAME_HARDMATCH_RULE", new HardMatchRule(Configuration.LNAME));
        this.getRules().put("LNAME_SOFTMATCH_RULE", new SoftMatchRule(new MatchConfig(Configuration.LNAME, new AlgorithmConfig(Algorithm.SOFT_TF_IDF, 0.9, 0.6))));
        this.getRules().put("MNAME_HARDMATCH_RULE", new HardMatchRule(Configuration.MNAME));
        this.getRules().put("MNAME_SOFTMATCH_RULE", new SoftMatchRule(new MatchConfig(Configuration.MNAME, new AlgorithmConfig(Algorithm.SOFT_TF_IDF, 0.9, 0.6))));
        this.getRules().put("TITLE_HARDMATCH_RULE", new HardMatchRule(Configuration.TITLE));
        this.getRules().put("TITLE_SOFTMATCH_RULE", new SoftMatchRule(new MatchConfig(Configuration.TITLE, new AlgorithmConfig(Algorithm.SOFT_TF_IDF, 0.9, 0.6))));
        this.setClues(new HashMap<String, Clue>());
        this.getClues().put("NAME_MATCH_CLUE", new Clue("NAME_MATCH_CLUE", 50) {
            @Override
            public double score(final MatchRecord mRecord) {
                if (Configuration.this.getRules().get("FNAME_HARDMATCH_RULE").match(mRecord) && Configuration.this.getRules().get("LNAME_HARDMATCH_RULE").match(mRecord)) {
                    return 100.0;
                }
                if (Configuration.this.getRules().get("FNAME_HARDMATCH_RULE").match(mRecord) && Configuration.this.getRules().get("LNAME_SOFTMATCH_RULE").match(mRecord)) {
                    print("SCORE: " + Configuration.this.getRules().get("LNAME_SOFTMATCH_RULE").score(mRecord));
                    return Configuration.this.getRules().get("LNAME_SOFTMATCH_RULE").score(mRecord) * 100.0;
                }
                if (Configuration.this.getRules().get("LNAME_HARDMATCH_RULE").match(mRecord) && Configuration.this.getRules().get("FNAME_SOFTMATCH_RULE").match(mRecord)) {
                    print("SCORE: " + Configuration.this.getRules().get("FNAME_SOFTMATCH_RULE").score(mRecord));
                    return Configuration.this.getRules().get("FNAME_SOFTMATCH_RULE").score(mRecord) * 100.0;
                }
                if (Configuration.this.getRules().get("FNAME_SOFTMATCH_RULE").match(mRecord) && Configuration.this.getRules().get("LNAME_SOFTMATCH_RULE").match(mRecord)) {
                    print("SCORE: " + Configuration.this.getRules().get("FNAME_SOFTMATCH_RULE").score(mRecord));
                    print("SCORE: " + Configuration.this.getRules().get("LNAME_SOFTMATCH_RULE").score(mRecord));
                    return Configuration.this.getRules().get("FNAME_SOFTMATCH_RULE").score(mRecord) * 100.0 * Configuration.this.getRules().get("LNAME_SOFTMATCH_RULE").score(mRecord);
                }
                return 0.0;
            }
            
            @Override
            public void reformulateRules(final double minScore) {
            }
        });
        this.getClues().put("MNAME_MATCH_CLUE", new Clue("MNAME_MATCH_CLUE", 10) {
            @Override
            public double score(final MatchRecord mRecord) {
                if (Configuration.this.getRules().get("MNAME_HARDMATCH_RULE").match(mRecord)) {
                    return 100.0;
                }
                if (Configuration.this.getRules().get("MNAME_SOFTMATCH_RULE").match(mRecord)) {
                    print("SCORE:" + Configuration.this.getRules().get("TITLE_SOFTMATCH_RULE").score(mRecord));
                    return Configuration.this.getRules().get("MNAME_SOFTMATCH_RULE").score(mRecord) * 100.0;
                }
                return 0.0;
            }
            
            @Override
            public void reformulateRules(final double minScore) {
            }
        });
        this.getClues().put("TITLE_MATCH_CLUE", new Clue("TITLE_MATCH_CLUE", 5) {
            @Override
            public double score(final MatchRecord mRecord) {
                if (Configuration.this.getRules().get("TITLE_HARDMATCH_RULE").match(mRecord)) {
                    return 100.0;
                }
                if (Configuration.this.getRules().get("TITLE_SOFTMATCH_RULE").match(mRecord)) {
                    print("SCORE:" + Configuration.this.getRules().get("TITLE_SOFTMATCH_RULE").score(mRecord));
                    return Configuration.this.getRules().get("TITLE_SOFTMATCH_RULE").score(mRecord) * 100.0;
                }
                return 0.0;
            }
            
            @Override
            public void reformulateRules(final double minScore) {
            }
        });
    }
    
    public Map<String, Rule> getRules() {
        return this.rules;
    }
    
    public void setRules(final Map<String, Rule> rules) {
        this.rules = rules;
    }
    
    public Map<String, Clue> getClues() {
        return this.clues;
    }
    
    public void setClues(final Map<String, Clue> clues) {
        this.clues = clues;
    }
    
    public Map<String, ClueUpdater> getUpdaters() {
        return this.updaters;
    }
    
    public void setUpdaters(final Map<String, ClueUpdater> updaters) {
        this.updaters = updaters;
    }
    
    private static void print(final String message) {
    }
}
