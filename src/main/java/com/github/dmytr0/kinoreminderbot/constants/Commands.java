package com.github.dmytr0.kinoreminderbot.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Commands {

    //callback
    public static final String WATCHED = "WATCHED";
    public static final String ADD_MOVIE = "ADD_MOVIE";
    public static final String DETAIL = "DETAIL";
    public static final String DEL = "DEL";
    public static final String DELM = "DELM";
    public static final String SKIP_ME = "SKIP_ME";
    public static final String LUCKY_MOVIE = "L_M";
    public static final String LUCKY_SERIAL = "L_S";

    //text
    public static final String DEFAULT = "default";
    public static final String ACTUAL_LIST_BUTTON = "\uD83D\uDC40 Що подивитись?";
    public static final String NOT_ACTUAL_LIST_BUTTON = "\uD83D\uDDD1 Історія переглядів";
    public static final String GET_LUCKY_BUTTON = "🎰 Мені пощастить";
    public static final String TELEGRAM_DONATE_BUTTON = "🍩 Зробити внесок";

    public static final String TELEGRAM_START_COMMAND = "/start";
    public static final String TELEGRAM_ACTUAL_COMMAND = "/actual";
    public static final String TELEGRAM_HISTORY_COMMAND = "/history";
    public static final String TELEGRAM_LUCKY_COMMAND = "/lucky";
    public static final String TELEGRAM_DONATE_COMMAND = "/donate";

}
