package com.pedro.cruzeiro.dev.inventorymanagement.configuration;

import com.pedro.cruzeiro.dev.inventorymanagement.util.interceptor.HeaderValidationHandler;
import com.pedro.cruzeiro.dev.inventorymanagement.util.interceptor.HttpLoggerHandler;
import com.pedro.cruzeiro.dev.inventorymanagement.util.interceptor.MdcContextProviderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration {

  @Bean
  public WebMvcConfigurer initializerWebMvcConfigurer(
      MdcContextProviderHandler mdcContextProviderHandler,
      HttpLoggerHandler loggingHandler,
      HeaderValidationHandler headerValidationHandlerInterceptor) {
    return new WebMvcConfigurer() {
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mdcContextProviderHandler);
        registry.addInterceptor(loggingHandler);
        registry.addInterceptor(headerValidationHandlerInterceptor);
      }
    };
  }
}
