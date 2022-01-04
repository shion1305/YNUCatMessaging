package com.shion1305.ynu_cat.line;

import com.linecorp.bot.model.profile.UserProfileResponse;
import com.shion1305.ynu_cat.line.core.LineMessagingClientManager;

import java.util.concurrent.ExecutionException;

public class UserDataCollector {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UserProfileResponse resp=LineMessagingClientManager.getClient().getProfile("").get();
    }
}
