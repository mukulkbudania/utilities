// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.service;

import java.util.List;
import com.record.matcher.utils.DBUtil;
import java.util.ArrayList;
import com.record.matcher.utils.MiscellaneousUtil;
import com.record.matcher.configuration.Clue;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import com.record.matcher.model.MatchRecord;
import java.util.Map;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import com.record.matcher.utils.CLIUtil;
import com.record.matcher.configuration.MatcherContext;

public class FuzzyMatcher
{
    private static final String DELIMITER = "\t";
    private static final MatcherContext context;
    public static boolean debug;
    
    static {
        context = MatcherContext.getInstance();
        FuzzyMatcher.debug = true;
    }
    
    public static void main(final String[] args) {
        final CLIUtil cli = new CLIUtil(args);
        final File dataSource = cli.getDataSource();
        try {
            FuzzyMatcher.context.loadData(dataSource, "\t");
        }
        catch (FileNotFoundException e) {
            System.out.println("Not able to find the datasource file: " + dataSource.getAbsolutePath());
            System.exit(1);
        }
        FuzzyMatcher.context.loadConfiguration();
        System.out.println("Successfully loaded configurations");
        final Map<MatchRecord, Double> result = getFinalMatches();
        System.out.println("Successfully matched " + result.size() + " records.");
        BufferedWriter matchResultWriter = null;
        final File resultsFile = cli.getResultsFile();
        try {
            matchResultWriter = new BufferedWriter(new FileWriter(resultsFile));
            matchResultWriter.write("RECORD_NO\t");
            matchResultWriter.write("GC_DC_TITLE\t");
            matchResultWriter.write("GC_DC_FNAME\t");
            matchResultWriter.write("GC_DC_MNAME\t");
            matchResultWriter.write("GC_DC_LNAME\t");
            matchResultWriter.write("SCORE\n");
            for (final Map.Entry<MatchRecord, Double> RecordResult : result.entrySet()) {
                addResulttoFile(RecordResult, matchResultWriter);
            }
            System.out.println("Successfully writen records to " + resultsFile.getName());
        }
        catch (IOException ex) {
            System.out.println("Not able to write results to file.");
            System.exit(1);
            try {
                if (matchResultWriter != null) {
                    matchResultWriter.close();
                }
            }
            catch (IOException ioEx) {
                System.out.println("Cannot close File" + resultsFile.getName());
                System.exit(1);
            }
            return;
        }
        finally {
            try {
                if (matchResultWriter != null) {
                    matchResultWriter.close();
                }
            }
            catch (IOException ioEx) {
                System.out.println("Cannot close File" + resultsFile.getName());
                System.exit(1);
            }
        }
        try {
            if (matchResultWriter != null) {
                matchResultWriter.close();
            }
        }
        catch (IOException ioEx) {
            System.out.println("Cannot close File" + resultsFile.getName());
            System.exit(1);
        }
    }
    
    private static Map<MatchRecord, Double> getFinalMatches() {
        print("In Getting Final Matches");
        final Map<MatchRecord, Double> toBeConsideredRecords = new HashMap<MatchRecord, Double>();
        final int numberOfRecords = FuzzyMatcher.context.getRecordFile().getNumberOfRecords();
        final String NAME_MATCH_CLUE = "NAME_MATCH_CLUE";
        for (int i = 0; i < numberOfRecords; ++i) {
            for (int j = i + 1; j < numberOfRecords; ++j) {
                final MatchRecord mRecord = new MatchRecord(i, j);
                final Clue nameMatchClue = FuzzyMatcher.context.getConfiguration().getClues().get(NAME_MATCH_CLUE);
                final double nameMatchClueScore = nameMatchClue.score(mRecord);
                final int nameMatchClueWeight = nameMatchClue.getWeightage();
                if (nameMatchClueScore > 65.0) {
                    toBeConsideredRecords.put(mRecord, nameMatchClueScore * nameMatchClueWeight / 100.0);
                    System.out.println("Macthed Record: i: " + i + " j: " + j);
                }
            }
        }
        print("Total Match Records: " + toBeConsideredRecords.size());
        for (final MatchRecord mRecord2 : toBeConsideredRecords.keySet()) {
            double currentScore = toBeConsideredRecords.get(mRecord2);
            if (FuzzyMatcher.context.getConfiguration().getClues().get("MNAME_MATCH_CLUE").score(mRecord2) > 0.1) {
                FuzzyMatcher.context.getConfiguration().getClues().get("MNAME_MATCH_CLUE").setWeightage(5);
            }
            if (FuzzyMatcher.context.getConfiguration().getClues().get("MNAME_MATCH_CLUE").score(mRecord2) > 0.1 && FuzzyMatcher.context.getConfiguration().getClues().get("TITLE_MATCH_CLUE").score(mRecord2) > 0.1) {
                FuzzyMatcher.context.getConfiguration().getClues().get("MNAME_MATCH_CLUE").setWeightage(15);
                FuzzyMatcher.context.getConfiguration().getClues().get("TITLE_MATCH_CLUE").setWeightage(15);
            }
            for (final Clue c : FuzzyMatcher.context.getConfiguration().getClues().values()) {
                if (c.getName().equals(NAME_MATCH_CLUE)) {
                    continue;
                }
                currentScore += c.score(mRecord2) * c.getWeightage() / 100.0;
            }
            toBeConsideredRecords.put(mRecord2, currentScore);
        }
        return MiscellaneousUtil.sortByValues(toBeConsideredRecords);
    }
    
    private static void addResulttoFile(final Map.Entry<MatchRecord, Double> recordResult, final Writer matchResultWriter) throws IOException {
        final MatchRecord mRecord = recordResult.getKey();
        final List<String> attributesOrder = new ArrayList<String>();
        attributesOrder.add("GC_DC_TITLE");
        attributesOrder.add("GC_DC_FNAME");
        attributesOrder.add("GC_DC_MNAME");
        attributesOrder.add("GC_DC_LNAME");
        matchResultWriter.write(String.valueOf(mRecord.getRecord1()) + " \t" + DBUtil.getValue(mRecord.getRecord1(), attributesOrder));
        matchResultWriter.write(recordResult.getValue() + "\n");
        matchResultWriter.write(String.valueOf(mRecord.getRecord2()) + " \t" + DBUtil.getValue(mRecord.getRecord2(), attributesOrder) + "\n");
    }
    
    private static void print(final String message) {
        if (FuzzyMatcher.debug) {
            System.out.println(message);
        }
    }
}
