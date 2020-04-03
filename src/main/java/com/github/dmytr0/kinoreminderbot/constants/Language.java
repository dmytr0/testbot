package com.github.dmytr0.kinoreminderbot.constants;

import lombok.Getter;

public enum Language {

    UKRAINIAN("uk"),
    ENGLISH("en"),
    RUSSIAN("ru");

    @Getter
    private final String locale;

    Language(String locale) {
        this.locale = locale;
    }
}
