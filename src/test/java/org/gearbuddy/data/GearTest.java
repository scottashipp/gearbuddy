package org.gearbuddy.data;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Test;

public class GearTest {

  @Test
  public void testSettersAndGettersAgree() {
    /**
     *
     * {
     *   "purchaseDate": "2016-07-22",
     *   "color": "Green / Gray",
     *   "size": "8-person",
     *   "purchasePlace": "B&H PhotoVideo Outdoors",
     *   "warranty": "None",
     *   "model": "Montana",
     *   "purchasePrice": "$144.95",
     *   "type": "Tent",
     *   "brand": "Coleman"
     * }
     *
     */
    Gear g = new Gear();
    g.setId(1L);
    g.setType("Tent");
    g.setBrand("Coleman");
    g.setModel("Montana");
    g.setColor("Green / Gray");
    g.setSize("8-person");
    g.setPurchaseDate(LocalDate.of(2016, 7,22));
    g.setPurchasePrice("$144.95");
    g.setPurchasePlace("B&H PhotoVideo Outdoors");
    g.setWarranty("None");
    assertEquals("Tent", g.getType());
    assertEquals("Coleman", g.getBrand());
    assertEquals("Montana", g.getModel());
    assertEquals("Green / Gray", g.getColor());
    assertEquals("8-person", g.getSize());
    assertEquals("B&H PhotoVideo Outdoors", g.getPurchasePlace());
    assertEquals("$144.95", g.getPurchasePrice());
    assertEquals("None", g.getWarranty());
  }
}
