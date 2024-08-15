package com.QRMenu.menu.mapper;

import com.QRMenu.menu.dto.NotificationDto;
import com.QRMenu.menu.entity.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationMapper {

    public static NotificationDto convert(Notification notification) {

        return NotificationDto.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .content(notification.getContent())
                .createdAt(notification.getCreatedAt())
                .build();
    }

    public static List<NotificationDto> convertList(List<Notification> notificationList) {
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for (Notification notification : notificationList) {
            notificationDtoList.add(convert(notification));
        }
        return notificationDtoList;
    }
}
