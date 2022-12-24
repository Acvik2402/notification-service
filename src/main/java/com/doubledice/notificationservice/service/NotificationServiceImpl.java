package com.doubledice.notificationservice.service;

import com.doubledice.notificationservice.dto.notification.AnalyticNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author anton
 * @since 24.12.2022
 */
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

  private final KafkaTemplate<Long, AnalyticNotification> kafkaTemplate;
  private final ObjectMapper objectMapper;

  @Autowired
  public NotificationServiceImpl(KafkaTemplate<Long, AnalyticNotification> kafkaTemplate,
                             ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }

  @Override
  public AnalyticNotification save(AnalyticNotification dto) {
    return null;
  }

  @Override
  public void send(AnalyticNotification dto) {
    log.info("<= sending {}", writeValueAsString(dto));
    kafkaTemplate.send("analytic.new", dto);
  }

  @Override
  @KafkaListener(id = "Analytic", topics = {"analytic.new"}, containerFactory = "singleFactory")
  public void consume(AnalyticNotification dto) {
    log.info("=> consumed {}", writeValueAsString(dto));
  }

  private String writeValueAsString(AnalyticNotification dto) {
    try {
      return objectMapper.writeValueAsString(dto);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
    }
  }
}
