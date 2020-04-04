package com.github.dmytr0.kinoreminderbot.controllers;

import com.github.dmytr0.kinoreminderbot.domain.Settings;
import com.github.dmytr0.kinoreminderbot.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping("/")
    public Settings get() {
        return settingsService.get(null);
    }
}
