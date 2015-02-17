package org.catalystitservices.PageObjectFramework.Framework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class SeleniumLogger {
	
    protected final String Warning = " WARN ";
    protected final String Info = " INFO ";
    protected final String Error = " ERROR";
    protected final String Start = " START";
    protected final String Finish = " FNSHD";
    protected final String Pass = " PASS ";
    protected final String Fail = " FAIL ";
    protected final String Time = " TIME ";
    protected final String Message = " -----";
    protected final String DashedLine = "-------------------------------------------------";

    private static final Map<String, SeleniumLogger> LoggerDict =
        new HashMap<String, SeleniumLogger>();
    private String _logFilePath;

    // The Directory of the log files.
    private String _logDir = "C:\\Selenium\\Logs\\";
    
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
        Date datetime = new Date();
        this._logFilePath = String.format("%s%s_%s.txt",
                _logDir,
                datetime.toString(),
                descriptiveLogName);

        File f = new File(this._logFilePath);
        if (!f.exists())
        {
            writeStartMessage(datetime);
        }
    }

    private void createLogDirectory()
    {
    	File dir = new File(_logDir);
        if (!dir.exists())
        {
            dir.mkdir();
        }
    }

    private void writeStartMessage(Date datetime)
    {
    	log("Starting Log...", Message);
    }

    private void log(String message, String level)
    {
        final String msgfmt = "%s%s- %s\n";
        Date datetime = new Date();

        try (FileWriter outfile = new FileWriter(_logFilePath, true))
        {
            outfile.write(String.format(msgfmt, 
                datetime.toString(), level, message));
            outfile.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void logDashedLine()
    {
    	log(DashedLine, Message);
    }

    public void logError(String errorMessage)
    {
        log(errorMessage, Error);
    }

    public void logFail(String testName)
    {
        log(testName + "() failed.", Fail);
    }

    public void logFinishTestSuite()
    {
    	logDashedLine();
        log("Finished Test Suite!", Finish);
        logDashedLine();
    }

    public void logInfo(String infoMessage)
    {
    	log(infoMessage, Info);
    }

    public void logMessage(String message)
    {
    	log(message, Message);
    }

    public void logPass(String testName)
    {
    	log(testName + "() passed!", Pass);
    }

    public void logStartTest(String testName)
    {
    	logDashedLine();
        log(testName + "() started!", Start);
        logDashedLine();
    }

    public void logStartTestSuite()
    {
    	logDashedLine();
        log("Starting Test Suite!", Start);
    }

    public void logTime(String message, Timer timeSpan)
    {
    	log(String.format("{0}: {1}",
            message, timeSpan), Time);
    }

    public void logWarning(String warningMessage)
    {
    	log(warningMessage, Warning);
    }
}
