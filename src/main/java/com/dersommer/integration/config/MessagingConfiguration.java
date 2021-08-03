package com.dersommer.integration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
@Slf4j
public class MessagingConfiguration {

  @Bean
  public MessageChannel queueChannel() {
    return new PublishSubscribeChannel();
  }

  @Bean
  @ServiceActivator(inputChannel = "queueChannel")
  public MessageHandler processQueueChannel(MessageChannel queueChannel ) {
    return (message) -> log.info("Received message [{}]", message.getPayload().toString());
  }
}
