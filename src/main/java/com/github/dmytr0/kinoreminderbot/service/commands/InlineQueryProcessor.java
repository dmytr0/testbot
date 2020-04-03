package com.github.dmytr0.kinoreminderbot.service.commands;

import com.github.dmytr0.kinoreminderbot.service.TelegramSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultVenue;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.cached.InlineQueryResultCachedSticker;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class InlineQueryProcessor {

    private final TelegramHelper telegramHelper;
    private final TelegramSender telegramSender;


    public void process(Update updateMessage) {
        InlineQuery inlineQuery = updateMessage.getInlineQuery();
        String query = inlineQuery.getQuery();

        AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
        answerInlineQuery.setInlineQueryId(inlineQuery.getId());
        answerInlineQuery.setCacheTime(400);

//        InlineQueryResultArticle article = getArticle();

        InlineQueryResultVenue venue = getVenue();

        answerInlineQuery.setResults(List.of(venue));

        telegramSender.send(answerInlineQuery);

    }

    private InlineQueryResultArticle getArticle() {
        InlineQueryResultArticle result = new InlineQueryResultArticle();
        return result;
    }

    private InlineQueryResultVenue getVenue() {
        InlineQueryResultVenue result = new InlineQueryResultVenue();
        result.setLatitude(50.193647F);
        result.setLongitude(30.311284F);
        result.setTitle("title");
        result.setAddress("adresss");
        return result;
    }

    private InlineQueryResultCachedSticker getSticker() {
        InlineQueryResultCachedSticker result = new InlineQueryResultCachedSticker();
        return result;
    }


}
