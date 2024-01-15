package com.bortoli.loadbalancer.utils;

public class NetworkUtils {

  public static boolean validatePort(Integer port) {
    return port.intValue() > 0 && port.intValue() < (1 << 16);
  }
}
