package com.kindredgroup.unibetlivetest.dto;

import lombok.Data;

@Data
public class AddBetDto {
    private String name;

    private float amount;

    private Long selectionId;

    private Long customerId;

    private float odd;

}
