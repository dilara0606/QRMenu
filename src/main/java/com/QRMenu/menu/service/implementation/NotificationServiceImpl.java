package com.QRMenu.menu.service.implementation;

import com.QRMenu.menu.dto.NotificationDto;
import com.QRMenu.menu.entity.Notification;
import com.QRMenu.menu.mapper.NotificationMapper;
import com.QRMenu.menu.repository.NotificationRepository;
import com.QRMenu.menu.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    @Override
    public NotificationDto createNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        return NotificationMapper.convert(repository.save(notification));
    }

    @Override
    public List<NotificationDto> getAllNotification() {
        return NotificationMapper.convertList(repository.findAll());
    }


}
