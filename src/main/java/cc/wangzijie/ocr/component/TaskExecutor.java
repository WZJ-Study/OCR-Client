package cc.wangzijie.ocr.component;


import cc.wangzijie.ocr.task.OcrProcessTask;

import java.util.concurrent.*;

public class TaskExecutor {

    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    /**
     * 定时任务执行的线程池
     */
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newScheduledThreadPool(CORE_POOL_SIZE);

    /**
     * 单线程的线程池
     */
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(CORE_POOL_SIZE);

    /**
     * 运行任务
     *
     * @param task 任务
     */
    public static void execute(Runnable task) {
        EXECUTOR_SERVICE.submit(task);
    }

    /**
     * 运行任务
     *
     * @param task 任务
     * @return Future结果
     */
    public static Future<?> run(Runnable task) {
        return EXECUTOR_SERVICE.submit(task);
    }


    /**
     * 运行定时任务
     *
     * @param task 定时任务
     * @return ScheduledFuture结果
     */
    public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, int initialDelay, int delay, TimeUnit timeUnit) {
        return SCHEDULED_EXECUTOR_SERVICE.scheduleWithFixedDelay(task, initialDelay, delay, timeUnit);
    }

    /**
     * 运行定时任务
     *
     * @param task 定时任务
     * @return ScheduledFuture结果
     */
    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable task, int initialDelay, int period, TimeUnit timeUnit) {
        return SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(task, initialDelay, period, timeUnit);
    }

    /**
     * 运行定时任务
     *
     * @param task 定时任务
     * @return ScheduledFuture结果
     */
    public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, int intervalSeconds) {
        return SCHEDULED_EXECUTOR_SERVICE.scheduleWithFixedDelay(task, intervalSeconds, intervalSeconds, TimeUnit.SECONDS);
    }
}
