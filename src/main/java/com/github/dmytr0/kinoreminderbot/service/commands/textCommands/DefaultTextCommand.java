package com.github.dmytr0.kinoreminderbot.service.commands.textCommands;

import com.github.dmytr0.kinoreminderbot.annotations.TextCommands;
import com.github.dmytr0.kinoreminderbot.constants.Commands;
import com.github.dmytr0.kinoreminderbot.service.TelegramSender;
import com.github.dmytr0.kinoreminderbot.service.commands.TelegramHelper;
import com.github.dmytr0.kinoreminderbot.service.commands.TextCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Log4j2
@TextCommands(targets = Commands.DEFAULT)
@RequiredArgsConstructor
public class DefaultTextCommand implements TextCommand {

    private final TelegramHelper telegramHelper;
    private final TelegramSender telegramSender;

    @Override
    public void process(Update message) {
        String chatId = telegramHelper.getChatId(message);
        String text = telegramHelper.getText(message);
        User user = telegramHelper.getUser(message);
    }

}
