package com.bortoli.loadbalancer.reverseProxy.services.dto;

import org.springframework.http.HttpHeaders;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProxyRequestDTO {

  private final String path;
  private final String method;
  private final HttpHeaders headers;
  private final String clientIp;
}
