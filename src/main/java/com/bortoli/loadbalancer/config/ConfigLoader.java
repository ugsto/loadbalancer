package com.bortoli.loadbalancer.config;

import com.bortoli.loadbalancer.config.strategies.CliConfigLoader;

public class ConfigLoader {
  final static Config config = new CliConfigLoader("config.toml").partialLoad().asConfig();

  public static Config load() {
    return config;
  }
}
