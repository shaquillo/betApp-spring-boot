package com.kindredgroup.unibetlivetest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kindredgroup.unibetlivetest.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
