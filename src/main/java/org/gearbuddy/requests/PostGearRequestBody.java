package org.gearbuddy.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class PostGearRequestBody {

  private final String type;
  private final String brand;
  private final String model;
  private final String color;
  private final String size;
  private final String purchasePlace;
  private final String purchasePrice;
  private final LocalDate purchaseDate;
  private final String warranty;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public PostGearRequestBody(@JsonProperty("type") String type, @JsonProperty("brand") String brand,
      @JsonProperty("model") String model, @JsonProperty("color") String color,
      @JsonProperty("size") String size, @JsonProperty("purchasePlace") String purchasePlace,
      @JsonProperty("purchasePrice") String purchasePrice,
      @JsonProperty("purchaseDate") LocalDate purchaseDate,
      @JsonProperty("warranty") String warranty) {
    this.type = type;
    this.brand = brand;
    this.model = model;
    this.color = color;
    this.size = size;
    this.purchasePlace = purchasePlace;
    this.purchasePrice = purchasePrice;
    this.purchaseDate = purchaseDate;
    this.warranty = warranty;
  }

  public String getType() {
    return type;
  }

  public String getBrand() {
    return brand;
  }

  public String getModel() {
    return model;
  }

  public String getColor() {
    return color;
  }

  public String getSize() {
    return size;
  }

  public String getPurchasePlace() {
    return purchasePlace;
  }

  public String getPurchasePrice() {
    return purchasePrice;
  }

  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }

  public String getWarranty() {
    return warranty;
  }

  @Override
  public String toString() {
    return "PostGearRequestBody{"
        + "type='" + type + '\''
        + ", brand='" + brand + '\''
        + ", model='" + model + '\''
        + ", color='" + color + '\''
        + ", size='" + size + '\''
        + ", purchasePlace='" + purchasePlace + '\''
        + ", purchasePrice='" + purchasePrice + '\''
        + ", purchaseDate=" + purchaseDate
        + ", warranty='" + warranty + '\''
        + '}';
  }

}
