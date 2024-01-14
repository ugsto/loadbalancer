package com.bortoli.loadbalancer.config;

import java.net.InetAddress;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

import com.bortoli.loadbalancer.config.enums.BalancingAlgorithms;
import com.bortoli.loadbalancer.models.Node;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class PartialConfig {

  private final Optional<InetAddress> address;
  private final Optional<Integer> port;
  private final Optional<BalancingAlgorithms> balancingAlgorithm;
  private final Optional<Vector<String>> nodes;

  public Config asConfig() {
    return new Config(
        address.orElse(InetAddress.getLoopbackAddress()),
        port.orElse(31415),
        balancingAlgorithm.orElse(BalancingAlgorithms.ROUND_ROBIN),
        nodes.orElse(new Vector<>()).stream().map(Node::new).collect(Collectors.toCollection(Vector::new)));
  }
}
