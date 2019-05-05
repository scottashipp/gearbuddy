package org.gearbuddy.data;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("integrationtest")
public class GearRepositoryTest {

  @Autowired
  private GearRepository repo;

  @Before
  public void setup() {

  }

  @Test
  public void testAddGear() {
    assertEquals(0, repo.count());

    saveGear("Stove", "MSR", "PocketRocket 2");

    Optional<Gear> result = repo.findById(1L);
    assertTrue(result.isPresent());
    result.ifPresent(res -> {
      assertEquals("MSR", res.getBrand());
      assertEquals("PocketRocket 2", res.getModel());
    });
  }

  private void saveGear(String type, String brand, String model) {
    Gear g = new Gear();
    g.setType(type);
    g.setBrand(brand);
    g.setModel(model);
    repo.save(g);
  }
}
