package com.kindredgroup.unibetlivetest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Table(name = "market")
@Entity
@Data
@Accessors(chain = true)
public class Market {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity=Selection.class, mappedBy="market", fetch = FetchType.LAZY)
    private List<Selection> selections = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;




}
