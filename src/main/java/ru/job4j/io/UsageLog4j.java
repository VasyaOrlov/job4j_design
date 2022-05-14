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
        byte byt = 1;
        short sh = 2;
        int i = 3;
        long lon = 4;
        float fl = 0.05f;
        double db = 0.06d;
        char ch = '7';
        boolean bool = true;
        LOG.debug("variable values: {}, {}, {}, {}, {}, {}, {}, {} ", byt, sh, i, lon, fl, db, ch, bool);
    }
}