package com.github.dmytr0.kinoreminderbot.service.commands;

import com.github.dmytr0.kinoreminderbot.dto.Payload;
import com.github.dmytr0.kinoreminderbot.service.TelegramSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.github.dmytr0.kinoreminderbot.constants.Commands.SKIP_ME;
import static com.github.dmytr0.kinoreminderbot.constants.PrivilegedUsers.DIM;
import static com.github.dmytr0.kinoreminderbot.constants.PrivilegedUsers.LEN;
import static com.github.dmytr0.kinoreminderbot.constants.Texts.GOOGLE_SEARCH_QUERY;
import static com.github.dmytr0.kinoreminderbot.utils.JsonParser.parseObjectJson;
import static com.github.dmytr0.kinoreminderbot.utils.TelegramUtils.createPayloadButton;
import static com.github.dmytr0.kinoreminderbot.utils.TelegramUtils.getHorizontalKeyboard;
import static java.text.MessageFormat.format;
import static java.util.Collections.singletonList;

@Component
@RequiredArgsConstructor
public class TelegramHelper {

    private final static List<String> family = List.of(DIM, LEN);

    private final TelegramSender telegramSender;
    private final ScheduledExecutorService scheduledExecutor;


    public List<String> getGroup(User user) {
        String userId = user.getId().toString();
        List<String> ids;
        if (family.contains(userId)) {
            ids = family;
        } else {
            ids = (singletonList(userId));
        }
        return ids;
    }

    public User getUser(Update message) {
        if (message.hasCallbackQuery()) {
            return message.getCallbackQuery().getFrom();
        }
        return message.getMessage().getFrom();
    }

    public String getChatId(Update message) {
        if (message.hasCallbackQuery()) {
            return String.valueOf(message.getCallbackQuery().getMessage().getChatId());
        }
        return String.valueOf(message.getMessage().getChatId());
    }

    public Payload getPayload(Update message) {
        String payload = message.getCallbackQuery().getData();
        return parseObjectJson(payload, Payload.class);
    }

    public String getText(Update message) {
        return message.getMessage().getText();
    }

    public Integer getMessageId(Update message) {
        if (message.hasCallbackQuery()) {
            return message.getCallbackQuery().getMessage().getMessageId();
        }
        return message.getMessage().getMessageId();
    }

    public void deleteMessage(String chatId, Integer messageId, String deletedDoneText) {
        telegramSender.editReplyMarkup(chatId, messageId, getHorizontalKeyboard(createPayloadButton(deletedDoneText, new Payload(SKIP_ME))));
        schedule(() -> telegramSender.deleteMessage(chatId, messageId));
    }

    public void schedule(Runnable runnable) {
        scheduledExecutor.schedule(runnable, 3, TimeUnit.SECONDS);
    }

    public String getGoogleSearchUrl(String movie) {
        return format(GOOGLE_SEARCH_QUERY, URLEncoder.encode("смотреть онлайн " + movie, StandardCharsets.UTF_8));
    }
}
