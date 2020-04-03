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
import org.telegram.telegrambots.meta.api.objects.Message;
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
    public void process(Update updateMessage) {
        User user = null;
        if (updateMessage.hasCallbackQuery()) {
            user = updateMessage.getCallbackQuery().getFrom();
            String chatId = String.valueOf(updateMessage.getCallbackQuery().getMessage().getChatId());
            String payload = updateMessage.getCallbackQuery().getData();
            log.debug("chat: {}, user {},  payload: {}", chatId, user, payload);
            Payload payloadJson = parseObjectJson(payload, Payload.class);
            CallbackCommand callbackCommand = callbackCommands.get(payloadJson.getAction());
            if (callbackCommand != null) {
                callbackCommand.process(updateMessage);
                return;
            }

        } else if (updateMessage.hasMessage()) {
            Message message = updateMessage.getMessage();
            if (message.hasText()) {
                user = message.getFrom();
                String chatId = String.valueOf(message.getChatId());
                String textMessage = message.getText();
                log.debug("chat: {}, user: {}, text message {}", chatId, user, textMessage);
                TextCommand textCommand = ofNullable(textCommands.get(textMessage))
                        .orElseGet(() -> textCommands.get(DEFAULT));

                if (textCommand != null) {
                    textCommand.process(updateMessage);
                } else {
                    // skip unknown text and default command absent
                }
            }
            if(message.hasContact()) {
                message.getContact();
            }
        }




        log.debug("USER: " + user + " MESSAGE " + updateMessage);
        ofNullable(user).ifPresent(userService::saveSubscriber);
    }

}
