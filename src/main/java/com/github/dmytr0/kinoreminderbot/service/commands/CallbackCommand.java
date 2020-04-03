package com.github.dmytr0.kinoreminderbot.service.commands;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackCommand {

    void process(Update message);
}
