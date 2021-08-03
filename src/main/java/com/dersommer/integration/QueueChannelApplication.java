package com.dersommer.integration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class QueueChannelApplication implements CommandLineRunner {

  @Autowired
  private MessageChannel queueChannel;

  public static void main(String[] args) {

    SpringApplication.run(QueueChannelApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    for (int i = 0; i < 10; i++) {
      queueChannel.send(MessageBuilder.createMessage(String.format("Message sent number %d", i), new MessageHeaders(new HashMap<>())));
    }
  }
}
