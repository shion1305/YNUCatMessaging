package com.shion1305.ynu_cat.line.core;

import com.linecorp.bot.client.LineMessagingClient;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

public class LineMessagingClientManager {
    static LineMessagingClient client;
    static Logger logger = Logger.getLogger("LineMessagingClientManager");
    private static final String confLoc="ynu_cat_bot.properties";

    public static LineMessagingClient getClient() {
        if (client == null) {
            Properties setting = new Properties();
            File file=new File(confLoc);
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //read setting
            try (FileInputStream in = new FileInputStream(confLoc)) {
                setting.load(in);
            } catch (IOException e) {
                logger.severe("ERROR: INCORRECT CONFIGURATION IN PROPERTIES");
                e.printStackTrace();
            }
            String token = setting.getProperty("LineChannelToken");
            if (token == null) {
                System.out.println("TOKEN NOT FOUND");
                System.out.print("Input your token... :");
                Scanner scanner = new Scanner(System.in);
                token = scanner.nextLine();
                System.out.println("your token is set to... " + token);
                setting.put("LineChannelToken", token);
                try(FileOutputStream stream=new FileOutputStream(confLoc)) {
                    setting.store(stream,null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            client = LineMessagingClient.builder(setting.getProperty("LineChannelToken")).build();
        }
        return client;
    }
}
