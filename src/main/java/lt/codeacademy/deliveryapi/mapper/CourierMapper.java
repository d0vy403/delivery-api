package lt.codeacademy.deliveryapi.mapper;

import lt.codeacademy.deliveryapi.dto.CreateCourierRequest;
import lt.codeacademy.deliveryapi.dto.GetCourierResponse;
import lt.codeacademy.deliveryapi.dto.UpdateCourierRequest;
import lt.codeacademy.deliveryapi.entity.Courier;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourierMapper {

  Courier toCourier(CreateCourierRequest request);

  GetCourierResponse toGetCourierResponse(Courier courier);

  void updateCourier(UpdateCourierRequest request, @MappingTarget Courier courier);
}
