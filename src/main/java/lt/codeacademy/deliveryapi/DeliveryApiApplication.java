package lt.codeacademy.deliveryapi;

import lt.codeacademy.deliveryapi.service.CourierService;
import lt.codeacademy.deliveryapi.service.ParcelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

    public class DeliveryApiApplication implements CommandLineRunner {
        private final ParcelService parcelService;
        private final CourierService courierService;

        public DeliveryApiApplication(ParcelService parcelService, CourierService courierService) {
            this.parcelService = parcelService;
            this.courierService = courierService;
        }

        public static void main(String[] args) {
            SpringApplication.run(DeliveryApiApplication.class, args);
        }

        @Override
        public void run(String... args) {
            courierService.addTestCourier();
            parcelService.addTestParcels();
            parcelService.printAllParcels();
            courierService.printAllCouriers();
        }
    }
