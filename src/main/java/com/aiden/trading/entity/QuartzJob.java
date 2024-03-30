package com.aiden.trading.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "QuartzJob实体类")
public class QuartzJob extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "BOOT_JOB_PARAM_KEY";

    @Schema(description = "任务id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @Schema(description = "Bean名称")
    private String beanName;

    @Schema(description = "执行参数")
    private String params;

    @Schema(description = "Cron表达式")
    private String cronExpres;

    @Schema(description = "任务状态：1正常，2暂停，3删除")
    private Integer state;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private Date createTime;
}