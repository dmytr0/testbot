package com.github.dmytr0.kinoreminderbot.repository;

import com.github.dmytr0.kinoreminderbot.domain.TelegramSubscriber;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriberRepository extends MongoRepository<TelegramSubscriber, String> {
}
