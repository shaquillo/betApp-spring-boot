package com.kindredgroup.unibetlivetest.batchs;

import com.kindredgroup.unibetlivetest.service.SelectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class SelectionBatch {


    private final SelectionService selectionService;

    @Scheduled(fixedRate = 5000)
    public void updateOddsRandomly() {
        log.info("{} selection(s) updated randomly.", selectionService.updateOddsRandomly());
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void closeOddsRandomly() {
        log.info("{} selections(s) closed randomly.", selectionService.closeOddsRandomly());
    }

}
