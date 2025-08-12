package io.github.isadorabello.microsservice_email.dto;

import java.util.UUID;

public record EmailDTO(UUID id, String email, String subject, String text) {
}
