# Spring Boot Starter Concurrency

Проект демонстрирует пример реализации собственного spring boot стартера.

Стартер содержит аннотации для синхронизации методов при многопоточной работе:
* Lockable - реализует синхронизацию с использованием ReentrantLock
* UseCyclicBarrier - реализует синхронизацию с использованием  CyclicBarrier
* UseSemaphore - реализует синхронизацию с использованием  Semaphore

## Описание реализации:

1. Аннотации Lockable, UseCyclicBarrier и UseSemaphore - используются клиентами нашего стартера.
2. Аспекты LockAspect, UseCyclicBarrierAspect, UseSemaphoreAspect - обработчики для методов, помеченных нашими аннотациями.
3. Класс ConcurrencyAutoConfiguration - здесь описаны бины, необходимые для работы нашего стартера и, также, условия из создания (аннотация @Condition и ее производные).
4. Класс ConcurrencyProperties - здесь описаны проперти, которые будут доступны клиентам нашего стартера для его конфигурирования. 
Для подключания данных проперти над классом нашей конфигурации ConcurrencyAutoConfiguration указана аннотация @EnableConfigurationProperties(ConcurrencyProperties.class).
5. Чтобы Spring Boot автоматически подключал нашу конфигурацию к приложению-клиенту и создавал бины,
в файле ресурсов org.springframework.boot.autoconfigure.AutoConfiguration.imports стартера указан импорт нашей конфигурации.
6. Аннотация @ConditionalOnProperty над классов конфигурации ConcurrencyAutoConfiguration - указывает, что по префиксу concurrency
в ресурасах необходимо проверить значение проперти enable. Если оно имеет значение true, то эта конфигурация будет обработана. 
Если такого проперти нет, то будет использовано значение по умолчанию false.
7. Аннотация @ConditionalOnMissingBean над методом barrierAction() - если в контексте нет бина с типов BarrierAction, то будет создан новый с типом BarrierActionImpl.
8. Аннотация @ConditionalOnExpression надо методом lockAspect() - создает бин, если есть проперти concurrency.lock-enabled.
9. Аннотация @ConditionalOnCyclicBarrierCondition над методом useCyclicBarrierAspect() - наша собственная реализация аннотации @Conditional. 
10. Аннотация @ConditionalOnProperty над методом useSemaphoreAspect() - создает бин, если есть проперти concurrency.semaphore-enabled со значением true.
11. Класс ThreadsInfo используется для печати информации о потоках.
12. Класс ConcurrencyApplicationContextInitializer - используется для регистрации бина ThreadsInfo в контексте (как пример еще одного способа регистрации бинов).
Чтобы эта реализация запускалась, ее надо прописать в файле spring.factories.
13. Класс ConcurrencyStartupException - ошибка, которая будет выкидываться если в конфигурации приложения-клиента нет проперти enable.
14. Класс ConcurrencyEnvironmentPostProcessor - проверка налачия проперти enable. Для регистрации PostProcessor необходимо прописать в файле spring.factories.
15. Класс ConcurrencyFailureAnalyzer - для указания причины ошибки ConcurrencyStartupException и способа ее решения. Для регистрации FailureAnalysis необходимо прописать в файле spring.factories.





