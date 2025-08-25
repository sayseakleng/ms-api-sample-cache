package kh.com.foss.sample.config;

import io.micrometer.context.ContextSnapshotFactory;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.boot.task.ThreadPoolTaskExecutorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class AsyncConfiguration {
    @Bean
    ThreadPoolTaskExecutorCustomizer executorCustomizer() {
        return executor -> {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        };
    }

    @Bean
    TaskDecorator taskDecorator() {
        return runnable -> ContextSnapshotFactory.builder().build().captureAll().wrap(runnable);
    }
}
