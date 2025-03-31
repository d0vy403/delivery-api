package lt.codeacademy.deliveryapi.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.deliveryapi.entity.Courier;
import lt.codeacademy.deliveryapi.entity.Parcel;
import lt.codeacademy.deliveryapi.entity.Status;
import lt.codeacademy.deliveryapi.repository.CourierRepository;
import lt.codeacademy.deliveryapi.repository.ParcelRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParcelService {
  private final ParcelRepository parcelRepository;
  private final CourierRepository courierRepository;

  public Parcel addParcel(Parcel parcel) {
    Long courierId = parcel.getCourier().getId();
    Courier courier = courierRepository.findById(courierId)
            .orElseThrow(() -> new EntityNotFoundException("Courier with id " + courierId + " not found"));

    parcel.setCourier(courier);

    return parcelRepository.saveAndFlush(parcel);
  }

  public List<Parcel> getAllParcels() {
    return parcelRepository.findAll();
  }

  public Parcel getParcelById(Long id) {
    return parcelRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Parcel with id " + id + " not found"));
  }

  public Parcel updateParcelStatus(Long id, Status status) {
    Parcel parcel = this.getParcelById(id);

    if (parcel.getStatus().equals(status)) {
      throw new EntityNotFoundException("Parcel with id " + id + " already has status " + status);
    }

    parcel.setStatus(status);
    return parcelRepository.saveAndFlush(parcel);
  }

  public void deleteParcel(Long id) {
    parcelRepository.deleteById(id);
  }
}
