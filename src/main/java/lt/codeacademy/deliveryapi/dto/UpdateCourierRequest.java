package lt.codeacademy.deliveryapi.dto;

public record UpdateCourierRequest(
        String personalCode,
        String name,
        String lastName,
        String vehicleNumber
) {}
