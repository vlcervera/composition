package org.vlcervera.composition.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.vlcervera.composition.application.UserFinderUseCase;
import org.vlcervera.composition.infrastructure.adapter.UserAsyncFinderAdapter;
import org.vlcervera.composition.infrastructure.adapter.UserFinderAdapter;

import java.util.concurrent.Executor;

@Configuration
public class Config {

    /**
     * Create a custom ThreadPoolTaskExecutor for @Async methods
     *
     * @return
     */
    @Bean("customTaskExecutor")
    public Executor customTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Custom-");
        executor.initialize();
        return executor;
    }

    /**
     * Create a new instance of UserFinderUseCase with UserAsyncFinderAdapter as UserFinderPort implementation
     *
     * @param userAsyncFinderAdapter
     * @return UserFinderUseCase instance
     */
    @Bean
    public UserFinderUseCase userFinderUseCaseAsync(UserAsyncFinderAdapter userAsyncFinderAdapter) {
        return new UserFinderUseCase(userAsyncFinderAdapter);
    }

    /**
     * Create a new instance of UserFinderUseCase with UserFinderAdapter as UserFinderPort implementation
     *
     * @param userAsyncFinderAdapter
     * @return UserFinderUseCase instance
     */
    @Bean
    public UserFinderUseCase userFinderUseCase(UserFinderAdapter userAsyncFinderAdapter) {
        return new UserFinderUseCase(userAsyncFinderAdapter);
    }
}
