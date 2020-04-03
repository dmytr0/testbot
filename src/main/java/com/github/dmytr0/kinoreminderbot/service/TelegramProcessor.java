package com.github.dmytr0.kinoreminderbot.service;

import com.github.dmytr0.kinoreminderbot.dto.Payload;
import com.github.dmytr0.kinoreminderbot.service.commands.CallbackCommand;
import com.github.dmytr0.kinoreminderbot.service.commands.TextCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Map;

import static com.github.dmytr0.kinoreminderbot.constants.Commands.DEFAULT;
import static com.github.dmytr0.kinoreminderbot.utils.JsonParser.parseObjectJson;
import static java.util.Optional.ofNullable;

@Service
@Log4j2
@RequiredArgsConstructor
public class TelegramProcessor {

    @Autowired
    private UserService userService;
    @Qualifier("callbackCommands")
    @Autowired
    private Map<String, CallbackCommand> callbackCommands;
    @Qualifier("textCommands")
    @Autowired
    private Map<String, TextCommand> textCommands;

    @Async("telegramTP")
    public void process(Update message) {
        User user = null;
        if (message.hasCallbackQuery()) {
            user = message.getCallbackQuery().getFrom();
            String chatId = String.valueOf(message.getCallbackQuery().getMessage().getChatId());
            String payload = message.getCallbackQuery().getData();
            log.debug("chat: {}, user {},  payload: {}", chatId, user, payload);
            Payload payloadJson = parseObjectJson(payload, Payload.class);
            CallbackCommand callbackCommand = callbackCommands.get(payloadJson.getAction());
            if (callbackCommand != null) {
                callbackCommand.process(message);
                return;
            }

        } else if (message.hasMessage()) {
            user = message.getMessage().getFrom();
            String chatId = String.valueOf(message.getMessage().getChatId());
            String textMessage = message.getMessage().getText();
            log.debug("chat: {}, user: {}, text message {}", chatId, user, textMessage);
            TextCommand textCommand = ofNullable(textCommands.get(textMessage))
                    .orElseGet(() -> textCommands.get(DEFAULT));

            if (textCommand != null) {
                textCommand.process(message);
            } else {
                // skip unknown text and default command absent
            }
        }
        log.debug("USER: " + user + " MESSAGE " + message);
        ofNullable(user).ifPresent(userService::saveSubscriber);
    }

}
