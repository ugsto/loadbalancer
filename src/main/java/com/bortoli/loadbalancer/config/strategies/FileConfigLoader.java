package com.bortoli.loadbalancer.config.strategies;

import com.bortoli.loadbalancer.config.PartialConfig;
import com.bortoli.loadbalancer.config.enums.BalancingAlgorithms;
import com.bortoli.loadbalancer.config.interfaces.PartialConfigLoadStrategy;
import com.bortoli.loadbalancer.utils.NetworkUtils;
import com.moandjiezana.toml.Toml;

import java.io.File;
import java.net.InetAddress;
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
    String address = toml.getString("connection.address");
    Long port = toml.getLong("connection.port");
    String strategy = toml.getString("loadbalancer.strategy");
    List<String> nodes = toml.getList("connection.nodes");

    return buildPartialConfig(address, port, strategy, nodes);
  }

  private PartialConfig buildPartialConfig(String address, Long port, String strategy, List<String> nodes) {
    Optional<InetAddress> optionalAddress;
    Optional<Integer> optionalPort;
    Optional<BalancingAlgorithms> optionalAlgorithm;
    Optional<Vector<String>> optionalNodes;

    try {
      optionalAddress = address == null
          ? Optional.empty()
          : Optional.of(InetAddress.getByName(address));
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid address: " + address);
    }

    if (port != null && NetworkUtils.validatePort(port.intValue())) {
      throw new IllegalArgumentException("Invalid port: " + port);
    }

    optionalPort = port == null
        ? Optional.empty()
        : Optional.of(port.intValue());

    optionalAlgorithm = strategy == null
        ? Optional.empty()
        : Optional.of(BalancingAlgorithms.valueOf(strategy));

    optionalNodes = nodes == null
        ? Optional.empty()
        : Optional.of(new Vector<>(nodes));

    return new PartialConfig(optionalAddress, optionalPort, optionalAlgorithm, optionalNodes);
  }
}
