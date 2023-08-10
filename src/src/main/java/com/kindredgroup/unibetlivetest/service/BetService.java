package com.kindredgroup.unibetlivetest.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kindredgroup.unibetlivetest.dto.AddBetDto;
import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Customer;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.repository.BetRepository;
import com.kindredgroup.unibetlivetest.repository.CustomerRepository;
import com.kindredgroup.unibetlivetest.repository.SelectionRepository;
import com.kindredgroup.unibetlivetest.types.BetState;
import com.kindredgroup.unibetlivetest.types.ExceptionType;
import com.kindredgroup.unibetlivetest.types.SelectionState;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BetService {
    private final BetRepository betRepository;
    private final SelectionRepository selectionRepository;
    private final CustomerRepository customerRepository;


    /*
     * 1. Vérifie que la mise n'existe pas
     * 2. Vérifie que la sélection choisie est ouverte et que la cote envoyée correspond à celle de la sélection
     * 3. Vérifie que le client a accès d'argent pour la mise
     * 4. Mettre à jour le montant que possède le client
     * 5. Enregistrer la mise
     */

    @Transactional
    public Bet addBet(AddBetDto betDto){

        log.info("BetService: Adding Bet");

        if(betRepository.existsBetByName(betDto.getName())) throw new CustomException(String.format("Bet %s already exist", betDto.getName()), ExceptionType.EXCEPTION_CONFLICT);

        Selection selection = selectionRepository.findById(betDto.getSelectionId()).orElseThrow(() -> new CustomException(String.format("Selection does not exist"), ExceptionType.EXCEPTION_BAD_REQUEST));
        if(selection.getState().equals(SelectionState.CLOSED)) throw new CustomException("Selection closed", ExceptionType.EXCEPTION_NOT_ACCEPTABLES);
        if(selection.getCurrentOdd().compareTo(new BigDecimal(Float.toString(betDto.getOdd()))) != 0) throw new CustomException(String.format("Odd %.2f changed", betDto.getOdd()), ExceptionType.EXCEPTION_NOT_ACCEPTABLES);
        
        Customer customer = customerRepository.findById(betDto.getCustomerId()).orElseThrow(() -> new CustomException(String.format("Customer does not exist"), ExceptionType.EXCEPTION_BAD_REQUEST));
        BigDecimal betTotalAmount = new BigDecimal(Float.toString(betDto.getOdd())).multiply(new BigDecimal(Float.toString(betDto.getAmount())));
        if(customer.getBalance().compareTo(betTotalAmount) < 1) throw new CustomException(String.format("Insufficient balance - Balance : %.2f - Bet price : %.2f", customer.getBalance(), betTotalAmount), ExceptionType.EXCEPTION_NOT_ACCEPTABLES);
        
        Bet bet = new Bet();

        bet.setName(betDto.getName());
        bet.setAmount(new BigDecimal(Float.toString(betDto.getAmount())));
        bet.setSelection(selection);
        bet.setCustomer(customer);

        customer.setBalance(customer.getBalance().subtract(betTotalAmount));
        customerRepository.save(customer);

        bet = betRepository.save(bet);

        log.info("BetService : Bet added, id = %d", bet.getId());

        return bet;
    }
}
