package org.catalystitservices.PageObjectFramework.Framework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SeleniumLogger {
	
    protected final String warning = " WARN ";
    protected final String info = " INFO ";
    protected final String error = " ERROR";
    protected final String start = " START";
    protected final String finish = " FNSHD";
    protected final String pass = " PASS ";
    protected final String Fail = " FAIL ";
    protected final String timeStr = " TIME ";
    protected final String msg = " -----";
    protected final String dashedLine = "-------------------------------------------------";

    private static final Map<String, SeleniumLogger> LoggerDict =
        new HashMap<String, SeleniumLogger>();
    private String logFilePath;

    // The Directory of the log files.
    private String logDir = SeleniumSettings.getLogDirectory();
    
    /** SeleniumLogger GetLogger(string descriptiveLogName)
     * 
     * @param - descriptive name of log.
     * ie. TestDetails, DatabaseCalls, etc.
     * 
     * Shows up in _logDir/DATE_descriptiveLogName.txt
     * 
     * This method uses a modified Singleton Design Pattern.
     * In the Singleton Design Pattern, the constructor is set to private,
     * and there is a static GetObject method and a private static instance
     * of that object. For instance, in the normal Singleton Pattern, I
     * would create a private static _logger, and the GetLogger() method
     * would call the constructor if _logger is null before sending back
     * _logger, or just simply return _logger if it has already been
     * constructed. 
     * 
     * In this modified version of the Singleton Design Pattern, we have a
     * private static List<Logger>, and when GetLogger() is called, it 
     * checks that list to see if it's already created, before returning it,
     * creating it if necessary.
     */
    public static SeleniumLogger getLogger(String descriptiveLogName)
    {
        SeleniumLogger loggerInst;
        if (!LoggerDict.containsKey(descriptiveLogName))
        {
            loggerInst = new SeleniumLogger(descriptiveLogName);
            LoggerDict.put(descriptiveLogName, loggerInst);
        }
        return LoggerDict.get(descriptiveLogName);
    }

    // Private Constructor. Only accessible through the public
    // GetLogger(descriptiveLogName) method.
    private SeleniumLogger(String descriptiveLogName)
    {
        createLogDirectory();
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String date = DATE_FORMAT.format(today);
        
        this.logFilePath = String.format("%s%s_%s.txt",
                logDir,
                date,
                descriptiveLogName);

        File f = new File(this.logFilePath);
        if (!f.exists())
        {
            writeStartMessage();
        }
    }

    private void createLogDirectory()
    { 
    	  File theDir = new File(logDir);
          if(!theDir.exists()){
          	try{
          		theDir.mkdirs();
          	}catch(SecurityException se){
          		se.printStackTrace();
          	}
          } 
    }

    private void writeStartMessage()
    {
    	log("Starting Log...", msg);
    	logDashedLine();
    }

    private void log(String message, String level)
    {
        final String msgfmt = "%s%s- %s%s";
        Date today = new Date();
        SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:SS");
        String time = TIME_FORMAT.format(today).substring(0, 8);

        
      
        
        try (FileWriter outfile = new FileWriter(logFilePath, true))
        {
            outfile.write(String.format(msgfmt, 
                time, level, message,
                System.getProperty("line.separator")));
            outfile.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void logDashedLine()
    {
    	log(dashedLine, msg);
    }

    public void logError(String errorMessage)
    {
        log(errorMessage, error);
    }

    public void logFail(String testName)
    {
        log(testName + "() failed.", Fail);
    }

    public void logFinishTestSuite()
    {
        log("Finished Test Suite!", finish);
    }

    public void logInfo(String infoMessage)
    {
    	log(infoMessage, info);
    }

    public void logMessage(String message)
    {
    	log(message, msg);
    }

    public void logPass(String testName)
    {
    	log(testName + "() passed!", pass);
    }

    public void logStartTest(String testName)
    {
        log(testName + "() started!", start);
    }

    public void logStartTestSuite()
    {
        log("Starting Test Suite!", start);
    }

    public void logTime(String message, double time)
    {
    	log(String.format("%s: %s",
            message, time), timeStr);
    }

    public void logWarning(String warningMessage)
    {
    	log(warningMessage, warning);
    }
}
