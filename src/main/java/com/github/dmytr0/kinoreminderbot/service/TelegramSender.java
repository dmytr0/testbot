package com.github.dmytr0.kinoreminderbot.service;

import com.github.dmytr0.kinoreminderbot.clients.TelegramClient;
import com.github.dmytr0.kinoreminderbot.dto.SendPhoto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Optional;

import static com.github.dmytr0.kinoreminderbot.utils.HttpUtils.callHttp;
import static com.github.dmytr0.kinoreminderbot.utils.TelegramUtils.editTextMessage;
import static com.github.dmytr0.kinoreminderbot.utils.TelegramUtils.textMessage;

@Log4j2
@Component
@RequiredArgsConstructor
public class TelegramSender {

    @Value("${telegram.token}")
    private String token;
    @Value("${heroku.app.name:}")
    private String appName;

    private final TelegramClient telegramClient;

    public void sendMessage(String recipientId, String text) {
        sendMessage(textMessage(recipientId, text));
    }

    public void editReplyMarkup(String chatId, Integer messageId, InlineKeyboardMarkup replyMarkup) {
        EditMessageReplyMarkup editReplyMarkup = new EditMessageReplyMarkup();
        editReplyMarkup.setChatId(chatId);
        editReplyMarkup.setMessageId(messageId);
        editReplyMarkup.setReplyMarkup(replyMarkup);

        editReplyMarkup(editReplyMarkup);
    }

    public void deleteMessage(String chatId, Integer messageId) {
        deleteMessage(new DeleteMessage(chatId, messageId));
    }

    public void sendMessage(String recipientId, String text, ReplyKeyboard keyboard) {
        sendMessage(textMessage(recipientId, text, keyboard));
    }

    public void sendEditTextMessage(String chatId, Integer messageId, String text, InlineKeyboardMarkup keyboard) {
        EditMessageText editMessageText = editTextMessage(chatId, messageId, text, keyboard);
        editMessageText(editMessageText);
    }

    public void sendMessage(PartialBotApiMethod<Message> message) {
        if (message instanceof SendPhoto) {
            sendPhoto((SendPhoto) message);
        } else if (message instanceof SendMessage) {
            sendMessage((SendMessage) message);
        } else if (message instanceof BotApiMethod) {
            send((BotApiMethod) message);
        } else {
            throw new UnsupportedOperationException("Message type not supported to send " + message);
        }
    }

    public void sendMessage(SendMessage message) {
        callHttp(() -> telegramClient.sendMessage(message, token));
    }

    public void send(BotApiMethod message) {
        callHttp(() -> telegramClient.send(message, token, message.getMethod()));
    }

    public void sendPhoto(SendPhoto photo) {
        callHttp(() -> telegramClient.sendPhoto(photo, token));
    }

    public void answerInlineQuery(AnswerInlineQuery answer) {
        callHttp(() -> telegramClient.answerInlineQuery(answer, token));
    }

    public void setWebhook(@Nullable String host) {
        String webhookUrl = Optional.ofNullable(host).orElseGet(() -> appName + ".herokuapp.com") + "/messenger/telegram/webhook";
        log.info("webhookUrl [{}] will be setup", webhookUrl);
        callHttp(() -> telegramClient.setWebhook(webhookUrl, token));
    }

    public void editMessageText(EditMessageText editMessageText) {
        callHttp(() -> telegramClient.editMessageText(editMessageText, token));
    }

    public void editReplyMarkup(EditMessageReplyMarkup editReplyMarkup) {
        callHttp(() -> telegramClient.editReplyMarkup(editReplyMarkup, token));
    }

    public void deleteMessage(DeleteMessage deleteMessage) {
        callHttp(() -> telegramClient.deleteMessage(deleteMessage, token));
    }
}
