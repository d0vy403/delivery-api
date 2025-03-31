package lt.codeacademy.deliveryapi.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.deliveryapi.entity.Parcel;
import lt.codeacademy.deliveryapi.entity.Status;
import lt.codeacademy.deliveryapi.service.ParcelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/parcels")
@RequiredArgsConstructor
public class ParcelController {

  private final ParcelService parcelService;

  @PostMapping
  public ResponseEntity<Parcel> addParcel(@RequestBody Parcel parcel) {
    Parcel newParcel = parcelService.addParcel(parcel);
    return new ResponseEntity<>(newParcel, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Parcel>> getAllParcels() {
    return new ResponseEntity<>(parcelService.getAllParcels(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Parcel> getParcelById(@PathVariable Long id) {
    try {
      Parcel parcel = parcelService.getParcelById(id);
      return new ResponseEntity<>(parcel, HttpStatus.OK);
    } catch (HttpClientErrorException.NotFound e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Parcel> updateParcelStatus(@PathVariable Long id, @RequestBody Status status) {
    try {
      Parcel updatedParcel = parcelService.updateParcelStatus(id, status);
      return new ResponseEntity<>(updatedParcel, HttpStatus.OK);
    } catch (HttpClientErrorException.NotFound e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Parcel> deleteParcel(@PathVariable Long id) {
    parcelService.deleteParcel(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
