package cc.wangzijie.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

@Slf4j
public class RetryHelper {

    private static final RetryTemplate TPL = RetryTemplate.builder()
            .maxAttempts(3)
            .exponentialBackoff(2000, 2, 10000, true)
            .retryOn(RuntimeException.class)
            .build();

    public static <T, E extends Throwable> T execute(RetryCallback<T, E> retryCallback) throws E {
        return TPL.execute(retryCallback);
    }
}
