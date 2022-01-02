package com.shion1305.ynu_cat;

import com.linecorp.bot.model.response.BotApiResponse;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class DeliveryLogger {
    public static final String logLocation = "/YNUCatMessaging/MessageDelivery.log";
    static Logger logger;
    static Logger systemLogger = Logger.getLogger("YnuCat-Logger");

    static void log(BotApiResponse resp, boolean auto) {
        if (logger == null) {
            init();
        }
        if (resp.getMessage() == null && resp.getDetails().isEmpty()) {
            //SUCCESS
            logger.info("0" + (auto ? "A" : "M") + " " + resp.getRequestId());
        } else {
            //A stands for Auto, M stands for Manual
            logger.severe("1" + (auto ? "A" : "M") + " " + resp.getRequestId() + " " + resp.getMessage() + " " + resp.getDetails().toString());
        }
    }

    static void logError(Exception e, boolean auto) {
        init();
        logger.severe("2" + (auto ? "A" : "M") + " " + e.getClass().getName());
    }

    private static void init() {
        try {
            String dir = System.getProperty("user.home");
            File file = new File(dir + logLocation);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
            FileHandler handler = new FileHandler(dir + logLocation);
            logger = Logger.getAnonymousLogger();
            logger.addHandler(handler);
            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    DateFormat format = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
                    return format.format(new Date(record.getMillis())) + record.getMessage() + "\n";
                }
            });
        } catch (Exception e) {
            systemLogger.severe("Error Occurred in Initial Setting: " + e.getClass().getName());
            e.printStackTrace();
        }
    }
}
