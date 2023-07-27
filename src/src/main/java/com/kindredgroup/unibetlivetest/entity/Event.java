package com.kindredgroup.unibetlivetest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "Event")
@Entity
@Data
@Accessors(chain = true)
public class Event {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "start_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @JsonIgnore
    @OneToMany(targetEntity=Market.class, mappedBy="event", fetch = FetchType.LAZY)
    private List<Market> markets = new ArrayList<>();
}
