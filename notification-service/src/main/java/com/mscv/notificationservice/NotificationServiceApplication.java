package com.mscv.notificationservice;

import com.mscv.notificationservice.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics = {"notificationTopic"})
	public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
		log.info("Notification received with booking {}", orderPlacedEvent.getBookingNumber());
	}

}
