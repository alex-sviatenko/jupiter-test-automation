package com.jupiter.service.hotel.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PricePercentileDTO {
    @JsonProperty("3")
    private Double _3;
    @JsonProperty("10")
    private Double _10;
    @JsonProperty("35")
    private Double _35;
    @JsonProperty("50")
    private Double _50;
    @JsonProperty("75")
    private Double _75;
    @JsonProperty("99")
    private Double _99;
}
