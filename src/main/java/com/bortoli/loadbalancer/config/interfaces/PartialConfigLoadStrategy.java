package com.bortoli.loadbalancer.config.interfaces;

import com.bortoli.loadbalancer.config.PartialConfig;

public interface PartialConfigLoadStrategy {

  public PartialConfig partialLoad();
}
