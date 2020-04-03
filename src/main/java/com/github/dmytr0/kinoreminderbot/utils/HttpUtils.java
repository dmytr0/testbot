package com.github.dmytr0.kinoreminderbot.utils;

import feign.FeignException;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;
import java.util.function.Supplier;

@Log4j2
@UtilityClass
public class HttpUtils {

    public static <T> Optional<T> callHttp(Supplier<T> s) {
        try {
            T response = s.get();
            log.debug("RESPONSE: " + response);
            return Optional.ofNullable(response);
        } catch (FeignException.BadRequest e) {
            log.error("BAD REQUEST message {}; content: {}", e.getMessage(), e.contentUTF8());
        } catch (FeignException e) {
            log.error("FAILED REQUEST " + e.getMessage(), e);
        }
        return Optional.empty();
    }
}
