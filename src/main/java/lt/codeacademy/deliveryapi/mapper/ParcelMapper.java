package lt.codeacademy.deliveryapi.mapper;

import lt.codeacademy.deliveryapi.dto.CreateParcelRequest;
import lt.codeacademy.deliveryapi.dto.GetParcelResponse;
import lt.codeacademy.deliveryapi.entity.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParcelMapper {

  @Mapping(source = "courierId", target = "courier.id")
  Parcel toParcel(CreateParcelRequest request);

  @Mapping(source = "courier.id", target = "courierId")
  GetParcelResponse parcelToParcelResponse(Parcel parcel);
}
