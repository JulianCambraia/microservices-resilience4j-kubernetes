package br.com.juliancambraia.apigateway.configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator getewayRouterLocator(RouteLocatorBuilder builder) {

        Function<PredicateSpec, Buildable<Route>> minhaFunction =
                p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("Hello", "World")
                                .addRequestParameter("paramHello", "World"))
                        .uri("http://httpbin.org:80");

        return builder.routes()
                .route(minhaFunction)
                .build();
    }
}
