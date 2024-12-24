package com.practice.practice_project.service.feign;

import com.practice.practice_project.dto.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationClient notificationClient;

    public void sendNotification(NotificationRequest request){
        try {
            notificationClient.sendNotification(request);
        } catch (Exception e){
            log.warn("Error was occurred while sending notification to client {}", request.getPhoneNumber());
        }
    }
}
