package com.bortoli.loadbalancer.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Node {

  @NonNull
  private final String uri;
  private int connections = 0;

  public void incrementConnections() {
    connections++;
  }

  public void decrementConnections() {
    connections--;
  }
}
