package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.screening.model.ScreeningDto;

import java.util.List;

public interface ScreeningService {
    void addScreening(ScreeningDto screeningDto);

    void removeScreening(ScreeningDto screeningDto);

    Boolean isThereAnOverlap(ScreeningDto screeningDto, Boolean breakIncluded);

    List<ScreeningDto> getScreeningList();
}
