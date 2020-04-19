package com.example.fincity.coding.challenge.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.net.URI;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import com.example.fincity.coding.challenge.model.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

/**
 * HATEAOS Resource class for cars
 * 
 * @author Ranganatha S K
 *
 */
@Relation(collectionRelation = "cars")
@Getter
public class CarResource extends ResourceSupport {

  @JsonUnwrapped
  private final Car car;

  public CarResource(Car car) {
    this.car = car;

    long id = car.getId();

    this.add(linkTo(methodOn(CarController.class).getCar(id)).withSelfRel());
    this.add(linkTo(CarController.class).withRel("cars"));
  }

  @JsonIgnore
  public URI getLocationUri() {
    return URI.create(getId().getHref());
  }
}
