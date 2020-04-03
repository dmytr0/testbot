package com.github.dmytr0.kinoreminderbot.dto.ivi;

import lombok.Data;

import java.util.List;

@Data
public class IviSearchDto {

    private List<IviMovieDto> result;
}
