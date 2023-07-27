package com.kindredgroup.unibetlivetest.service;

import org.springframework.stereotype.Service;

import com.kindredgroup.unibetlivetest.repository.BetRepository;
import com.kindredgroup.unibetlivetest.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import com.kindredgroup.unibetlivetest.types.BetState;
import com.kindredgroup.unibetlivetest.types.SelectionState;

@Log4j2
@RequiredArgsConstructor
@Service
public class MarketService {
    
    private final BetRepository betRepository;
    private final CustomerRepository customerRepository;

    /*
     * 1. Récupère les mises gagnantes et dont la sélection est ferme
     * 2. Mis à jour du montant que possède le client
     */
    public Long payCustomers(){
        return betRepository.getBetByBetStateEqualsAndSelection_StateEquals(BetState.WON, SelectionState.CLOSED)
                    .stream()
                    .map(bet -> bet.getCustomer().setBalance(bet.getCustomer().getBalance().add(bet.getSelection().getCurrentOdd().multiply(bet.getAmount()))))
                    .map(customerRepository::save)
                    .count();
    }
}
