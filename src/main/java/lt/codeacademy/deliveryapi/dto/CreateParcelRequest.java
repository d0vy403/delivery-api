package lt.codeacademy.deliveryapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lt.codeacademy.deliveryapi.entity.Status;
import org.hibernate.validator.constraints.Length;

public record CreateParcelRequest(
    @Length(min = 5, max = 10) @NotBlank String trackingNumber,
    @Positive @NotBlank Double weightKg,
    @Length(min = 5, max = 50) @NotBlank String destinationAddress,
    @NotBlank Status status,
    @NotBlank @Length(min = 1, max = 10) Long courierId) {}
