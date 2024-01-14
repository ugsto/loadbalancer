package com.bortoli.loadbalancer.reverseProxy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class ReverseProxyController {

  @RequestMapping("/**")
  public ResponseEntity<String> reverseProxy() {
    return ResponseEntity.ok("OK");
  }
}
