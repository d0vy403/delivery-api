package lt.codeacademy.deliveryapi.dto;

public record GetCourierResponse(
        Long id,
        String personalCode,
        String name,
        String lastName,
        String vehicleNumber
) {}
