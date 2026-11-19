package com.trunghau.rabbitmqapp.controller;

import com.trunghau.rabbitmqapp.entity.Notification;
import com.trunghau.rabbitmqapp.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @GetMapping("/getallnotification")
    public ResponseEntity<List<Notification>> getAllNotification() {
        List<Notification> listOfNotification = notificationRepository.findAll();
        return ResponseEntity.ok(listOfNotification);
    }

    @GetMapping("/getnotificationsbyrecipient")
    public ResponseEntity<List<Notification>> getNotificationsByRecipient(String recipient) {
        List<Notification> notificationsByRecipient = notificationRepository.findByRecipientOrderByCreatedAtDesc(recipient);
        return ResponseEntity.ok(notificationsByRecipient);
    }
}