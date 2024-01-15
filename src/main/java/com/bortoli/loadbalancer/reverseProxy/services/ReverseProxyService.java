package com.bortoli.loadbalancer.reverseProxy.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bortoli.loadbalancer.balancingStrategies.interfaces.BalancingStrategy;
import com.bortoli.loadbalancer.models.Node;
import com.bortoli.loadbalancer.reverseProxy.services.dto.ProxyRequestDTO;

@Service
public class ReverseProxyService {

  @Autowired
  private BalancingStrategy balancingStrategy;

  public ResponseEntity<String> proxy(ProxyRequestDTO proxyRequestDTO) {
    Node node = balancingStrategy.getNextNode();
    URI uri = URI.create(node.getUrl() + proxyRequestDTO.getPath());

    try {
      node.incrementConnections();
      HttpEntity<String> httpEntity = new HttpEntity<>("", proxyRequestDTO.getHeaders());
      return new RestTemplate().exchange(
          uri,
          HttpMethod.valueOf(proxyRequestDTO.getMethod()),
          httpEntity,
          String.class);
    } finally {
      node.decrementConnections();
    }
  }
}
