package com.bortoli.loadbalancer.beanConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bortoli.loadbalancer.balancingStrategies.factories.BalancingStrategyFactory;
import com.bortoli.loadbalancer.balancingStrategies.interfaces.BalancingStrategy;
import com.bortoli.loadbalancer.config.Config;
import com.bortoli.loadbalancer.config.ConfigLoader;

@Configuration
public class BalancingStrategyConfig {

  @Bean
  public BalancingStrategy strategy() {
    Config config = ConfigLoader.load();

    return BalancingStrategyFactory.createStrategy(config.getBalancingAlgorithm(), config.getNodes());
  }
}
