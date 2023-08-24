package com.xinghuan.thinking.in.redisson.controller;

import com.xinghuan.thinking.in.redisson.service.RedissonMultiLockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shenchen
 * @since 2023/8/21 16:08
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@ControllerAdvice
public class RedissonMultiLockController {

    private final RedissonMultiLockService redissonMultiLockService;

    @PostMapping("/lock/multi")
    public String multiLock(@RequestBody List<Long> ids) throws InterruptedException {
        redissonMultiLockService.multiLock(ids);
        return "success";
    }

}
