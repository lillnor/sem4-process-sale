/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for the log.
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "sale-log.txt";
    private static final LogHandler INSTANCE = new LogHandler();
    private PrintWriter logFile;
    
    public static LogHandler getLogger() {
        return INSTANCE;
    }
    
    private LogHandler() {
        try {
            logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
        } catch (IOException ex) {
            System.out.println("Could not create logger.");
            ex.printStackTrace();
        }
    }
    
    public void logException(Exception exception) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(", Exception was thrown: ");
        logMsgBuilder.append(exception.getMessage());
        logFile.println(logMsgBuilder);
        exception.printStackTrace(logFile);
    }

     private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
    
}
