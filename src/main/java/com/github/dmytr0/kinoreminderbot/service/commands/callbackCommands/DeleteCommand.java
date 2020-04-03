package com.github.dmytr0.kinoreminderbot.service.commands.callbackCommands;

import com.github.dmytr0.kinoreminderbot.annotations.CallbackCommands;
import com.github.dmytr0.kinoreminderbot.dto.Payload;
import com.github.dmytr0.kinoreminderbot.service.TelegramSender;
import com.github.dmytr0.kinoreminderbot.service.commands.CallbackCommand;
import com.github.dmytr0.kinoreminderbot.service.commands.TelegramHelper;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static com.github.dmytr0.kinoreminderbot.constants.Commands.DEL;

@CallbackCommands(targets = DEL)
@RequiredArgsConstructor
public class DeleteCommand implements CallbackCommand {

    private final TelegramHelper telegramHelper;
    private final TelegramSender telegramSender;

    @Override
    public void process(Update message) {
        User user = telegramHelper.getUser(message);
        Payload payload = telegramHelper.getPayload(message);
        String chatId = telegramHelper.getChatId(message);
        Integer messageId = telegramHelper.getMessageId(message);
    }

}
