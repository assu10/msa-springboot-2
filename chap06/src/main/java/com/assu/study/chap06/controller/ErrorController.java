package com.assu.study.chap06.controller;

import com.assu.study.chap06.domain.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Locale;

@Slf4j
@RestController
public class ErrorController {
  private final MessageSource messageSource;

  public ErrorController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @GetMapping("/error")
  public void createError() {
    Locale locale = LocaleContextHolder.getLocale();
    String[] args = {"10"};
    String errorMessage = messageSource.getMessage("main.cart.tooltip", args, locale);
    BadRequestException badRequestException = new BadRequestException(errorMessage);

    LocalDate errorDate = LocalDate.now();
    log.trace("trace log, {}", errorDate);
    log.debug("debug log, {}", errorDate);
    log.info("info log, {}", errorDate);
    log.warn("warn log, {}", errorDate);
    log.error("error log, {}, {}", errorDate, "errorMessage 입니다.", badRequestException);

    throw badRequestException;
  }
}
