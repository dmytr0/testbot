package com.github.dmytr0.kinoreminderbot.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.github.dmytr0.kinoreminderbot.constants.Commands.ADD_MOVIE;
import static com.github.dmytr0.kinoreminderbot.constants.Commands.DETAIL;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payload {

    @JsonProperty("a")
    private String action;
    @JsonProperty("mid")
    private String movieId;
    @JsonProperty("tid")
    private String tmpMovieId;
    @JsonProperty("m")
    private String movie;
    @JsonProperty("eid")
    private Long externalId;
    @JsonProperty("ms")
    private MovieSource movieSource;
    @JsonProperty("ns")
    private Integer needSave;

    public Payload(String action) {
        this.action = action;
    }

    public static Payload addMovieByTmpId(String tmpMovieId) {
        Payload payload = new Payload();
        payload.action = ADD_MOVIE;
        payload.tmpMovieId = tmpMovieId;
        return payload;
    }

    public static Payload addMovieByMovieId(String movieId) {
        Payload payload = new Payload();
        payload.action = ADD_MOVIE;
        payload.movieId = movieId;
        return payload;
    }

    public static Payload detailMovie(String movieId, boolean needSave) {
        return Payload.builder()
                .action(DETAIL)
                .movieId(movieId)
                .needSave(needSave ? 1 : 0)
                .build();
    }

}
