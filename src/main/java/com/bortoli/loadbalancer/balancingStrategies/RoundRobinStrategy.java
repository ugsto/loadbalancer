package com.bortoli.loadbalancer.balancingStrategies;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import com.bortoli.loadbalancer.balancingStrategies.interfaces.BalancingStrategy;
import com.bortoli.loadbalancer.models.Node;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoundRobinStrategy implements BalancingStrategy {

  @NonNull
  private final Vector<Node> nodes;
  private final AtomicInteger index = new AtomicInteger(0);

  public Node getNextNode() {
    int current = index.getAndIncrement();
    return nodes.get(current % nodes.size());
  }
}
