package com.jupiter.service.hotel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestStatus {
    OK("ok"),
    ERROR("error");

    private final String status;
}
