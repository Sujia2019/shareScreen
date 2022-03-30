package com.tute.wjl.utils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {

    public static ThreadPoolExecutor ThreadPool(final String name) {
        ThreadPoolExecutor executors=new ThreadPoolExecutor(
                60, 300, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000), r -> new Thread(r, "[" + name + "]-Pool-" + r.hashCode()), (r, executor) -> {
            throw new RuntimeException("["+name+"] Thread pool is EXHAUSTED!");
        });// default maxThreads 300, minThreads 60
        return executors;
    }
}
