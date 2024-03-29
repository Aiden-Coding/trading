package com.aiden.trading.controller;

import com.aiden.trading.entity.QuartzJob;
import com.aiden.trading.service.QuartzJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quartz")
@Tag(name = "定时任务接口")
public class QuartzController  {

    @Resource
    private QuartzJobService quartzJobService ;

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