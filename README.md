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

By default, Spring uses the ThreadPoolTaskExecutor injected in context but you can define a custom thread pool executor
for async methods
```java
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
```

Apply the @Async annotation in your method. You can define the custom thread pool defined above.
```java
    @Async("customTaskExecutor")
    public CompletableFuture<UserPhone> findAsync(UUID userId) {
        log.info("Start to retrieve phone for user {}", userId);
        return CompletableFuture.completedFuture(generate());
    }
```

When you have all the methods for the composition you can use the CompletableFuture api to join all responses
````java
    public User find(UUID userId) {
        User user = null;
        try {
            CompletableFuture<UserName>    userNamePage    = userNameFinder.findAsync(userId);
            CompletableFuture<UserPhone>   userPhonePage   = userPhoneFinder.findAsync(userId);
            CompletableFuture<UserCompany> userCompanyPage = userCompanyFinder.findAsync(userId);
            CompletableFuture<UserEmail>   userEmailPage   = userEmailFinder.findAsync(userId);

            CompletableFuture.allOf(userNamePage, userPhonePage, userCompanyPage, userEmailPage).join();

            user = User.builder()
                       .name(userNamePage.get())
                       .email(userEmailPage.get())
                       .company(userCompanyPage.get())
                       .phone(userPhonePage.get())
                       .build();
        } catch (Exception e) {
            log.error("Error retrieving user {}", userId, e);
            throw new UserFinderException();
        }

        return user;
    }

````


