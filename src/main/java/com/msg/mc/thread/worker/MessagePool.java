package com.msg.mc.thread.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.msg.mc.thread.SendMessageTaskThread;

/**
 * 线程池管理短信发送服务
 *
 * @author cloudy
 * @version 1.0
 * @date 2016年9月5日
 */
public enum MessagePool {

    instance;

    private ExecutorService executor = Executors.newCachedThreadPool();

    public void addTask(SendMessageTaskThread task) {
        executor.execute(task);
    }
}
