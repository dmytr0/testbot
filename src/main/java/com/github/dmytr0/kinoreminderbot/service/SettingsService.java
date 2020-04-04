package com.github.dmytr0.kinoreminderbot.service;

import com.github.dmytr0.kinoreminderbot.domain.Settings;
import com.github.dmytr0.kinoreminderbot.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingsService {

    private final SettingsRepository settingsRepository;

    public Settings get(String name) {
//        settingsRepository.findOne()
        return null;
    }
}
