package com.QRMenu.menu.service;

import com.QRMenu.menu.dto.NotificationDto;
import com.QRMenu.menu.entity.Notification;

import java.util.List;

public interface NotificationService {

    NotificationDto createNotification(Notification notification);

    List<NotificationDto> getAllNotification();
}
