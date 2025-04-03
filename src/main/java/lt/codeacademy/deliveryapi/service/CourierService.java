package lt.codeacademy.deliveryapi.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.deliveryapi.dto.CreateCourierRequest;
import lt.codeacademy.deliveryapi.dto.GetCourierResponse;
import lt.codeacademy.deliveryapi.dto.GetParcelResponse;
import lt.codeacademy.deliveryapi.dto.UpdateCourierRequest;
import lt.codeacademy.deliveryapi.entity.Courier;
import lt.codeacademy.deliveryapi.entity.Parcel;
import lt.codeacademy.deliveryapi.mapper.CourierMapper;
import lt.codeacademy.deliveryapi.mapper.ParcelMapper;
import lt.codeacademy.deliveryapi.repository.CourierRepository;
import lt.codeacademy.deliveryapi.repository.ParcelRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {
  private final CourierRepository courierRepository;
  private final CourierMapper courierMapper;
  private final ParcelRepository parcelRepository;
  private final ParcelMapper parcelMapper;

  public GetCourierResponse addCourier(CreateCourierRequest request) {
    if (request.personalCode() == null || !request.personalCode().matches("\\d{3}-\\d{3}-\\d{3}")) {
      throw new IllegalArgumentException("Invalid personal code format. Expected: ddd-ddd-ddd");
    }

    if (request.vehicleNumber() == null || !request.vehicleNumber().matches("[A-Z]{3}-\\d{3}")) {
      throw new IllegalArgumentException("Invalid vehicle number format. Expected: AAA-ddd");
    }
    try {
      Courier courier = courierMapper.toCourier(request);
      Courier savedCourier = courierRepository.save(courier);
      return courierMapper.toGetCourierResponse(savedCourier);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException("Duplicate personal code or vehicle number.");
    }
  }

  public List<GetCourierResponse> getAllCouriers() {
    List<Courier> couriers = courierRepository.findAll();
    return couriers.stream().map(courierMapper::toGetCourierResponse).toList();
  }

  public GetCourierResponse getCourierById(Long id) {
    Courier courier =
        courierRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Courier with id " + id + " not found"));

    if (id < 1) {
      throw new IllegalArgumentException("Invalid courier id");
    }

    return courierMapper.toGetCourierResponse(courier);
  }

  public List<GetParcelResponse> getParcelByCourierId(Long courierId) {
    List<Parcel> parcels = parcelRepository.findByCourierId(courierId);
    if (parcels.isEmpty()) {
      throw new EntityNotFoundException("Parcels not found for courier id: " + courierId);
    }
    return parcels.stream().map(parcelMapper::parcelToParcelResponse).collect(Collectors.toList());
  }

  public Courier updateCourier(Long id, UpdateCourierRequest request) {
    Courier courier =
        courierRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Courier with id " + id + " not found"));

    try {
      courierMapper.updateCourier(request, courier);
      return courierRepository.saveAndFlush(courier);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException("Duplicate personal code or vehicle number.");
    }
  }
}
