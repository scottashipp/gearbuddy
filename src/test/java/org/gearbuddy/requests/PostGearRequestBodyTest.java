package org.gearbuddy.requests;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;
import org.junit.Test;

public class PostGearRequestBodyTest {

  @Test
  public void testPostGearRequestBody() throws Exception {
    String request = new JSONObject()
        .put("type", "Tent")
        .put("brand", "Coleman")
        .put("model", "Montana")
        .put("color", "Green / Gray")
        .put("size", "8-person")
        .put("purchasePlace", "B&H PhotoVideo Outdoors")
        .put("purchasePrice", "$144.95")
        .put("purchaseDate", LocalDate.of(2016, 07, 22).format(DateTimeFormatter.ISO_DATE))
        .put("warranty", "None")
        .toString();
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    PostGearRequestBody req = mapper.readValue(request, PostGearRequestBody.class);
    assertEquals("Tent", req.getType());
    assertEquals("Coleman", req.getBrand());
    assertEquals("Montana", req.getModel());
    assertEquals("Green / Gray", req.getColor());
    assertEquals("8-person", req.getSize());
    assertEquals("B&H PhotoVideo Outdoors", req.getPurchasePlace());
    assertEquals("$144.95", req.getPurchasePrice());
    assertEquals("None", req.getWarranty());
  }

}
