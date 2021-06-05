package br.com.juliancambraia.bookservice.resilience4j;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/book-service")
public class FooBarController {

    private Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @GetMapping("/foo-bar")
    @Retry(name = "foo-bar-retry", fallbackMethod = "responseErrorFallback")
    public String fooBar() {
        logger.info("Request to Foo-bar is received");
        return new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class).getBody();
    }

    private String responseErrorFallback(Exception e) {
        return "FallbackMethod Foo-bar.!!!";
    }

    @GetMapping("/foo-bar-circuitbreaker")
    @CircuitBreaker(name = "foo-bar-circuit-breaker", fallbackMethod = "responseErrorFallbackCircuitBreaker")
    public String fooBarCircuitBreaker() {
        logger.info("Request to Foo-bar-circuit-breaker is received");
        return new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class).getBody();
    }

    private String responseErrorFallbackCircuitBreaker(Exception e) {
        return "Request to Foo-bar-circuit-breaker";
    }

    @GetMapping("/foo-bar-ratelimiter")
    @RateLimiter(name = "foo-bar-rate-limiter")
    public String fooBarRateLimiter() {
        logger.info("Request to Foo-bar-rate-limiter is received");
        // return new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class).getBody();
        return "Foo-bar-rate-limiter";
    }

    @GetMapping("/foo-bar-bulkhead")
    @Bulkhead(name = "foo-bar-bulkhead")
    public String fooBarBulkHead() {
        logger.info("Request to Foo-bar-bulk-head is received");
        // return new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class).getBody();
        return "Foo-bar-bulk-head";
    }
}
