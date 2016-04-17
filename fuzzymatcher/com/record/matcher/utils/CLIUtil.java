// 
// Decompiled by Procyon v0.5.30
// 

package com.record.matcher.utils;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import org.apache.commons.cli2.Group;
import org.apache.commons.cli2.util.HelpFormatter;
import org.apache.commons.cli2.commandline.Parser;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.builder.GroupBuilder;
import org.apache.commons.cli2.builder.ArgumentBuilder;
import org.apache.commons.cli2.builder.DefaultOptionBuilder;
import org.apache.commons.cli2.option.DefaultOption;
import org.apache.commons.cli2.CommandLine;
import java.util.logging.Logger;

public class CLIUtil
{
    private static final Logger logger;
    private CommandLine cmdLine;
    private DefaultOption inputFileOpt;
    private DefaultOption outputFileOpt;
    
    static {
        logger = Logger.getLogger(CLIUtil.class.getName());
    }
    
    public CLIUtil(final String[] args) {
        this.cmdLine = null;
        this.inputFileOpt = null;
        this.outputFileOpt = null;
        final DefaultOptionBuilder obuilder = new DefaultOptionBuilder();
        final ArgumentBuilder abuilder = new ArgumentBuilder();
        final DefaultOption help = obuilder.withLongName("help").withDescription("print this list").create();
        this.inputFileOpt = obuilder.withShortName("i").withLongName("dataSource").withRequired(true).withArgument(abuilder.withName("datasource").withMinimum(1).withMaximum(1).create()).withDescription("The csv file from where the data needs to be loaded").create();
        this.outputFileOpt = obuilder.withShortName("o").withLongName("resultsFile").withRequired(true).withArgument(abuilder.withName("dataSource").withMinimum(1).withMaximum(1).create()).withDescription("The txt file where the results will be stored").create();
        final Group normalArgs = new GroupBuilder().withOption(help).withOption(this.inputFileOpt).withOption(this.outputFileOpt).create();
        final Parser parser = new Parser();
        parser.setHelpOption(help);
        parser.setHelpTrigger("--help");
        parser.setGroup(normalArgs);
        parser.setHelpFormatter(new HelpFormatter(" ", "", " ", 130));
        this.cmdLine = parser.parseAndHelp(args);
    }
    
    public File getDataSource() {
        File file = null;
        final String dataSource = (String)this.cmdLine.getValue(this.inputFileOpt);
        try {
            file = new File(dataSource);
            final FileReader fileReader = new FileReader(file);
        }
        catch (FileNotFoundException e) {
            System.out.println("Not able to find the datasource file: " + dataSource);
            System.exit(1);
        }
        return file;
    }
    
    public File getResultsFile() {
        File file = null;
        final String resultsFile = (String)this.cmdLine.getValue(this.outputFileOpt);
        try {
            file = new File(resultsFile);
            final FileWriter fileWriter = new FileWriter(file);
        }
        catch (IOException e) {
            System.out.println("Cannot write to file: " + resultsFile);
            System.exit(1);
        }
        return file;
    }
}
