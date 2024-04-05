package com.aiden.trading.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SymbolResp {
    private String symbol;
    @JsonProperty("full_name")
    private String fullName;
    private String description;

    @JsonProperty("exchange")
    private String exchangeCode;
    private String type;
}
