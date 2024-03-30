package com.aiden.trading.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * @author 公众号:知了一笑
 * @since 2023-07-26 11:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartzLog extends BaseEntity{

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Integer jobId;

    private String beanName;

    private String params;

    private Integer state;

    private String error;

    private Integer times;

    private Date createTime;

}