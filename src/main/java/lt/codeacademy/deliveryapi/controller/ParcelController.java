package lt.codeacademy.deliveryapi.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.deliveryapi.dto.CreateParcelRequest;
import lt.codeacademy.deliveryapi.dto.GetParcelResponse;
import lt.codeacademy.deliveryapi.dto.UpdateParcelStatusRequest;
import lt.codeacademy.deliveryapi.entity.Parcel;
import lt.codeacademy.deliveryapi.service.ParcelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parcels")
@RequiredArgsConstructor
public class ParcelController {

  private final ParcelService parcelService;

  @PostMapping
  public ResponseEntity<GetParcelResponse> addParcel(@RequestBody CreateParcelRequest request) {
    GetParcelResponse response = parcelService.addParcel(request);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<GetParcelResponse>> getAllParcels() {
    return new ResponseEntity<>(parcelService.getAllParcels(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetParcelResponse> getParcelById(@PathVariable Long id) {
    return new ResponseEntity<>(parcelService.getParcelById(id), HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetParcelResponse> updateParcelStatus(
      @PathVariable Long id, @RequestBody UpdateParcelStatusRequest request) {
    return new ResponseEntity<>(parcelService.updateParcelStatus(id, request), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Parcel> deleteParcel(@PathVariable Long id) {
    parcelService.deleteParcel(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
