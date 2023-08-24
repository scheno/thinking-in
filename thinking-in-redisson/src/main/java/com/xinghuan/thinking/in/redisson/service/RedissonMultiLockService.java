package com.xinghuan.thinking.in.redisson.service;

import com.xinghuan.thinking.in.redisson.model.po.SettItemTrPO;
import com.xinghuan.thinking.in.redisson.repo.SettDocTrRepo;
import com.xinghuan.thinking.in.redisson.repo.SettItemTrRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author shenchen
 * @since 2023/8/21 16:06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RedissonMultiLockService {

    private final RedissonClient redissonClient;

    private final SettItemTrRepo settItemTrRepo;

    private final SettDocTrRepo settDocTrRepo;

    public void multiLock(List<Long> ids) {
        List<SettItemTrPO> settItemTrList = settItemTrRepo.selectBatchIds(ids);
        try {
            RLock rLock = getMultiLock(ids);
            if (Objects.nonNull(rLock)) {
                Thread.sleep(30000);
            } else {
                log.error("未获取到锁！！！");
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.info(settItemTrList.toString());
    }

    private RLock getMultiLock(List<Long> ids) throws InterruptedException {
        List<RLock> locks = new ArrayList<>();
        for (Long id : ids) {
            Thread.sleep(10000);
            RLock rLock = redissonClient.getLock("lock:" + id);
            locks.add(rLock);
        }
        RLock[] array = locks.toArray(new RLock[0]);
        RLock multiLock = redissonClient.getMultiLock(array);
        boolean isLock = multiLock.tryLock();
        if (isLock) {
            return multiLock;
        }
        return null;
    }

}
