package lt.codeacademy.deliveryapi.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.deliveryapi.entity.Courier;
import lt.codeacademy.deliveryapi.repository.CourierRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierRepository courierRepository;

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public void printAllCouriers() {
        List<Courier> couriers = getAllCouriers();
        System.out.println("\n--- All couriers ---");
        if (couriers.isEmpty()) {
            System.out.println("No Couriers found.");
        } else {
            couriers.forEach(System.out::println);
        }
    }

    public void addTestCourier() {
        List<Courier> couriers = new ArrayList<>();

        Courier courier1 = new Courier();
        courier1.setPersonalCode("12345");
        courier1.setName("Courier 1");
        courier1.setLastName("Johnson");
        courier1.setVehicleNumber("BGP 541");
        couriers.add(courier1);

        Courier courier2 = new Courier();
        courier2.setPersonalCode("67890");
        courier2.setName("Alice");
        courier2.setLastName("Smith");
        courier2.setVehicleNumber("ABC 123");
        couriers.add(courier2);

        Courier courier3 = new Courier();
        courier3.setPersonalCode("11223");
        courier3.setName("Bob");
        courier3.setLastName("Williams");
        courier3.setVehicleNumber("XYZ 789");
        couriers.add(courier3);

        courierRepository.saveAll(couriers);
    }
}
