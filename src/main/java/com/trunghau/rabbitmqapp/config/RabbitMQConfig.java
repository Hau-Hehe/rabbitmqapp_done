package com.trunghau.rabbitmqapp.config;

public class RabbitMQConfig {
    //Exchange
    public static final String APP_EXCHANGE = "app.exchange";

    //Routing keys
    public static final String EMAIL_NOTIFICATION_ROUTING_KEY = "notification.email";
    public static final String SMS_NOTIFICATION_ROUTING_KEY = "notification.sms";

    //Queues
    public static final String EMAIL_QUEUE = "email.queue";
    public static final String SMS_QUEUE = "sms.queue";
}
