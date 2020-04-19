package com.example.fincity.coding.challenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.fincity.coding.challenge.model.Car;

/**
 * @author Ranganatha S K
 *
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
