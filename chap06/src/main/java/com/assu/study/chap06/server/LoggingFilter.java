package com.assu.study.chap06.server;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoggingFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    log.info("--- LoggingFilter doFilter: 선처리 작업");
    filterChain.doFilter(servletRequest, servletResponse);
    log.info("--- LoggingFilter doFilter: 후처리 작업");
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("--- LoggingFilter init()");
    System.out.println("--- LoggingFilter init() filterConfig: " + filterConfig);
    Filter.super.init(filterConfig);
  }

  @Override
  public void destroy() {
    System.out.println("--- LoggingFilter destroy()");
    Filter.super.destroy();
  }
}
