package hse.algosim.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadExecutorConfiguration {

    @Bean
    public ThreadPoolTaskExecutor boundedTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setThreadNamePrefix("bounded_task_executor_thread");
        executor.setQueueCapacity(2);
        executor.initialize();
        return executor;
    }
}