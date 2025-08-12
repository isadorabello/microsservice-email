package io.github.isadorabello.microsservice_email.repository;

import io.github.isadorabello.microsservice_email.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
