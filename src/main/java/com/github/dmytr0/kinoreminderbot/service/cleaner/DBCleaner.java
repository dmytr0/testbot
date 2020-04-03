package com.github.dmytr0.kinoreminderbot.service.cleaner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DBCleaner {

    @Value("${keep.tmp.movies.days:2}")
    public int daysToKeepTmp;

    public void cleanup() {
        try {

        } catch (Exception e) {
            log.warn("Cleanup failed with error " + e.getMessage(), e);
        }
    }


}
