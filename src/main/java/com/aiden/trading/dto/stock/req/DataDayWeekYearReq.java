package com.aiden.trading.dto.stock.req;

import lombok.Data;

@Data
public class DataDayWeekYearReq {
    private String symbol;
    private String resolution;
    private Long from;
    private Long to;

}
