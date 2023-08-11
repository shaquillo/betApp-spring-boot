package com.kindredgroup.unibetlivetest.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.repository.EventRepository;
import com.kindredgroup.unibetlivetest.repository.SelectionRepository;
import com.kindredgroup.unibetlivetest.types.ExceptionType;
import com.kindredgroup.unibetlivetest.types.SelectionState;
import com.kindredgroup.unibetlivetest.utils.Helpers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Component
@Log4j2
public class SelectionService {

    private final SelectionRepository selectionRepository;
    private final EventRepository eventRepository;

    /**
     * 1. Récupère toute les selections ouvertes
     * 2. Mis à jour la cote aléatoirement
     */

    public Long updateOddsRandomly() {
        final List<Selection> selectionsOpened = selectionRepository.getSelectionByStateEquals(SelectionState.OPENED);
        return selectionsOpened.isEmpty() ? 0 : selectionsOpened
                .stream()
                .map(selection -> selection.setCurrentOdd(Helpers.updateOddRandomly(selection.getCurrentOdd())))
                .map(selectionRepository::save)
                .count();
    }

    /**
     * 1. Récupère toute les selections ouvertes
     * 2. Ferme 5 sélections aléatoirement.
     */

    public Long closeOddsRandomly() {
        final List<Selection> selectionsOpened = selectionRepository.getSelectionByStateEquals(SelectionState.OPENED);
        return selectionsOpened.isEmpty() ? 0 : IntStream
                .range(0, 5)
                .mapToObj(i -> selectionRepository.save(
                        selectionsOpened.get(Helpers.getRandomIndex(0, selectionsOpened.size()))
                                .setState(SelectionState.CLOSED)
                                .setResult(Helpers.setResultRandomly())))
                .count();
    }


    /*
     * 1. Vérifie que l'événement existe
     * 2. Récupère les sélections avec un état donné pour un événement donné
     */

    public List<Selection> findSelections(Long eventId, SelectionState state){
        log.info("Finding selections with eventId = %d and selectionState = %s", eventId, state.toString());

        eventRepository.findById(eventId).orElseThrow(() -> new CustomException(String.format("Event with id %d not found", eventId), ExceptionType.EXCEPTION_NOT_FOUND));

        return selectionRepository.getSelectionByMarket_Event_IdEqualsAndStateEquals(eventId, state);
    } 


}
