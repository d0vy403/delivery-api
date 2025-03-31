package lt.codeacademy.deliveryapi.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.deliveryapi.entity.Courier;
import lt.codeacademy.deliveryapi.entity.Parcel;
import lt.codeacademy.deliveryapi.service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/courier")
@RequiredArgsConstructor
public class CourierController {

  private final CourierService courierService;

  @PostMapping
  public ResponseEntity<Courier> addCourier(@RequestBody Courier courier) {
    Courier newCourier = courierService.addCourier(courier);
    return new ResponseEntity<>(newCourier, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Courier>> getAllCouriers() {
    return new ResponseEntity<>(courierService.getAllCouriers(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Courier> getCourier(@PathVariable Long id) {
    try {
      Courier courier = courierService.getCourierById(id);
      return new ResponseEntity<>(courier, HttpStatus.OK);
    } catch (HttpClientErrorException.NotFound e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{courierId}/parcels")
  public ResponseEntity<List<Parcel>> getCourierParcels(@PathVariable Long courierId) {
    try {
      List<Parcel> parcels = courierService.getParcelByCourierId(courierId);
      return new ResponseEntity<>(parcels, HttpStatus.OK);
    } catch (HttpClientErrorException.NotFound e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Courier> updateCourier(
      @PathVariable Long id, @RequestBody Courier courier) {
    try {
      courier.setId(id);
      Courier updatedCourier = courierService.updateCourier(courier);
      return new ResponseEntity<>(updatedCourier, HttpStatus.OK);
    } catch (HttpClientErrorException.NotFound e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
