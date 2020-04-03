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

        InlineQueryResultVenue venue = getVenue("111");
        InlineQueryResultVenue venue2 = getVenue("222");

        InlineQueryResultCachedSticker sticker = getSticker();
        answerInlineQuery.setResults(List.of(venue, venue2, sticker));

        telegramSender.answerInlineQuery(answerInlineQuery);

    }

    private InlineQueryResultArticle getArticle() {
        InlineQueryResultArticle result = new InlineQueryResultArticle();
        return result;
    }

    private InlineQueryResultVenue getVenue(String id) {
        return new InlineQueryResultVenue()
                .setId(id)
                .setLatitude(50.193647F)
                .setLongitude(30.311284F)
                .setTitle("title")
                .setAddress("adresss")
                .setThumbUrl("https://pngimg.com/uploads/light/light_PNG14440.png");
    }


    private InlineQueryResultCachedSticker getSticker() {
        return new InlineQueryResultCachedSticker()
                .setId("3333")
                .setStickerFileId("AAMCAgADGQEAAwdeh4aJLMTjJQVbyS2TmM7kzPtVugACBwADRDM-CRTMY_GI_goQZL6sDgAEAQAHbQADUFUAAhgE");

    }


}
