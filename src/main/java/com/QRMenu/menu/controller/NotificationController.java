package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.NotificationDto;
import com.QRMenu.menu.entity.Notification;
import com.QRMenu.menu.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping("create-notification")
    public NotificationDto createNotification(@RequestBody Notification notification) {
        return service.createNotification(notification);
    }

    @GetMapping("all-notifications")
    public List<NotificationDto> getAllNotifications() {
        return service.getAllNotification();
    }
}
