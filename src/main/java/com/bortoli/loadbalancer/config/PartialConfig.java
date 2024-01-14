package com.bortoli.loadbalancer.config;

import java.util.Optional;
import java.util.Vector;

import com.bortoli.loadbalancer.config.enums.BalancingAlgorithms;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class PartialConfig {

  private final Optional<String> uri;
  private final Optional<BalancingAlgorithms> balancingAlgorithm;
  private final Optional<Vector<String>> nodes;

  public Config asConfig() {
    return new Config(
        uri.orElse("localhost:31415"),
        balancingAlgorithm.orElse(BalancingAlgorithms.ROUND_ROBIN),
        nodes.orElse(new Vector<>()));
  }
}
