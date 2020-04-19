package com.example.fincity.coding.challenge.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.fincity.coding.challenge.api.exceptions.CarNotFoundException;
import com.example.fincity.coding.challenge.api.exceptions.CarsNotFoundException;
import com.example.fincity.coding.challenge.model.Car;
import com.example.fincity.coding.challenge.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for cars
 * 
 * @author Ranganatha S K
 *
 */
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@Slf4j
public class CarController {

  private final CarService carService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/hal+json")
  public ResponseEntity<CarResource> crateOrSaveCar(@RequestBody @Valid Car theCar) {

    log.info("creating new car with details: " + theCar);
    carService.saveCar(theCar);

    CarResource carResource = new CarResource(theCar);

    carResource.getCar().getId();
    return ResponseEntity.created(carResource.getLocationUri()).body(carResource);
  }

  @GetMapping(path = "/{id}", produces = "application/hal+json")
  public CarResource getCar(@PathVariable Long id) {

    log.info("Get car details with given id: " + id);
    Optional<Car> carDetails = carService.findCarById(id);

    return carDetails.map(CarResource::new).orElseThrow(() -> new CarNotFoundException(id));
  }

  @GetMapping(produces = "application/hal+json")
  public CarResources getCars() {

    List<CarResource> carResources =
        carService.findCars().stream().map(CarResource::new).collect(Collectors.toList());

    if (carResources == null || carResources.isEmpty()) {

      throw new CarsNotFoundException();
    }

    return new CarResources(carResources);
  }

  @PatchMapping(path = "/{id}", produces = "application/hal+json")
  public CarResource updateCarDetails(@PathVariable Long id, @RequestBody Car theCar)
      throws Exception {

    // check car details with specified exists
    if (carService.isExistsCarById(id)) {
      carService.updateCarDetails(id, theCar);

      theCar.setId(id);
      return new CarResource(theCar);

    } else {
      throw new CarNotFoundException(id);
    }
  }

  @DeleteMapping("/{id}")
  public void deleteCar(@PathVariable Long id) {

    if (carService.isExistsCarById(id)) {
      carService.deleteCarById(id);
    } else {
      throw new CarNotFoundException(id);
    }
  }
}
