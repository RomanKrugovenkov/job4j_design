package ru.job4j.ood.ocp;

import java.util.List;

public class MessageSender {

    private static class SmsMessage {
        public String send() {
            return "message";
        }
    }

    public static void main(String[] args) {
        List<SmsMessage> sm = List.of(new SmsMessage(), new SmsMessage());
        sm.forEach(SmsMessage::send);
    }
}
