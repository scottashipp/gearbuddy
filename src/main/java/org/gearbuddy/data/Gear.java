package org.gearbuddy.data;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gear {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String type;
  private String brand;
  private String model;
  private String color;
  private String size;
  private String purchasePlace;
  private String purchasePrice;
  private LocalDate purchaseDate;
  private String warranty;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getPurchasePlace() {
    return purchasePlace;
  }

  public void setPurchasePlace(String purchasePlace) {
    this.purchasePlace = purchasePlace;
  }

  public String getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(String purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public String getWarranty() {
    return warranty;
  }

  public void setWarranty(String warranty) {
    this.warranty = warranty;
  }

  @Override
  public String toString() {
    return "Gear{"
        + "id=" + id
        + ", type='" + type + '\''
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
