package com.example.fincity.coding.challenge.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for cars
 * 
 * @author Ranganatha S K
 *
 */

@NoArgsConstructor
@Data
@Entity
public class Car implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 347424201966421997L;

  @Column(name = "id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  @NotBlank(message = "name must not be blank")
  @NotNull(message = "name must be supplied")
  @Size(min = 1, max = 45, message = "name must be in between 1 and 45")
  private String name;

  @Column(name = "manufacturer_name")
  @JsonProperty("manufacturer-name")
  @NotBlank(message = "manufactuer-name must not be blank")
  @NotNull(message = "manufactuer-name must be supplied")
  @Size(min = 1, max = 45, message = "manufactuer-name must be in between 1 and 45")
  private String manufacturerName;

  @Column(name = "model")
  @NotBlank(message = "model must not be blank")
  @NotNull(message = "model must be supplied")
  @Size(min = 1, max = 45, message = "model must be in between 1 and 45")
  private String model;

  @Column(name = "manufacturing_year")
  @JsonProperty("manufacturing-year")
  @NotNull(message = "manufacturing-year must be supplied")
  private Integer manufacturingYear;

  @Column(name = "color")
  @NotBlank(message = "color must not be blank")
  @NotNull(message = "color must be supplied")
  @Size(min = 1, max = 45, message = "color must be in between 1 and 45")
  private String color;

  // TODO: Need to replace with @SomeArgsConstructor annotation once it is available
  /**
   * @param name
   * @param manufacturerName
   * @param model
   * @param manufacturingYear
   * @param color
   */
  public Car(String name, String manufacturerName, String model, Integer manufacturingYear,
      String color) {
    this.name = name;
    this.manufacturerName = manufacturerName;
    this.model = model;
    this.manufacturingYear = manufacturingYear;
    this.color = color;
  }

}
