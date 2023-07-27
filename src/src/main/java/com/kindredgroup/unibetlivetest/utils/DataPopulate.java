package com.kindredgroup.unibetlivetest.utils;

import com.kindredgroup.unibetlivetest.entity.Customer;
import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.entity.Market;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.repository.CustomerRepository;
import com.kindredgroup.unibetlivetest.repository.EventRepository;
import com.kindredgroup.unibetlivetest.repository.MarketRepository;
import com.kindredgroup.unibetlivetest.repository.SelectionRepository;
import com.kindredgroup.unibetlivetest.types.SelectionState;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;

@Configuration
@Order(0)
@RequiredArgsConstructor
public class DataPopulate {

    private final EventRepository eventRepository;
    private final MarketRepository marketRepository;
    private final SelectionRepository selectionRepository;
    private final CustomerRepository customerRepository;

    @PostConstruct
    public void createRealPsgData() {

        /** 1. On crée l'évenement */
        final Event realMadridVsPsg = eventRepository.save(new Event().setName("Unibet IT vs Real madrid").setStartDate(new Date()));

        /** 2. On crée un marché 'resultat du match' ==> qui va gagner le match (ou match nul) **/
        final Market marketResultatDuMatch = marketRepository.save(new Market().setName("Résultat du match").setEvent(realMadridVsPsg));

        /** Pour ce marché, on crée les résultats possibles (sélections) : real gagne, psg gagne ou match nul */

        selectionRepository.save(new Selection()
                .setName("Unibet IT gagne")
                .setState(SelectionState.OPENED) // Etat ouvert, prêt pour prise de pari
                .setCurrentOdd(new BigDecimal("20.7")) //Cote
                .setMarket(marketResultatDuMatch));
        selectionRepository.save(new Selection()
                .setName("Real madrid gagne")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("1.3"))
                .setMarket(marketResultatDuMatch));
        selectionRepository.save(new Selection()
                .setName("Match Nul")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("4.4"))
                .setMarket(marketResultatDuMatch));


        /** On crée un deuxième marché buteur **/
        final Market marketScorer = marketRepository.save(new Market().setName("Buteur dans le match").setEvent(realMadridVsPsg));

        selectionRepository.save(new Selection()
                .setName("Sabri")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("1.8"))
                .setMarket(marketScorer));
        selectionRepository.save(new Selection()
                .setName("Enzo")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("1.6"))
                .setMarket(marketScorer));
        selectionRepository.save(new Selection()
                .setName("Eric")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("1.5"))
                .setMarket(marketScorer));
        selectionRepository.save(new Selection()
                .setName("Ons")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("2.6"))
                .setMarket(marketScorer));
        selectionRepository.save(new Selection()
                .setName("Ki cheuk")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("25"))
                .setMarket(marketScorer));
        selectionRepository.save(new Selection()
                .setName("K. Benzema")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("1.4"))
                .setMarket(marketScorer));
        selectionRepository.save(new Selection()
                .setName("E. Hazard")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("3.2"))
                .setMarket(marketScorer));
        selectionRepository.save(new Selection()
                .setName("Isco")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("6.4"))
                .setMarket(marketScorer));


        /** allez un dernier pour nos collègues :) qui marquera plus que Messi dans le match parmi nos collègues ?  **/
        final Market scoreMoreThanMessi = marketRepository.save(new Market().setName("Qui va marquer plus que Messi ?").setEvent(realMadridVsPsg));

        selectionRepository.save(new Selection()
                .setName("Guillaume")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("30"))
                .setMarket(scoreMoreThanMessi));
        selectionRepository.save(new Selection()
                .setName("Maxime")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("40"))
                .setMarket(scoreMoreThanMessi));
        selectionRepository.save(new Selection()
                .setName("Thomas")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("50"))
                .setMarket(scoreMoreThanMessi));
        selectionRepository.save(new Selection()
                .setName("Enzo")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("60"))
                .setMarket(scoreMoreThanMessi));
        selectionRepository.save(new Selection()
                .setName("Murat")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("70"))
                .setMarket(scoreMoreThanMessi));
        selectionRepository.save(new Selection()
                .setName("Henri")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("80"))
                .setMarket(scoreMoreThanMessi));
        selectionRepository.save(new Selection()
                .setName("Ons")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("90"))
                .setMarket(scoreMoreThanMessi));
        selectionRepository.save(new Selection()
                .setName("Sabri")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("100"))
                .setMarket(scoreMoreThanMessi));
        selectionRepository.save(new Selection()
                .setName("Isma")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("110"))
                .setMarket(scoreMoreThanMessi));
        selectionRepository.save(new Selection()
                .setName("Eric")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("999999"))
                .setMarket(scoreMoreThanMessi));
        selectionRepository.save(new Selection()
                .setName("Louis")
                .setState(SelectionState.OPENED)
                .setCurrentOdd(new BigDecimal("10000000"))
                .setMarket(scoreMoreThanMessi));
    }

    @PostConstruct
    public void createUser(){
         customerRepository.save(new Customer().setPseudo("unibest").setBalance(new BigDecimal("50")));
    }

}
