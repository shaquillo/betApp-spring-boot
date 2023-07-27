package com.kindredgroup.unibetlivetest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(name = "customer")
@Entity
@Data
@Accessors(chain = true)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "balance")
    private BigDecimal balance;

    @JsonIgnore
    @OneToMany(targetEntity = Bet.class, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Bet> bets = new ArrayList<>();

}
