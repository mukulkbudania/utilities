// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.configuration;

import com.record.matcher.algorithms.Algorithm;
import com.wcohen.ss.api.StringWrapperIterator;
import com.wcohen.ss.BasicStringWrapperIterator;
import com.wcohen.ss.api.StringWrapper;
import com.wcohen.ss.api.Tokenizer;
import com.wcohen.ss.SoftTFIDF;
import com.wcohen.ss.JaroWinkler;
import com.wcohen.ss.tokens.SimpleTokenizer;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.HashMap;
import com.record.matcher.model.CSVRecordFile;
import com.wcohen.ss.api.StringDistance;
import com.record.matcher.model.MatchConfig;
import java.util.List;
import java.util.Map;

public class MatcherContext
{
    private static final boolean debug = false;
    private static MatcherContext INSTANCE;
    private Map<String, List<String>> verticalDatabase;
    private Map<MatchConfig, StringDistance> trainedDistances;
    private Configuration configuration;
    private CSVRecordFile recordFile;
    
    static {
        MatcherContext.INSTANCE = null;
    }
    
    private MatcherContext() {
        this.verticalDatabase = null;
        this.trainedDistances = null;
        this.configuration = null;
        this.recordFile = null;
        this.initialize();
    }
    
    private void initialize() {
        this.setVerticalDatabase(new HashMap<String, List<String>>());
        this.trainedDistances = new HashMap<MatchConfig, StringDistance>();
    }
    
    public static synchronized MatcherContext getInstance() {
        if (MatcherContext.INSTANCE == null) {
            MatcherContext.INSTANCE = new MatcherContext();
        }
        return MatcherContext.INSTANCE;
    }
    
    public void loadData(final File CSVRecordsFile, final String Delimiter) throws FileNotFoundException {
        final Map<String, List<String>> verticalDb = new HashMap<String, List<String>>();
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(CSVRecordsFile));
        Label_0411: {
            try {
                String[] attributeNames = null;
                String currentLine;
                if ((currentLine = br.readLine()) != null) {
                    this.print("currentLine:" + currentLine);
                    attributeNames = currentLine.split(Delimiter);
                    for (int i = 0; i < attributeNames.length; ++i) {
                        this.print("Attribute: " + attributeNames[i].trim());
                        verticalDb.put(attributeNames[i].trim(), new ArrayList<String>());
                    }
                }
                int noOfRecordsInFile = 0;
                while ((currentLine = br.readLine()) != null) {
                    ++noOfRecordsInFile;
                    final String[] attributes = currentLine.split(Delimiter);
                    for (int j = 0; j < attributes.length; ++j) {
                        verticalDb.get(attributeNames[j].trim()).add(attributes[j].trim());
                    }
                }
                System.out.println("Successfully imported " + noOfRecordsInFile + " records");
                this.setRecordFile(new CSVRecordFile(CSVRecordsFile.getAbsolutePath(), Delimiter, noOfRecordsInFile, attributeNames));
            }
            catch (IOException e) {
                this.print("Cannot Read from File" + CSVRecordsFile.getName());
                try {
                    if (br != null) {
                        br.close();
                    }
                }
                catch (IOException ioEx) {
                    this.print("Cannot close File" + CSVRecordsFile.getName());
                }
                break Label_0411;
            }
            finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                }
                catch (IOException ioEx) {
                    this.print("Cannot close File" + CSVRecordsFile.getName());
                }
            }
            try {
                if (br != null) {
                    br.close();
                }
            }
            catch (IOException ioEx) {
                this.print("Cannot close File" + CSVRecordsFile.getName());
            }
        }
        this.setVerticalDatabase(verticalDb);
        if (!this.getVerticalDatabase().isEmpty()) {
            this.print("Successfully imported records. \nThere are " + this.getVerticalDatabase().size() + " fields: ");
            for (final String attribute : this.getVerticalDatabase().keySet()) {
                this.print("\n" + attribute + " - " + this.getVerticalDatabase().get(attribute).size() + " records");
            }
        }
    }
    
    public Configuration getConfiguration() {
        return this.configuration;
    }
    
    public void loadConfiguration() {
        (this.configuration = new Configuration()).init();
    }
    
    public void exportResultsToFile() {
    }
    
    public StringDistance getTrainedDistance(final MatchConfig mConfig) {
        if (this.trainedDistances.containsKey(mConfig)) {
            return this.trainedDistances.get(mConfig);
        }
        StringDistance distance = null;
        final Algorithm matchAlgorithm = mConfig.getAlgoConfig().getAlgorithm();
        final String attributeName = mConfig.getAttributeName();
        switch (matchAlgorithm) {
            case SOFT_TF_IDF: {
                distance = new SoftTFIDF(new SimpleTokenizer(false, true), new JaroWinkler(), mConfig.getAlgoConfig().getMinTokenThreshold());
                System.out.println("Training distance for " + attributeName);
                final List<StringWrapper> tokensList = new ArrayList<StringWrapper>();
                for (final String a : this.getVerticalDatabase().get(attributeName)) {
                    if (!a.equals("null")) {
                        tokensList.add(distance.prepare(a));
                    }
                }
                if (distance instanceof SoftTFIDF) {
                    ((SoftTFIDF)distance).train(new BasicStringWrapperIterator(tokensList.iterator()));
                    break;
                }
                break;
            }
            default: {
                this.print("Not a supported Algorithm. Please check the config file again.");
                break;
            }
        }
        this.trainedDistances.put(mConfig, distance);
        return distance;
    }
    
    public CSVRecordFile getRecordFile() {
        return this.recordFile;
    }
    
    public void setRecordFile(final CSVRecordFile recordFile) {
        this.recordFile = recordFile;
    }
    
    public Map<String, List<String>> getVerticalDatabase() {
        return this.verticalDatabase;
    }
    
    public void setVerticalDatabase(final Map<String, List<String>> verticalDatabase) {
        this.verticalDatabase = verticalDatabase;
    }
    
    private void print(final String message) {
    }
}
