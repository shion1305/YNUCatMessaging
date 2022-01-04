package com.shion1305.ynu_cat.line.core.message;

import java.util.TimerTask;
import java.util.UUID;

public class MessageJob {
    TimerTask timerTask;
    UUID id;
    MessageSchedule schedule;

    public MessageJob(TimerTask timerTask, UUID id, MessageSchedule schedule) {
        this.timerTask = timerTask;
        this.id = id;
        this.schedule = schedule;
    }
}
