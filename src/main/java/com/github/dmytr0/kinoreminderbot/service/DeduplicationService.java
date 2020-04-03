package com.github.dmytr0.kinoreminderbot.service;

import com.github.dmytr0.kinoreminderbot.dto.LastUserMessage;
import com.github.dmytr0.kinoreminderbot.service.commands.TelegramHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;

import static com.github.dmytr0.kinoreminderbot.utils.CommonUtils.createLRUMap;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeduplicationService {

    public static final int DEDUPLICATION_THRESHOLD_SECONDS = 2;
    private final TelegramHelper telegramHelper;

    private Map<Integer, LastUserMessage> cache = createLRUMap(100);

    public boolean isNeedToProcess(Update message) {
        try {
            cache.entrySet().forEach(log::debug);
            Integer userId = telegramHelper.getUser(message).getId();
            String text = message.hasCallbackQuery()
                    ? telegramHelper.getPayload(message).getAction()
                    : telegramHelper.getText(message);

            boolean res = Optional.ofNullable(cache.get(userId))
                    .filter(lum -> ChronoUnit.SECONDS.between(lum.getDate(), LocalDateTime.now()) < DEDUPLICATION_THRESHOLD_SECONDS)
                    .filter(lum -> text.equals(lum.getText()))
                    .isEmpty();

            cache.put(userId, new LastUserMessage(userId, LocalDateTime.now(), text));
            return res;
        } catch (Exception e) {
            log.error("Deduplication error " + e.getMessage(), e);
        }
        return true;
    }
}
