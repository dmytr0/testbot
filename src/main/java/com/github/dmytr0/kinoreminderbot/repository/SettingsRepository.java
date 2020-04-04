package com.github.dmytr0.kinoreminderbot.repository;

import com.github.dmytr0.kinoreminderbot.domain.Settings;
import com.github.dmytr0.kinoreminderbot.domain.TelegramSubscriber;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettingsRepository extends MongoRepository<Settings, String> {
}
