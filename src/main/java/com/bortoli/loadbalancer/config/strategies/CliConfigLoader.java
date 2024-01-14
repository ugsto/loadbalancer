package com.bortoli.loadbalancer.config.strategies;

import com.bortoli.loadbalancer.config.PartialConfig;
import com.bortoli.loadbalancer.config.enums.BalancingAlgorithms;
import com.bortoli.loadbalancer.config.interfaces.PartialConfigLoadStrategy;
import com.moandjiezana.toml.Toml;

import java.io.File;
import java.util.Optional;

public class CliConfigLoader implements PartialConfigLoadStrategy {

  private final String configFilePath;

  public CliConfigLoader(String configFilePath) {
    this.configFilePath = configFilePath;
  }

  @Override
  public PartialConfig partialLoad() {
    Toml toml = new Toml().read(new File(configFilePath));
    String uri = toml.getString("connection.uri");
    String strategy = toml.getString("loadbalancer.strategy");

    Optional<String> optionalUri = Optional.ofNullable(uri);
    Optional<BalancingAlgorithms> optionalAlgorithm = Optional.empty();

    if (strategy != null) {
      optionalAlgorithm = Optional.of(BalancingAlgorithms.valueOf(strategy));
    }

    return new PartialConfig(optionalUri, optionalAlgorithm);
  }
}
