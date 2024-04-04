package com.aiden.trading.controller;

import com.aiden.trading.dto.PageReq;
import com.aiden.trading.dto.Result;
import com.aiden.trading.entity.QuartzJob;
import com.aiden.trading.service.IQuartzJobService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 任务列表 前端控制器
 * </p>
 *
 * @author zd
 * @since 2024-03-30 10:22:44
 */
@RestController
@RequestMapping("/quartz-job")
@Tag(name = "定时任务接口")
@CrossOrigin
public class QuartzJobController {


    @Resource
    private IQuartzJobService quartzJobService ;

    @Operation(summary = "任务查询")
    @PostMapping("/page")
    public Result<PageInfo<QuartzJob>> page(@RequestBody PageReq pageReq){
        PageInfo<QuartzJob> ret = quartzJobService.pageList(pageReq) ;
        return Result.data(ret);
    }

    @Operation(summary = "任务查询")
    @GetMapping("/job/{id}")
    public QuartzJob getById(@PathVariable Integer id){
        return quartzJobService.getById(id) ;
    }

    @Operation(summary = "任务新增")
    @PostMapping("/job")
    public Integer insert(@RequestBody QuartzJob quartzJob){
        return quartzJobService.insert(quartzJob) ;
    }

    @Operation(summary = "更新任务")
    @PutMapping("/job")
    public Integer update(@RequestBody QuartzJob quartzJob){
        return quartzJobService.update(quartzJob) ;
    }

    @Operation(summary = "停止任务")
    @PutMapping("/job/pause/{id}")
    public void pause(@PathVariable("id") Integer id) {
        quartzJobService.pause(id);
    }

    @Operation(summary = "恢复任务")
    @PutMapping("/job/resume/{id}")
    public void resume(@PathVariable("id") Integer id) {
        quartzJobService.resume(id) ;
    }

    @Operation(summary = "执行一次")
    @GetMapping("/job/runOnce/{id}")
    public void runOnce(@PathVariable("id") Integer id) {
        quartzJobService.runOnce(id) ;
    }
}
