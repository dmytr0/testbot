package com.github.dmytr0.kinoreminderbot.dto.ivi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class IviMovieDto {

    private Long id;
    private String title;
    @JsonProperty("orig_title")
    private String origTitle;
    private String description;
    private String synopsis;
    @JsonProperty("imdb_rating")
    private String imdbRating;
    @JsonProperty("ivi_rating_10")
    private String iviRating;
    @JsonProperty("kp_rating")
    private String kpRating;
    private String duration;
    private String year;
    @JsonProperty("poster_originals")
    private List<IviPosterOriginal> posterOriginals;
    private String country;
    private IviKind kind;
}
