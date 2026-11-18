package com.trunghau.rabbitmqapp.service;

import com.trunghau.rabbitmqapp.config.RabbitMQConfig;
import com.trunghau.rabbitmqapp.entity.Message;
import com.trunghau.rabbitmqapp.entity.Notification;
import com.trunghau.rabbitmqapp.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer {

    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    private final NotificationRepository notificationRepository;

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void handleEmail(Message message) {
        log.info("Email-notification received: Id={}, Recipient={}, subject={}",
                message.getId(), message.getRecipient(), message.getSubject());

        try{
            Thread.sleep(2000);
            Notification notification = Notification.builder()
                    .messageId(message.getId())
                    .type(message.getType())
                    .recipient(message.getRecipient())
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .status("SENT")
                    .processedAt(LocalDateTime.now())
                    .build();
            notificationRepository.save(notification);
            log.info("Email notification message processed and saved with ID: {}", message.getId());
        }catch(Exception e) {
            log.error("Failed to processed the message: {}", e.getMessage(), e);

            Notification notification = Notification.builder()
                    .messageId(message.getId())
                    .type(message.getType())
                    .recipient(message.getRecipient())
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .status("FAILED")
                    .processedAt(LocalDateTime.now())
                    .build();
            notificationRepository.save(notification);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.SMS_QUEUE)
    public void handleSms(Message message) {
        log.info("Email-notification received: Id={}, Recipient={}, Content={}",
                message.getId(), message.getRecipient(), message.getContent());

        try{
            Thread.sleep(500);
            Notification notification = Notification.builder()
                    .messageId(message.getId())
                    .type(message.getType())
                    .recipient(message.getRecipient())
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .status("SENT")
                    .processedAt(LocalDateTime.now())
                    .build();
            notificationRepository.save(notification);
            log.info("SMS notification message processed and saved with ID: {}", message.getId());
        }catch(Exception e) {
            log.error("Failed to processed the message: {}", e.getMessage(), e);

            Notification notification = Notification.builder()
                    .messageId(message.getId())
                    .type(message.getType())
                    .recipient(message.getRecipient())
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .status("FAILED")
                    .processedAt(LocalDateTime.now())
                    .build();
            notificationRepository.save(notification);
        }
    }
}
