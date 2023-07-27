package com.kindredgroup.unibetlivetest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.entity.Market;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.repository.EventRepository;
import com.kindredgroup.unibetlivetest.types.ExceptionType;
import com.kindredgroup.unibetlivetest.types.SelectionState;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class EventService {
    
    private final EventRepository eventRepository;

    public List<Event> findEvents(boolean isLive){

        List<Event> events = eventRepository.findAll();

        if(isLive){
             events = events.stream().filter((event) -> {
            for(Market market : event.getMarkets()){
                for(Selection selection: market.getSelections()){
                    if(selection.getState() != SelectionState.OPENED){
                        return false;
                    }
                }
            }
            return true;
            }).collect(Collectors.toList());

            if(events.isEmpty()) throw new CustomException("No live event", ExceptionType.EXCEPTION_NO_CONTENT);
        }

        if(events.isEmpty()) throw new CustomException("No event", ExceptionType.EXCEPTION_NO_CONTENT);

        return events;
    }
}
