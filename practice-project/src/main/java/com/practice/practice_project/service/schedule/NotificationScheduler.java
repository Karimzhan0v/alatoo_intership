package com.practice.practice_project.service.schedule;

import com.practice.practice_project.dto.request.NotificationRequest;
import com.practice.practice_project.model.ClientEntity;
import com.practice.practice_project.model.ClientRepository;
import com.practice.practice_project.model.MovieEntity;
import com.practice.practice_project.model.MovieRepository;
import com.practice.practice_project.service.feign.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationScheduler {
    private final NotificationService notificationClient;
    private final MovieRepository movieRepository;
    private final ClientRepository clientRepository;

    @Scheduled(cron = "0 * * * * *")
    public void sendNotification() {
        List<MovieEntity> entitiesWithoutRate =
                movieRepository.findByRateIsNull();
        if (entitiesWithoutRate.isEmpty()) {
            log.info("NotificationScheduler.sendNotification() has not found movies without rate today!");
            return;
        }
        List<Long> clientIds = new ArrayList<>();
        Map<Long, String> clientIdToMovieMap = new HashMap<>();
        for (MovieEntity entity : entitiesWithoutRate) {
            ClientEntity client = entity.getClient();
            if (client != null) {
                clientIds.add(client.getId());
                clientIdToMovieMap.put(client.getId(), entity.getName());
            }
        }
        if (clientIds.isEmpty()) {
            log.info("No clients are linked to movies without rate.");
            return;
        }
        List<ClientEntity> clients = clientRepository.findAllById(clientIds);
        if (clients.isEmpty()) {
            log.info("No clients found by their IDs.");
            return;
        }
        List<NotificationRequest> notificationRequests = clients.stream().map(client -> {
            String phoneNumber = client.getPhoneNumber();
            Long clientId = client.getId();
            String movieName = clientIdToMovieMap.get(clientId);

            return new NotificationRequest(
                    phoneNumber,
                    String.format("Не желаете поставить оценку недавно просмотренному фильму %s", movieName)
            );
        }).toList();
        notificationRequests.forEach(notificationClient::sendNotification);
    }
}
