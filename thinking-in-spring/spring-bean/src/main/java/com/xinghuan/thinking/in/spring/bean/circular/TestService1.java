package com.xinghuan.thinking.in.spring.bean.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestService1 {

    @Autowired
    private TestService2 testService2;

    public void test1() {
        System.out.println("test1...");
    }

    @Async
    public void test2() {
        System.out.println("test2...");
    }

}
