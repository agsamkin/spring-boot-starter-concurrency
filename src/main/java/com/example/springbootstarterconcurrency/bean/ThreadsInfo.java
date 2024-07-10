package com.example.springbootstarterconcurrency.bean;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadsInfo {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadsInfo.class);

    private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    private void printThreadInfo() {
        ThreadInfo[] allThreads = threadMXBean.dumpAllThreads(true, true);

        for (ThreadInfo threadInfo : allThreads) {
            LOG.info("ID потока: {}", threadInfo.getThreadId());
            LOG.info("Имя потока: {}", threadInfo.getThreadName());
            LOG.info("Статус потока: {}", threadInfo.getThreadState());
            LOG.info("-------------------------------------------------");
        }
    }


}
