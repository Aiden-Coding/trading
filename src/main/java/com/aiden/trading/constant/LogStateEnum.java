package com.aiden.trading.constant;

import lombok.Getter;

/**
 * @author 公众号:知了一笑
 * @since 2023-07-26 10:06
 */
@Getter
public enum LogStateEnum {

    LOG_SUS((byte)1, "成功"),
    LOG_FAIL((byte)2, "失败");

    private Byte status;
    private String desc;

    LogStateEnum(Byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}