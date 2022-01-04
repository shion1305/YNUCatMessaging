package com.shion1305.ynu_cat.line.core;

import com.linecorp.bot.client.LineMessagingClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class LineMessagingClientManager {
    static LineMessagingClient client;
    static Logger logger=Logger.getLogger("LineMessagingClientManager");

    public static LineMessagingClient getClient() {
        if (client == null) {
            Properties setting = new Properties();
            //read setting
            try (FileInputStream in = new FileInputStream("ynu_cat_bot.properties")) {
                setting.load(in);
            } catch (IOException e) {
                logger.severe("ERROR: INCORRECT CONFIGURATION IN PROPERTIES");
                e.printStackTrace();
            }
            client = LineMessagingClient.builder(setting.getProperty("LineChannelToken")).build();
        }
        return client;
    }
}
