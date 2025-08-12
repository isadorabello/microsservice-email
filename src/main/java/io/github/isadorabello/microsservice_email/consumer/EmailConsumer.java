package io.github.isadorabello.microsservice_email.consumer;

import io.github.isadorabello.microsservice_email.dto.EmailDTO;
import io.github.isadorabello.microsservice_email.model.Email;
import io.github.isadorabello.microsservice_email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService service;

    public EmailConsumer(EmailService service){
        this.service = service;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void ListenEmailQueue(@Payload EmailDTO dto) {
//        System.out.println(dto.email());
        var email = new Email();
        BeanUtils.copyProperties(dto, email);
        service.sendEMail(email);
    }

}
