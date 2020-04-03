package com.github.dmytr0.kinoreminderbot.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Texts {

    public static final String ERROR_TEXT = "🙇‍♂️ Ой, щось пішло не так ...!";
    public static final String SEARCH_TEXT = "🔎 Шукати";
    public static final String DETAIL_TEXT = "📜 Детальніше";
    //    public static final String DETAIL_EMOJI = "\uD83D\uDCDC";
    public static final String WATCHED_TEXT = "☑️ Переглянуто";
    public static final String WATCHED_DONE_TEXT = "✅️ Переглянуто";
    public static final String DELETED_DONE_TEXT = "❌️ Видалено успішно";
    public static final String SAVED_MOVIE = "✅ Збережено \"{0}\"";
    public static final String GREETINGS = "Вітаю!\nБуває чуєш/бачиш опис якогось фільму який хочеш згодом подивитись, а потім геть забуваєш про нього, або його назву." +
            "\nТоді цей бот для тебе! Відправ йому назву фільму, він збереже її для тебе, і коли в тебе знайдеться вільна годинка для кіно - запитай в бота що подивитись \uD83D\uDC40";

    public static final String WATCHED_MOVIE_MESSAGE =
            "✅ <b>{0}</b>\n" +
                    "<i>⏱ Переглянуто: {1}</i>";

    public static final String DEFAULT_MOVIE_MESSAGE =
            "\uD83C\uDFAC <b>{0}</b>" +
                    "\n\n" +
                    "\uD83D\uDCC5<pre>   </pre><i>{1}</i>";


    public static final String GOOGLE_SEARCH_QUERY = "https://www.google.com/search?q={0}";
    public static final String MOVIES_ABSENT = "{0}, у вас нема такого фільму";
    public static final String NEED_SAVE_MOVIE = "🦊 Зберегти фільм <b>{0}</b>";
    public static final String NEED_SAVE_MOVIE_SIMPLE = "🦊 Зберегти";
    public static final String SAVE_FOUND_MOVIE_OR_ORIGINAL = "Я дещо знайшов.\nВиберіть що зберегти. 👇";

    public static final String MOVIE_EXIST = "\n\n<i>P.S. Схожий фільм було додано раніше</i>";

    public static final String YES = "✅ Так";
    public static final String NO = "❌ Ні";
    public static final String DELETE_BUTTON = "❌ Видалити";

    public static final String ADD_EXTERNAL_MOVIE = "📽 {0} {1}";
    public static final String ADD_ORIGINAL_MOVIE = "😈 Зберегти мій варіант";

    public static final String ACTUAL_MOVIES_ABSENT = "😫 Наразі нема жодного збереженого фільму для перегляду. При нагоді додавайте цікаві фільми \uD83E\uDD17";


    public static final String PREFER = "Чому віддаєте перевагу?";
    public static final String GET_LUCKY_MOVIE = "🍿 Фільм";
    public static final String GET_LUCKY_SERIAL = "📺 Серіал";

    public static final String MAIN_MENU = "📲 Повернутись";
    public static final String DONATE_TEXT = "Виберіть один з варіантів подяки";
    public static final String DONATE_ONETIME = "Разова";
    public static final String DONATE_REGULAR = "Постійна";

}
