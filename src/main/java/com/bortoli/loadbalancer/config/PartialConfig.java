package com.bortoli.loadbalancer.config;

import java.util.Optional;

import com.bortoli.loadbalancer.config.enums.BalancingAlgorithms;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class PartialConfig {

  private final Optional<String> uri;
  private final Optional<BalancingAlgorithms> balancingAlgorithm;

  public Config asConfig() {
    if (!uri.isPresent()) {
      throw new IllegalArgumentException("uri is required");
    }

    if (!balancingAlgorithm.isPresent()) {
      throw new IllegalArgumentException("balancingAlgorithm is required");
    }

    return new Config(uri.get(), balancingAlgorithm.get());
  }
}
