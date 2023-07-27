package com.kindredgroup.unibetlivetest.repository;

import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.types.SelectionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectionRepository extends JpaRepository<Selection, Long> {

    List<Selection> getSelectionByStateEquals(SelectionState state);

    List<Selection> getSelectionByMarket_Event_IdEqualsAndStateEquals(Long eventId, SelectionState state);
}
