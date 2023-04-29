package com.prj;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author pengrj
 * @Date 2023/4/25 10:59
 * @Version 1.0
 **/
public class TimeServerHandlerExecutePool {
    private ExecutorService executor;

    public TimeServerHandlerExecutePool(int maxPollSize, int queueSize) {
        this.executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPollSize,120L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void execute(Runnable task){
        executor.execute(task);
    }
}
