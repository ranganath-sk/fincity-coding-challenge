package com.example.fincity.coding.challenge.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.util.Collection;
import org.springframework.hateoas.Resources;

/**
 * HATEAOS Resources for the cars
 * 
 * @author S K
 *
 */
public class CarResources extends Resources<CarResource> {

  CarResources(Collection<CarResource> carResources) {

    super(carResources);

    this.add(linkTo(methodOn(CarController.class).getCars()).withSelfRel());
  }
}
