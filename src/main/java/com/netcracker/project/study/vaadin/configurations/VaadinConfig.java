package com.netcracker.project.study.vaadin.configurations;

import com.vaadin.spring.annotation.EnableVaadin;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.vaadin.spring.events.config.EventBusConfiguration;

@Configuration()
@EnableVaadin // this imports VaadinConfiguration
@Import(EventBusConfiguration.class)
public class VaadinConfig {
    // application specific configurations - register myBean in the context
    /*@Bean
    public VaadinServlet vaadinServlet() {
        return new VaadinUIServlet();
    }*/

   /* @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }*/
}