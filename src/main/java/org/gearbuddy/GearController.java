package org.gearbuddy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.gearbuddy.data.Gear;
import org.gearbuddy.data.GearRepository;
import org.gearbuddy.requests.PostGearRequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GearController {

  private static final Logger log = LoggerFactory.getLogger(GearController.class);

  private GearRepository repo;

  @Autowired
  public GearController(GearRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/gear")
  ResponseEntity<List<Gear>> getAll() {
    List<Gear> allGear = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
    return ResponseEntity.ok(allGear);
  }

  @PostMapping("/gear")
  public @ResponseBody
  ResponseEntity<Gear> addNewGear(@RequestBody PostGearRequestBody requestBody) {
    log.info("Received requestBody: " + requestBody);
    Gear newGear = new Gear();
    newGear.setType(requestBody.getType());
    newGear.setBrand(requestBody.getBrand());
    newGear.setColor(requestBody.getColor());
    newGear.setModel(requestBody.getModel());
    newGear.setPurchaseDate(requestBody.getPurchaseDate());
    newGear.setPurchasePrice(requestBody.getPurchasePrice());
    newGear.setPurchasePlace(requestBody.getPurchasePlace());
    newGear.setSize(requestBody.getSize());
    newGear.setWarranty(requestBody.getWarranty());
    Gear result = repo.save(newGear);
    log.info("Saved " + result);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/gear/{id}")
  public ResponseEntity<Gear> getAllForBrand(@PathVariable("id") long id) {
    log.info("Retrieving gear id: " + id);
    Optional<Gear> gear = repo.findById(id);
    return gear.map(g -> ResponseEntity.ok().body(g)).orElse(ResponseEntity.notFound().build());
  }

}
