package com.example.fincity.coding.challenge.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.fincity.coding.challenge.model.Car;
import com.example.fincity.coding.challenge.repo.CarRepository;
import lombok.RequiredArgsConstructor;

/**
 * Service class for the cars
 * 
 * @author Ranganatha S K
 *
 */
@Service
@RequiredArgsConstructor
public class CarService {

  private final CarRepository carRepository;

  public void saveCar(Car theCar) {

    carRepository.save(theCar);
  }

  public Optional<Car> findCarById(Long carId) {

    return carRepository.findById(carId);
  }

  public List<Car> findCars() {
    return carRepository.findAll();
  }

  public void updateCarDetails(Long carId, Car theCar) {

    carRepository.findById(carId).ifPresent(car -> {
      car.setId(carId);
      car.setName(theCar.getName());
      car.setModel(theCar.getModel());
      car.setManufacturerName(theCar.getManufacturerName());
      car.setManufacturingYear(theCar.getManufacturingYear());
      car.setColor(theCar.getColor());

      carRepository.save(car);
    });
  }

  public void deleteCarById(Long carId) {
    carRepository.deleteById(carId);
  }

  public boolean isExistsCarById(Long carId) {
    return carRepository.existsById(carId);
  }
}
