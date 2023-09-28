package com.assu.study.chap06.controller;

import com.assu.study.chap06.domain.BadRequestException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

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

    throw badRequestException;
  }
}
