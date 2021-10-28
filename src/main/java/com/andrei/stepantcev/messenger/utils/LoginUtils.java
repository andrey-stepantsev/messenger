package com.andrei.stepantcev.messenger.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class LoginUtils {

    private static final AtomicInteger atomicCounter = new AtomicInteger();

    public static String generateLogin() {
        return "USER_" + atomicCounter.incrementAndGet();
    }
}