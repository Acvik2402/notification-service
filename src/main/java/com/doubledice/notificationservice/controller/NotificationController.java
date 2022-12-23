package com.doubledice.notificationservice.controller;

import com.doubledice.notificationservice.dto.notification.AnalyticNotification;
import com.doubledice.notificationservice.dto.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anton
 * @since 24.12.2022
 */
@RestController
@RequestMapping("/notification")
public class NotificationController {
  private final NotificationService service;

  @Autowired
  public NotificationController(NotificationService service) {
    this.service = service;
  }

  @PostMapping
  public void send(@RequestBody AnalyticNotification dto) {
    service.send(dto);
  }
}
