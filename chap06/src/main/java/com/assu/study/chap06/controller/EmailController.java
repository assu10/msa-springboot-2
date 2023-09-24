package com.assu.study.chap06.controller;

import com.assu.study.chap06.domain.email.EmailAddress;
import com.assu.study.chap06.domain.email.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
  private final EmailService emailService;

  public EmailController(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostMapping("/hotels/{hotelId}/rooms/{roomNumber}/reservations/{reservationId}/send-email")
  public ResponseEntity<Void> sendEmail(@PathVariable Long hotelId,
                                        @PathVariable String roomNumber,
                                        @PathVariable Long reservationId) {
    emailService.sendEmail(new EmailAddress("Assu", "assu", "google.com"));
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
