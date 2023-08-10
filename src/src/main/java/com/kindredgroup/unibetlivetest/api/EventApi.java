package com.kindredgroup.unibetlivetest.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.service.EventService;
import com.kindredgroup.unibetlivetest.service.SelectionService;
import com.kindredgroup.unibetlivetest.types.SelectionState;

@RestController
@Log4j2
@RequestMapping(Urls.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EventApi {

    private final EventService eventService;

    private final SelectionService selectionService;


    /** TODO
     *  @GetMapping(Urls.EVENTS)
     */

     @GetMapping(Urls.EVENTS)
     public List<Event> fetchEvents(@RequestParam(name = "isLive", required = false) boolean isLive){
        log.info("EventService : finding events with live state = %s", isLive);
        return eventService.findEvents(isLive);
     }


    /** TODO
     *  @GetMapping(Urls.SELECTIONS)
     */

     @GetMapping(Urls.SELECTIONS)
     public List<Selection> fetchSelections(@PathVariable(name = "id") Long id, @RequestParam(name = "state") SelectionState state){
        log.info("EventService : finding selections by eventId = %d and selectionState = %s", id, state.toString());
        return selectionService.findSelections(id, state);
     }


}
