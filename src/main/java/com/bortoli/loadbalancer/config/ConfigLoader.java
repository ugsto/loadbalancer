package com.bortoli.loadbalancer.config;

import com.bortoli.loadbalancer.config.strategies.FileConfigLoader;

public class ConfigLoader {
  final static Config config = new FileConfigLoader("config.toml").partialLoad().asConfig();

  public static Config load() {
    return config;
  }
}
