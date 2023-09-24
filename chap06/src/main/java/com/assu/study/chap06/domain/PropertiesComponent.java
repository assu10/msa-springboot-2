package com.assu.study.chap06.domain;


import org.springframework.beans.factory.annotation.Value;

import java.util.List;

//@Component
public class PropertiesComponent {
  private final List<String> bootStrapServers;
  private final String checkoutTopic;
  private final String reservationTopic;
  private final Integer ackLevel;

  public PropertiesComponent(
      @Value("${springtour.kafka.bootstrap-servers}") List<String> bootStrapServers,
      @Value("${springtour.kafka.topic.checkout}") String checkoutTopic,
      @Value("${springtour.kafka.topic.reservation}") String reservationTopic,
      @Value("${springtour.kafka.ack-level}") Integer ackLevel) {
    this.bootStrapServers = bootStrapServers;
    this.checkoutTopic = checkoutTopic;
    this.reservationTopic = reservationTopic;
    this.ackLevel = ackLevel;

    System.out.println("---" + this.bootStrapServers);  // [10.1.1.100, 10.1.1.101, 10.1.1.102]
    System.out.println("---" + this.checkoutTopic);
    System.out.println("---" + this.reservationTopic);
    System.out.println("---" + this.ackLevel);
  }
}
