package com.github.dmytr0.kinoreminderbot.utils;

import com.github.dmytr0.kinoreminderbot.dto.Payload;
import com.github.dmytr0.kinoreminderbot.dto.SendPhoto;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.Nullable;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.github.dmytr0.kinoreminderbot.constants.Commands.*;
import static com.github.dmytr0.kinoreminderbot.dto.SendPhoto.HTML;
import static com.github.dmytr0.kinoreminderbot.utils.CommonUtils.splitList;
import static com.github.dmytr0.kinoreminderbot.utils.JsonParser.prepareObjectJson;
import static java.util.stream.Collectors.toList;

@Log4j2
@UtilityClass
public class TelegramUtils {

    public static PartialBotApiMethod<Message> textMessage(String recipientId, String text) {
        return textMessage(recipientId, text, getMainMenu());
    }

    @SneakyThrows
    public static PartialBotApiMethod<Message> textMessage(String chatId, String text, @Nullable ReplyKeyboard keyboard) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(text);
        sm.setParseMode(HTML);
        sm.disableWebPagePreview();
        sm.disableNotification();
        if (keyboard != null) {
            sm.setReplyMarkup(keyboard);
        } else {
            sm.setReplyMarkup(getMainMenu());
        }
        sm.validate();
        return sm;
    }

    @SneakyThrows
    public static EditMessageText editTextMessage(String chatId, Integer messageId, String text, @Nullable InlineKeyboardMarkup keyboard) {
        EditMessageText etm = new EditMessageText();
        etm.setChatId(chatId);
        etm.setMessageId(messageId);
        etm.setText(text);
        etm.setParseMode(HTML);
        etm.disableWebPagePreview();
        if (keyboard != null) {
            etm.setReplyMarkup(keyboard);
        }

        etm.validate();
        return etm;
    }

    @SneakyThrows
    public static PartialBotApiMethod<Message> photoMessage(String chatId, String caption, String imgUrl, @Nullable ReplyKeyboard keyboard) {
        SendPhoto sp = new SendPhoto();
        sp.setChatId(chatId);
        sp.setCaption(caption);
        sp.setParseMode(HTML);
        sp.disableNotification();
        sp.setPhoto(imgUrl);
        if (keyboard != null) {
            sp.setReplyMarkup(keyboard);
        } else {
            sp.setReplyMarkup(getMainMenu());
        }
        log.debug("SEND PHOTO " + sp);
        sp.validate();
        return sp;
    }

    @SneakyThrows
    public static ReplyKeyboardMarkup getMainMenu() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        KeyboardRow row1 = new KeyboardRow();
        row1.add(ACTUAL_LIST_BUTTON);
        row1.add(NOT_ACTUAL_LIST_BUTTON);
        KeyboardRow row2 = new KeyboardRow();
        row2.add(TELEGRAM_DONATE_BUTTON);
        row2.add(GET_LUCKY_BUTTON);
        List<KeyboardRow> rowsList = List.of(row1, row2);
        keyboard.setKeyboard(rowsList);
        keyboard.setResizeKeyboard(true);

        keyboard.validate();
        return keyboard;
    }

    @SneakyThrows
    public static InlineKeyboardButton createPayloadButton(String name, Payload payload) {

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setCallbackData(prepareObjectJson(payload));
        inlineKeyboardButton.setText(name);

        inlineKeyboardButton.validate();
        return inlineKeyboardButton;
    }

    @SneakyThrows
    public static InlineKeyboardButton createUrlButton(String name, String url) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setUrl(url);
        inlineKeyboardButton.setText(name);

        inlineKeyboardButton.validate();
        return inlineKeyboardButton;
    }

    @SneakyThrows
    public static InlineKeyboardMarkup getVerticalKeyboard(List<InlineKeyboardButton> buttons) {
        if (buttons.size() == 0) {
            return null;
        }
        List<List<InlineKeyboardButton>> listButton = buttons.stream().map(Collections::singletonList).collect(toList());              // vertical buttons
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(listButton);

        inlineKeyboardMarkup.validate();
        return inlineKeyboardMarkup;
    }

    @SneakyThrows
    public static InlineKeyboardMarkup getHorizontalKeyboard(InlineKeyboardButton... buttons) {
        if (buttons.length == 0) {
            return null;
        }
        List<List<InlineKeyboardButton>> listButton = Collections.singletonList(Arrays.asList(buttons));                                   // horizontal buttons
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(listButton);

        inlineKeyboardMarkup.validate();
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getKeyboard(int column, InlineKeyboardButton... buttons) {
        if (buttons.length == 0) {
            return null;
        }
        return getKeyboard(column, Arrays.asList(buttons));
    }

    @SneakyThrows
    public static InlineKeyboardMarkup getKeyboard(int column, List<InlineKeyboardButton> buttons) {
        if (buttons.size() == 0) {
            return null;
        }
        buttons = buttons.stream().filter(Objects::nonNull).collect(toList());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> listButton = splitList(buttons, column);
        inlineKeyboardMarkup.setKeyboard(listButton);

        inlineKeyboardMarkup.validate();
        return inlineKeyboardMarkup;
    }
}
