package com.bortoli.loadbalancer.beanConfig;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

import com.bortoli.loadbalancer.config.Config;
import com.bortoli.loadbalancer.config.ConfigLoader;

@Configuration
public class WebServerConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

  public void customize(ConfigurableServletWebServerFactory factory) {
    Config config = ConfigLoader.load();

    factory.setAddress(config.getAddress());
    factory.setPort(config.getPort());
  }
}
