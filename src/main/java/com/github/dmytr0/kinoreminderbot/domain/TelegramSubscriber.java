package com.github.dmytr0.kinoreminderbot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.telegram.telegrambots.meta.api.objects.User;

import javax.persistence.Entity;

@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity(name = "subscribers")
public class TelegramSubscriber {

    private String _id;
    private String firstName;
    private Boolean isBot;
    private String lastName;
    private String userName;
    private String languageCode;

    public TelegramSubscriber(User user) {
        _id = user.getId().toString();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        userName = user.getUserName();
        isBot = user.getBot();
        languageCode = user.getLanguageCode();
    }
}
