package com.bortoli.loadbalancer.reverseProxy.services.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProxyRequestDTO {

  private final String path;
  private final String method;
}
