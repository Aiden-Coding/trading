package com.aiden.trading.entity;

import com.aiden.trading.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 股票概念
 * </p>
 *
 * @author zd
 * @since 2024-04-27 14:19:52
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("stock_gn")
@Schema(name = "StockGn", description = "股票概念")
public class StockGn extends BaseEntity {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "名称")
    @TableField("`name`")
    private String name;

    @Schema(description = "代码")
    @TableField("`code`")
    private String code;

    @Schema(description = "代码")
    @TableField("ex_code")
    private String exCode;

    @Schema(description = "代码")
    @TableField("`source`")
    private String source;

    @Schema(description = "代码")
    @TableField("url")
    private String url;

    @Schema(description = "上市日期")
    @TableField("list_date")
    private LocalDateTime listDate;
}
