package com.bortoli.loadbalancer.config.strategies;

import com.bortoli.loadbalancer.config.PartialConfig;
import com.bortoli.loadbalancer.config.enums.BalancingAlgorithms;
import com.bortoli.loadbalancer.config.interfaces.PartialConfigLoadStrategy;
import com.moandjiezana.toml.Toml;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class FileConfigLoader implements PartialConfigLoadStrategy {

  private final String configFilePath;

  public FileConfigLoader(String configFilePath) {
    this.configFilePath = configFilePath;
  }

  @Override
  public PartialConfig partialLoad() {
    Toml toml = new Toml().read(new File(configFilePath));
    String uri = toml.getString("connection.uri");
    String strategy = toml.getString("loadbalancer.strategy");
    List<String> nodes = toml.getList("connection.nodes");

    Optional<String> optionalUri = Optional.ofNullable(uri);
    Optional<BalancingAlgorithms> optionalAlgorithm = strategy == null
        ? Optional.empty()
        : Optional.of(BalancingAlgorithms.valueOf(strategy));
    Optional<Vector<String>> optionalNodes = nodes == null
        ? Optional.empty()
        : Optional.of(new Vector<>(nodes));

    return new PartialConfig(optionalUri, optionalAlgorithm, optionalNodes);
  }
}
