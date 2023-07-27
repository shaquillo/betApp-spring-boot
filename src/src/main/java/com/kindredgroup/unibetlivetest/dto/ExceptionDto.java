package com.kindredgroup.unibetlivetest.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ExceptionDto {

    private String path;
    private String errormessage;
    private String timestamp = LocalDateTime.now().toString();
}
