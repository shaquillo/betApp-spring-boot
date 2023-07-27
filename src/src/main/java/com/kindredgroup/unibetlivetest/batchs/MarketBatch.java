package com.kindredgroup.unibetlivetest.batchs;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.kindredgroup.unibetlivetest.service.MarketService;

@Component
@Log4j2
@RequiredArgsConstructor
public class MarketBatch {

    private final MarketService marketService;

    private final StopWatch timeMeasure = new StopWatch();

    /**
     * TODO
     *  void payerLesParisBatch()
     */

     @Scheduled(fixedRate = 5000)
     void payerLesParisBatch(){
        timeMeasure.start();
        Long customersPayed = marketService.payCustomers();
        timeMeasure.stop();
        log.info("{} customers payed in {}ms.", customersPayed, timeMeasure.getLastTaskTimeMillis());
     }

}
