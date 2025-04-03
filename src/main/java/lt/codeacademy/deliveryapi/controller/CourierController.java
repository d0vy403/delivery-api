package lt.codeacademy.deliveryapi.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.deliveryapi.dto.CreateCourierRequest;
import lt.codeacademy.deliveryapi.dto.GetCourierResponse;
import lt.codeacademy.deliveryapi.dto.GetParcelResponse;
import lt.codeacademy.deliveryapi.dto.UpdateCourierRequest;
import lt.codeacademy.deliveryapi.entity.Courier;
import lt.codeacademy.deliveryapi.mapper.CourierMapper;
import lt.codeacademy.deliveryapi.service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courier")
@RequiredArgsConstructor
public class CourierController {

  private final CourierService courierService;
  private final CourierMapper courierMapper;

  @PostMapping
  public ResponseEntity<GetCourierResponse> addCourier(@RequestBody CreateCourierRequest request) {
    GetCourierResponse response = courierService.addCourier(request);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<GetCourierResponse>> getAllCouriers() {
    return new ResponseEntity<>(courierService.getAllCouriers(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetCourierResponse> getCourier(@PathVariable Long id) {
    return new ResponseEntity<>(courierService.getCourierById(id), HttpStatus.OK);
  }

  @GetMapping("/{courierId}/parcels")
  public ResponseEntity<List<GetParcelResponse>> getCourierParcels(@PathVariable Long courierId) {
    List<GetParcelResponse> parcels = courierService.getParcelByCourierId(courierId);
    return ResponseEntity.ok(parcels);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GetCourierResponse> updateCourier(
      @PathVariable Long id, @RequestBody UpdateCourierRequest request) {
    Courier updatedCourier = courierService.updateCourier(id, request);
    return ResponseEntity.ok(courierMapper.toGetCourierResponse(updatedCourier));
  }
}
