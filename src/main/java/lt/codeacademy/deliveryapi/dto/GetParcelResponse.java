package lt.codeacademy.deliveryapi.dto;

import lt.codeacademy.deliveryapi.entity.Status;

public record GetParcelResponse(
        Long id,
        String trackingNumber,
        Double weightKg,
        String destinationAddress,
        Status status,
        Long courierId
) {}
