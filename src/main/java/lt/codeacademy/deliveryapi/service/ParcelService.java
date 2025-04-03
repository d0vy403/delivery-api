package lt.codeacademy.deliveryapi.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.deliveryapi.dto.CreateParcelRequest;
import lt.codeacademy.deliveryapi.dto.GetParcelResponse;
import lt.codeacademy.deliveryapi.dto.UpdateParcelStatusRequest;
import lt.codeacademy.deliveryapi.entity.Courier;
import lt.codeacademy.deliveryapi.entity.Parcel;
import lt.codeacademy.deliveryapi.entity.Status;
import lt.codeacademy.deliveryapi.mapper.ParcelMapper;
import lt.codeacademy.deliveryapi.repository.CourierRepository;
import lt.codeacademy.deliveryapi.repository.ParcelRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParcelService {
  private final ParcelRepository parcelRepository;
  private final ParcelMapper parcelMapper;
  private final CourierRepository courierRepository;

  public GetParcelResponse addParcel(CreateParcelRequest request) {
    if (request.weightKg() <= 0 || request.weightKg() > 100) {
      throw new IllegalArgumentException("Weight must be between 0 and 100");
    }

    Courier courier =
        courierRepository
            .findById(request.courierId())
            .orElseThrow(() -> new EntityNotFoundException("Courier not found"));
    try {
      Parcel parcel = parcelMapper.toParcel(request);
      parcel.setCourier(courier);
      Parcel savedParcel = parcelRepository.save(parcel);
      return parcelMapper.parcelToParcelResponse(savedParcel);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException("Duplicate tracking number or invalid courier ID.");
    }
  }

  public List<GetParcelResponse> getAllParcels() {
    List<Parcel> parcels = parcelRepository.findAll();
    return parcels.stream().map(parcelMapper::parcelToParcelResponse).toList();
  }

  public GetParcelResponse getParcelById(Long id) {
    Parcel parcel =
        parcelRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Parcel with id " + id + " not found"));

    if (id < 1) {
      throw new IllegalArgumentException("Invalid parcel id");
    }

    return parcelMapper.parcelToParcelResponse(parcel);
  }

  public GetParcelResponse updateParcelStatus(Long id, UpdateParcelStatusRequest request) {
    Parcel parcel =
        parcelRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Parcel with id " + id + " not found"));

    if (parcel.getStatus() == Status.DELIVERED) {
      throw new IllegalArgumentException("Parcel is already delivered");
    }

    parcel.setStatus(request.status());
    Parcel updatedParcel = parcelRepository.save(parcel);
    return parcelMapper.parcelToParcelResponse(updatedParcel);
  }

  public void deleteParcel(Long id) {
    if (!parcelRepository.existsById(id)) {
      throw new EntityNotFoundException("Parcel with id: " + id + " not found");
    }
    parcelRepository.deleteById(id);
  }
}
