package com.bortoli.loadbalancer.config;

import com.bortoli.loadbalancer.config.enums.BalancingAlgorithms;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Config {

  private final String uri;
  private final BalancingAlgorithms balancingAlgorithm;
}
