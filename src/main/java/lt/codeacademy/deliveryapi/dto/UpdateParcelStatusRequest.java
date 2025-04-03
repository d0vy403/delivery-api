package lt.codeacademy.deliveryapi.dto;

import lt.codeacademy.deliveryapi.entity.Status;

public record UpdateParcelStatusRequest(
        Status status
) {}
