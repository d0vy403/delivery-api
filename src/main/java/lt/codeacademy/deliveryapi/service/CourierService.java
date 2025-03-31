package lt.codeacademy.deliveryapi.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.deliveryapi.entity.Courier;
import lt.codeacademy.deliveryapi.entity.Parcel;
import lt.codeacademy.deliveryapi.repository.CourierRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {
  private final CourierRepository courierRepository;

  public Courier addCourier(Courier courier) {
    return courierRepository.saveAndFlush(courier);
  }

  public List<Courier> getAllCouriers() {
    return courierRepository.findAll();
  }

  public Courier getCourierById(Long id) {
    return courierRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Courier with id " + id + " not found"));
  }

  public List<Parcel> getParcelByCourierId(Long courierId) {
    Courier courier =
        courierRepository
            .findById(courierId)
            .orElseThrow(
                () -> new EntityNotFoundException("Courier with id " + courierId + " not found"));
    return courier.getParcels();
  }

  public Courier updateCourier(Courier courier) {
    return courierRepository.saveAndFlush(courier);
  }
}
