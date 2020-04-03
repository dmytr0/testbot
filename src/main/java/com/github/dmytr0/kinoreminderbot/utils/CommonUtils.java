package com.github.dmytr0.kinoreminderbot.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.min;
import static java.util.Optional.ofNullable;

@Log4j2
@UtilityClass
public class CommonUtils {

    public static <T> List<List<T>> splitList(List<T> list, int n) {
        if (n <= 0 || list.isEmpty()) return Collections.emptyList();
        return IntStream
                .iterate(0, i -> i < list.size(), i -> i + n)
                .mapToObj(i -> list.subList(i, min(i + n, list.size())))
                .collect(Collectors.toList());
    }

    public static String clean(@NonNull String unsafe) {
        Whitelist whitelist = Whitelist.none();
//        whitelist.addTags("i", "b", "pre");

        unsafe = unsafe.replaceAll("&nbsp;", " ");
        return Jsoup.clean(unsafe, whitelist);
    }

    public static <K, V> Map<K, V> createLRUMap(final int maxEntries) {
        return new LinkedHashMap<K, V>(maxEntries * 10 / 7, 0.7f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxEntries;
            }


            @Override
            public boolean containsKey(Object key) {
                return get(key) != null;
            }
        };
    }

    public static String uniqKey(@Nullable String year,
                                 @Nullable String origTitle) {

        return Objects.toString(year, "") +
                ofNullable(origTitle).orElse("").toLowerCase();
    }

}
