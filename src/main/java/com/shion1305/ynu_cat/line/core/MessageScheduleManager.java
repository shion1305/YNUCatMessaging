package com.shion1305.ynu_cat.line.core;

import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexGravity;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.response.BotApiResponse;
import com.shion1305.ynu_cat.line.core.message.MessageJob;
import com.shion1305.ynu_cat.line.core.message.MessageSchedule;
import com.shion1305.ynu_cat.line.core.message.MessageType;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class MessageScheduleManager {
    static List<MessageJob> jobs = new ArrayList<>();

    public static void schedule(MessageSchedule schedule) {
        final Message mes;
        if (schedule.MessageType == MessageType.CUSTOM) mes = schedule.customMessage;
        else mes = getMessage(schedule.MessageType);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    BotApiResponse resp = LineMessagingClientManager.getClient()
                            .pushMessage(new PushMessage(schedule.member.userId, mes)).get();
                    DeliveryLogger.log(resp, true);
                } catch (InterruptedException | ExecutionException e) {
                    DeliveryLogger.logError(e, true);
                }
            }
        };
        TimerManager.schedule(task, schedule.date);
        MessageJob job = new MessageJob(task, UUID.randomUUID(), schedule);
        jobs.add(job);
    }

    private static Message getMessage(int type) {
        switch (type) {
            case MessageType.ROUTINE_NOTIFICATION:
                try {
                    URI uri = new URI("https://lh3.googleusercontent.com/nXIbLSL0vr73g9Nsw_ZhNODGbKQ8AnQGhNLn0ldXjY_zVPZnk4nrGdluTBXKOKcgGipm0z9sjsD2vySc6wWkRRtnjUXH7VhJJPkoSJ4JFEQ-6npicjEup0L5ZWjBof8ddpLIiFNUsavQGVqkgInNi8COHnnXxJigFJwQ5fs5BhB4laiQkA31bsh6CUxUB97ODziDH3cmnjw4g8xUWEzmyeDbkXDq5K0q0l1m4ba0QntYJGvACa_B-HMhZ8s5o256fj-woASaUccvZQGZeiQQlZAMmFk90sP-xyQ9P5_E3pzhtsK8KpwWKY3CTzsWEgz2iGGj8YcmSF_zIaeWTaNdt07GtQlyv8drPdfqcSi696wMmhccW9_qapspSOx-mqXBSHuAdAz5Obr8-ywm9pEwMWe8G3ZM7Sck0U3ASndLGXrgIt8MmbiIu4tFxiXYRjAnA_x4A85Rgtb0JIEfiSXB8-6SnlppIdm7qYSM5QnG0FbUKDcXIwCzK7DyAmrb__QCZ2xTXk1Gqt_tD_-M_DK6fpXKdZKDTbqJD-oIvde-4fqRtX9_AFzOX178Y1yHqxdeqzwuIO0BI5uibWXjfpiScfKZWbVSjl3nowHS1gmUEBT9d6XaBPwGG-4Y_PKNfupEc5cAqa1DYkWh0FE7v3qIwNzMJpIy5ES7LcSx3d9HpmloE4gx3LzcT_bDd3iBaz_KSeyebTq5W4H4vAom4eHp9KR1=w1809-h1358-no?authuser=0");
                    Bubble bubble = Bubble.builder().hero(Image.builder().url(uri).size("full").aspectRatio(4, 3).aspectMode(Image.ImageAspectMode.Cover).build())
                            .body(Box.builder().layout(FlexLayout.VERTICAL).contents(Text.builder().text("本日当番のお知らせ").gravity(FlexGravity.CENTER).weight(Text.TextWeight.BOLD).size(FlexFontSize.XL).align(FlexAlign.CENTER).color("#FFFFFF").build()).backgroundColor("#5AC2D9").build()).build();
                    return FlexMessage.builder().contents(bubble).altText("猫サークル 当番のお知らせ").build();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            default:
                return null;
        }
    }
}
