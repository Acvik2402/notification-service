package com.doubledice.notificationservice.service;

import com.doubledice.notificationservice.dto.notification.AnalyticNotification;

/**
 * @author anton
 * @since 24.12.2022
 */
public interface NotificationService {
  AnalyticNotification save(AnalyticNotification dto);

  void send(AnalyticNotification dto);

  void consume(AnalyticNotification dto);
}
