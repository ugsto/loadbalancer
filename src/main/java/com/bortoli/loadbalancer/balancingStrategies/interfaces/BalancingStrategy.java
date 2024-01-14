package com.bortoli.loadbalancer.balancingStrategies.interfaces;

import com.bortoli.loadbalancer.models.Node;

public interface BalancingStrategy {
  Node getNextNode();
}
