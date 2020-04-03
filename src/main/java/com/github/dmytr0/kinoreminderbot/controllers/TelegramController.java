package com.github.dmytr0.kinoreminderbot.controllers;

import com.github.dmytr0.kinoreminderbot.service.DeduplicationService;
import com.github.dmytr0.kinoreminderbot.service.TelegramProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.dmytr0.kinoreminderbot.utils.JsonParser.prepareObjectJson;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin("*")
@RequestMapping("messenger/telegram")
@Log4j2
@RequiredArgsConstructor
public class TelegramController {

    private final TelegramProcessor telegramProcessor;
    private final DeduplicationService deduplicationService;

    //kino-reminder.herokuapp.com/messenger/telegram/webhook
    @RequestMapping("/webhook")
    @ResponseStatus(OK)
    public void messageReceiver(@RequestBody Update message) {
//        if (deduplicationService.isNeedToProcess(message)) {
            log.debug("Message received:\n" + prepareObjectJson(message));
            telegramProcessor.process(message);
//        } else {
//            log.debug("Message DUPLICATE SKIPPED:\n" + prepareObjectJson(message));
//        }
    }
}
