package com.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerSingleton {
	private static final Logger logger = LogManager.getLogger(LoggerSingleton.class);

    LoggerSingleton() {
        // private constructor to prevent instantiation
    }

    public Logger getLogger() {
        return logger;
    }
}
