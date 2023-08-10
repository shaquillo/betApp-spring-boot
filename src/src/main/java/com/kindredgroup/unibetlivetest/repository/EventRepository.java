package com.kindredgroup.unibetlivetest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.types.SelectionState;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> getEventByMarket_Selection_StateEquals(SelectionState selectionState);
}
