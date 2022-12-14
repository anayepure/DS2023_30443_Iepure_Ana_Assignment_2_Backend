package com.example.dsproject.rabbitmq;

import com.example.dsproject.dtos.DeviceDto;
import com.example.dsproject.dtos.MeasurementDto;
import com.example.dsproject.services.DeviceService;
import com.example.dsproject.services.MeasurementService;
import com.example.dsproject.websocket.WebSocketController;
import java.text.ParseException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
  @Autowired
  private DeviceService deviceService;

  @Autowired
  private MeasurementService measurementService;

  @Value("${energy.device_Id}")
  private int device_Id;


  @RabbitListener(queues=MQConfig.QUEUE)
    public void listener(Message message) {
      System.out.println(message);
      DeviceDto deviceDto=deviceService.getDeviceById(Long.valueOf(device_Id));
      MeasurementDto measurementDto=new MeasurementDto(message.getMeasurement_value(), message.getTimestamp());
      Long measurementId=measurementService.saveMeasurement(measurementDto, deviceDto.getId());
    }

}
