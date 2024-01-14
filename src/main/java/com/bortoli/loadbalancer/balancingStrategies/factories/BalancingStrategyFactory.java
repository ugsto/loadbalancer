package com.bortoli.loadbalancer.balancingStrategies.factories;

import java.util.Vector;

import com.bortoli.loadbalancer.balancingStrategies.RoundRobinStrategy;
import com.bortoli.loadbalancer.balancingStrategies.interfaces.BalancingStrategy;
import com.bortoli.loadbalancer.config.enums.BalancingAlgorithms;
import com.bortoli.loadbalancer.models.Node;

public class BalancingStrategyFactory {

  public static BalancingStrategy createStrategy(BalancingAlgorithms algorithm, Vector<Node> nodes) {
    switch (algorithm) {
      case ROUND_ROBIN:
        return new RoundRobinStrategy(nodes);
      default:
        throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
    }
  }
}
