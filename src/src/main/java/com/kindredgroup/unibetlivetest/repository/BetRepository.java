package com.kindredgroup.unibetlivetest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.types.BetState;
import com.kindredgroup.unibetlivetest.types.SelectionState;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long>{
    
    public List<Bet> getBetByBetStateEqualsAndSelection_StateEquals(BetState betState, SelectionState selectionState);

    public boolean existsBetByName(String name);
}
