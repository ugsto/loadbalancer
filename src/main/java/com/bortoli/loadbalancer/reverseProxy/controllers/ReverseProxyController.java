package com.bortoli.loadbalancer.reverseProxy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.bortoli.loadbalancer.logger.ApplicationLogger;
import com.bortoli.loadbalancer.reverseProxy.services.ReverseProxyService;
import com.bortoli.loadbalancer.reverseProxy.services.dto.ProxyRequestDTO;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class ReverseProxyController {

  @Autowired
  private ReverseProxyService reverseProxyService;

  @RequestMapping("/**")
  public ResponseEntity<String> reverseProxy(HttpServletRequest request) {
    try {
      return reverseProxyService.proxy(new ProxyRequestDTO(request.getRequestURI(), request.getMethod()));
    } catch (HttpStatusCodeException e) {
      return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
    } catch (Exception e) {
      ApplicationLogger.error("Unhandled error while proxying request: ", e);
      return ResponseEntity.status(500).body("Internal Load Balancer Error");
    }
  }
}
