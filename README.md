# Composition using @Async methods

# Steps
Configure your application to enable async behaviour using @EnableAsync annotation in your spring boot
application class.
```java
@SpringBootApplication
@EnableAsync
public class CompositionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompositionApplication.class, args);
    }
}
```

By default, Spring use the ThreadPoolTaskExecutor injected in your context but you can define a custom thread pool executor
for async methods
```java
    @Bean
    public Executor customTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Custom-");
        executor.initialize();
        return executor;
    }
```

Apply the @Async annotation in your method. You can define the custom thread pool defined above.
```java
    @Async("customTaskExecutor")
    public CompletableFuture<UserPhone> findConcurrent(UUID userId) {
        log.info("Start to retrieve phone for user {}", userId);
        return CompletableFuture.completedFuture(generate());
    }
```

When you have all the methods for the composition you can use the CompletableFuture api to join all responses
````java
        CompletableFuture<UserName>    userNamePage    = userNameFinderPort.findConcurrent(userId);
        CompletableFuture<UserPhone>   userPhonePage   = userPhoneFinderPort.findConcurrent(userId);
        CompletableFuture<UserCompany> userCompanyPage = userCompanyFinderPort.findConcurrent(userId);
        CompletableFuture<UserEmail>   userEmailPage   = userEmailFinderPort.findConcurrent(userId);

        CompletableFuture.allOf(userNamePage, userPhonePage, userCompanyPage, userEmailPage).join();

        User user = User.builder()
                        .name(userNamePage.get())
                        .email(userEmailPage.get())
                        .company(userCompanyPage.get())
                        .phone(userPhonePage.get())
                        .build();

````

