package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Petr Arsentev";
        char character = '*';
        boolean bool = false;
        byte byteNum = 7;
        short shortNum = 12;
        int intNum = 789;
        long longNum = 123456;
        float floatNum = 123.1f;
        double doubleNum = 456.123d;
        LOG.debug("User info name : {}, symbol : {}", name, character);
        LOG.info("info message : status {}, byteNum : {}", bool, byteNum);
        LOG.warn("warn message : shortNum {}, intNum : {}", shortNum, intNum);
        LOG.error("error message : longNum {}, floatNum : {}, doubleNum : {}", longNum, floatNum, doubleNum);
    }
}
