package io.github.isadorabello.microsservice_email.service;

import io.github.isadorabello.microsservice_email.model.Email;
import io.github.isadorabello.microsservice_email.model.StatusEmail;
import io.github.isadorabello.microsservice_email.repository.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    final EmailRepository repository;
    final JavaMailSender sender;

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.repository = emailRepository;
        this.sender = javaMailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public Email sendEMail(Email email){
        try{
            email.setSendDateEmail(LocalDateTime.now());
            email.setEmailFrom(emailFrom);


            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getTextEmail());
            sender.send(message);

            email.setStatusEmail(StatusEmail.SENT);

        }catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        }finally {
            return repository.save(email);
        }
    }

}
