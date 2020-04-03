package com.github.dmytr0.kinoreminderbot.service;

import com.github.dmytr0.kinoreminderbot.domain.TelegramSubscriber;
import com.github.dmytr0.kinoreminderbot.repository.SubscriberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final SubscriberRepository subscriberRepository;

    public void saveSubscriber(@NonNull User user) {
        try {
            TelegramSubscriber telegramSubscriber = new TelegramSubscriber(user);

            subscriberRepository.findById(telegramSubscriber.get_id())
                    .orElseGet(() -> subscriberRepository.save(telegramSubscriber));
        } catch (Exception e) {
            log.error("Error saving subscriber " + e.getMessage(), e);
        }
    }
}
