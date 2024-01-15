package com.bortoli.loadbalancer.reverseProxy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;

import com.bortoli.loadbalancer.logger.ApplicationLogger;
import com.bortoli.loadbalancer.reverseProxy.services.ReverseProxyService;
import com.bortoli.loadbalancer.reverseProxy.services.dto.ProxyRequestDTO;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@RestController
public class ReverseProxyController {

  @Autowired
  private ReverseProxyService reverseProxyService;

  @RequestMapping("/**")
  public ResponseEntity<String> reverseProxy(HttpServletRequest request) {
    try {
      HttpHeaders headers = getHeaders(request);
      ProxyRequestDTO proxyRequestDTO = new ProxyRequestDTO(
          request.getRequestURI(),
          request.getMethod(),
          headers,
          request.getRemoteAddr());

      return reverseProxyService.proxy(proxyRequestDTO);
    } catch (HttpStatusCodeException e) {
      return ResponseEntity.status(e.getStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
    } catch (ResourceAccessException e) {
      if (e.getMessage().endsWith("Unsupported or unrecognized SSL message")) {
        return ResponseEntity.status(500).body("Did an SSL request was made to a non-SSL endpoint?");
      }

      ApplicationLogger.error("Unhandled error while proxying request: ", e);
      return ResponseEntity.status(500).body("Internal Load Balancer Error");
    } catch (Exception e) {
      ApplicationLogger.error("Unhandled error while proxying request: ", e);
      return ResponseEntity.status(500).body("Internal Load Balancer Error");
    }
  }

  private HttpHeaders getHeaders(HttpServletRequest request) {
    HttpHeaders headers = new HttpHeaders();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      headers.set(headerName, request.getHeader(headerName));
    }
    headers.set("X-Real-IP", request.getRemoteAddr());

    return headers;
  }
}
