package lt.codeacademy.deliveryapi.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.deliveryapi.entity.Parcel;
import lt.codeacademy.deliveryapi.entity.Status;
import lt.codeacademy.deliveryapi.repository.ParcelRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParcelService {
  private final ParcelRepository parcelRepository;

  public List<Parcel> getAllParcels() {
    return parcelRepository.findAll();
  }

  public void printAllParcels() {
    List<Parcel> parcels = getAllParcels();
    System.out.println("\n--- All Parcels ---");
    if (parcels.isEmpty()) {
      System.out.println("No Parcels found");
    } else {
      parcels.forEach(System.out::println);
    }
  }

  public void addTestParcels() {

    List<Parcel> parcels = new ArrayList<>();

    Parcel parcel1 = new Parcel();
    parcel1.setTrackingNumber("TN12345");
    parcel1.setWeightKg(2.5);
    parcel1.setDestinationAddress("123 Main St, Vilnius");
    parcel1.setStatus(Status.IN_TRANSIT);
    parcel1.setCourier(null);
    parcels.add(parcel1);

    Parcel parcel2 = new Parcel();
    parcel2.setTrackingNumber("TN67890");
    parcel2.setWeightKg(1.0);
    parcel2.setDestinationAddress("456 Oak Ave, Kaunas");
    parcel2.setStatus(Status.DELIVERED);
    parcel2.setCourier(null);
    parcels.add(parcel2);

    Parcel parcel3 = new Parcel();
    parcel3.setTrackingNumber("TN11223");
    parcel3.setWeightKg(5.0);
    parcel3.setDestinationAddress("789 Pine Ln, Klaipeda");
    parcel3.setStatus(Status.PENDING);
    parcel3.setCourier(null);
    parcels.add(parcel3);

    parcelRepository.saveAll(parcels);
  }
}
