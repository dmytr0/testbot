package com.github.dmytr0.kinoreminderbot.clients;

import com.github.dmytr0.kinoreminderbot.dto.SendPhoto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@FeignClient(value = "telegram", url = "https://api.telegram.org/")
public interface TelegramClient {

    /*
     * GET https://api.telegram.org/bot{my_bot_token}/setWebhook?url={url_to_send_updates_to}
     */
    @RequestMapping(method = RequestMethod.GET, value = "/bot{token}/setWebhook")
    String setWebhook(@RequestParam("url") String webhookUrl, @PathVariable("token") String token);

    @RequestMapping(method = RequestMethod.POST, value = "/bot{token}/sendMessage")
    String sendMessage(@RequestBody SendMessage message, @PathVariable("token") String token);

    @RequestMapping(method = RequestMethod.POST, value = "/bot{token}/sendPhoto")
    String sendPhoto(@RequestBody SendPhoto photo, @PathVariable("token") String token);

    @RequestMapping(method = RequestMethod.POST, value = "/bot{token}/editMessageReplyMarkup")
    String editReplyMarkup(@RequestBody EditMessageReplyMarkup editReplyMarkup, @PathVariable("token") String token);

    @RequestMapping(method = RequestMethod.POST, value = "/bot{token}/editMessageText")
    String editMessageText(@RequestBody EditMessageText editMessageText, @PathVariable("token") String token);

    @RequestMapping(method = RequestMethod.POST, value = "/bot{token}/deleteMessage")
    String deleteMessage(@RequestBody DeleteMessage editReplyMarkup, @PathVariable("token") String token);

    @RequestMapping(method = RequestMethod.POST, value = "/bot{token}/answerInlineQuery")
    String answerInlineQuery(@RequestBody AnswerInlineQuery answerInlineQuery, @PathVariable("token") String token);

    @RequestMapping(method = RequestMethod.POST, value = "/bot{token}/{path}}")
    String send(@RequestBody BotApiMethod message,
                @PathVariable("token") String token,
                @PathVariable("path") String path);
}
