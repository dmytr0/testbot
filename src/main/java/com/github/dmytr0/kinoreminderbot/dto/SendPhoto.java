package com.github.dmytr0.kinoreminderbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.util.Optional;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendPhoto extends PartialBotApiMethod<Message> {

    public static final String HTML = "HTML";
    public static final String PATH = "sendphoto";

    @JsonProperty("chat_id")
    private String chatId;
    private String photo;
    private String caption;

    @JsonProperty("disable_notification")
    private Boolean disableNotification;
    @JsonProperty("reply_to_message_id")
    private Integer replyToMessageId;
    @JsonProperty("reply_markup")
    private ReplyKeyboard replyMarkup;
    @JsonProperty("parse_mode")
    private String parseMode;

    public void disableNotification() {
        this.disableNotification = true;
    }

    public SendPhoto(String chatId, String photo, String caption) {
        this.chatId = chatId;
        this.photo = photo;
        this.disableNotification = true;
        this.parseMode = HTML;
        Optional.ofNullable(caption).ifPresent(this::setCaption);
    }

    @Override
    public void validate() throws TelegramApiValidationException {
        org.telegram.telegrambots.meta.api.methods.send.SendPhoto method = new org.telegram.telegrambots.meta.api.methods.send.SendPhoto();
        BeanUtils.copyProperties(this, method, "photo");
        if (chatId == null) {
            throw new TelegramApiValidationException("ChatId parameter can't be empty", method);
        }

        if (photo == null) {
            throw new TelegramApiValidationException("Photo parameter can't be empty", method);
        }

        if (caption != null && caption.length() > 1023) {
            throw new TelegramApiValidationException("Caption length more than 1023", method);
        }

        if (replyMarkup != null) {
            replyMarkup.validate();
        }
    }

    @Override
    public Message deserializeResponse(String answer) throws TelegramApiRequestException {
        return null;
    }
}
