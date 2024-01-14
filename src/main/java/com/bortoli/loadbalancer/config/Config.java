package com.bortoli.loadbalancer.config;

import java.net.InetAddress;
import java.util.Vector;

import com.bortoli.loadbalancer.config.enums.BalancingAlgorithms;
import com.bortoli.loadbalancer.models.Node;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Config {

  private final InetAddress address;
  private final int port;
  private final BalancingAlgorithms balancingAlgorithm;
  private final Vector<Node> nodes;
}
