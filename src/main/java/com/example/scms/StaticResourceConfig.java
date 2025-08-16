package com.example.scms;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String invoicePath = Paths.get("invoices").toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/invoices/**")
                .addResourceLocations(invoicePath);
    }
}
