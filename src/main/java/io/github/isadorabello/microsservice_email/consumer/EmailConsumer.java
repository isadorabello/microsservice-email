package io.github.isadorabello.microsservice_email.consumer;

import io.github.isadorabello.microsservice_email.dto.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void ListenEmailQueue(@Payload EmailDTO dto) {
        System.out.println(dto.email());
    }

}
