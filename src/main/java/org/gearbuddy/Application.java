package org.gearbuddy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @RequestMapping("/")
  public ResponseEntity<String> home() {
    log.info("home called; returning welcome message");
    return ResponseEntity.ok().body("Welcome to Gear Buddy!");
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
