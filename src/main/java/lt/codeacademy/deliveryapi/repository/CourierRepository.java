package lt.codeacademy.deliveryapi.repository;

import lt.codeacademy.deliveryapi.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {}
