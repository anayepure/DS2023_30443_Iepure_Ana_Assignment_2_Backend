package com.example.dsproject.rabbitmq;


import com.example.dsproject.controllers.DeviceController;
import com.example.dsproject.dtos.DeviceDto;
import com.example.dsproject.dtos.MeasurementDto;
import com.example.dsproject.services.DeviceService;
import com.example.dsproject.services.MeasurementService;
import java.util.concurrent.TimeUnit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

@RestController
@EnableAsync
public class Producer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private MeasurementService measurementService;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody Message message)
    {
      Message message1=new Message(message.getDevice_id(), message.getMeasurement_value());
      template.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY, message1);
      return "Message published";
    }

    void readFromCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/ana.iepure/Documents/ds-project -backend/ds-project/sensor.csv"))) {
            Double line;
            while ((line = Double.valueOf(br.readLine())) != null) {
              System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/ana.iepure/Documents/ds-project -backend/ds-project/sensor.csv"))) {
            Double line;
            while ((line = Double.valueOf(br.readLine())) != null) {
                Message message=new Message(1L,line);
                template.convertAndSend(MQConfig.QUEUE, message);
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
