package com.shion1305.ynu_cat.message;

import com.linecorp.bot.model.message.Message;
import com.shion1305.ynu_cat.Member;

import java.util.Date;

public class MessageSchedule {
    public int MessageType;
    public Date date;
    public Member member;
    public Message customMessage;

    public MessageSchedule(int messageType, Date date, Member member, Message customMessage) {
        MessageType = messageType;
        this.date = date;
        this.member = member;
        this.customMessage = customMessage;
    }
}