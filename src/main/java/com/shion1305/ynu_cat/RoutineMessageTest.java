package com.shion1305.ynu_cat;

import com.shion1305.ynu_cat.message.MessageSchedule;
import com.shion1305.ynu_cat.message.MessageType;

import java.util.Calendar;

public class RoutineMessageTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("STARTED");
        calendar.add(Calendar.SECOND, 5);
        calendar.add(Calendar.SECOND, 1);
        Member member = new Member("Ua54a5505fcbf303b59a856c6bbec5518");
        MessageSchedule mes = new MessageSchedule(MessageType.ROUTINE_NOTIFICATION, calendar.getTime(), member, null);
        MessageScheduleManager.schedule(mes);
    }
}
