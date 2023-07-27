package com.kindredgroup.unibetlivetest.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kindredgroup.unibetlivetest.dto.AddBetDto;
import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.service.BetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(Urls.BASE_PATH)
@CrossOrigin(origins = "*")
public class BetApi {

   private final BetService betService;

    /** TODO
     *  @PostMapping(Urls.ADD_BET)
     */

     @PostMapping(Urls.ADD_BET)
     public Bet addBet(@RequestBody AddBetDto bet){
        return betService.addBet(bet);
     }

}
