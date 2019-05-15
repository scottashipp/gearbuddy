package org.gearbuddy;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.gearbuddy.data.Gear;
import org.gearbuddy.data.GearRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
@OverrideAutoConfiguration(enabled = true)
public class GearControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  GearRepository gearRepository;

  private Map<Long, Gear> gearMap;
  private AtomicLong id;

  @Before
  public void setup() {
    id = new AtomicLong(0);
    Gear gear = new Gear();
    gear.setId(id.incrementAndGet());
    gear.setBrand("MSR");
    gear.setModel("PocketRocket 2");
    gear.setType("Stove");
    gearMap = new HashMap<>();
    gearMap.put(gear.getId(), gear);
    given(this.gearRepository.save(any(Gear.class))).willAnswer((invocationOnMock) -> {
      Gear g = invocationOnMock.getArgument(0);
      g.setId(id.incrementAndGet());
      gearMap.put(g.getId(), g);
      return g;
    });
    given(this.gearRepository.findById(any(Long.class))).willAnswer(
        (invocationOnMock) -> Optional.ofNullable(gearMap.get(invocationOnMock.getArgument(0)))
    );
    given(this.gearRepository.findAll()).willAnswer((invocationOnMock) -> gearMap.values());
  }

  @Test
  public void testGetById() throws Exception {
    mvc.perform(get("/gear/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.brand", is("MSR")))
        .andExpect(jsonPath("$.model", is("PocketRocket 2")))
        .andExpect(jsonPath("$.type", is("Stove")));
  }

  @Test
  public void testGetNonExistentGear() throws Exception {
    mvc.perform(get("/gear/2"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testGetBadRequest() throws Exception {
    mvc.perform(get("/gear/msr"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testPostGear() throws Exception {
    String requestBody = new JSONObject()
        .put("type", "Sleeping Bag")
        .put("brand", "Kelty")
        .put("model", "Cosmic 20")
        .toString();
    mvc.perform(post("/gear").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(2)))
        .andExpect(jsonPath("$.type", is("Sleeping Bag")))
        .andExpect(jsonPath("$.brand", is("Kelty")))
        .andExpect(jsonPath("$.model", is("Cosmic 20")));
  }

  @Test
  public void testGetAll() throws Exception {
    String requestBody = new JSONObject()
        .put("type", "Water Shoes")
        .put("brand", "Keen")
        .put("model", "Seacamp II CNX Sandals - Kids")
        .put("color", "Steel Grey / Rapture Rose")
        .put("purchasePlace", "REI")
        .put("purchasePrice", "$54.95")
        .put("purchaseDate", LocalDate.of(2019, 4, 21))
        .put("warranty", "1 year limited")
        .toString();
    mvc.perform(post("/gear").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody))
        .andExpect(status().isOk());

    mvc.perform(get("/gear"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].type", is("Stove")))
        .andExpect(jsonPath("$[0].brand", is("MSR")))
        .andExpect(jsonPath("$[0].model", is("PocketRocket 2")))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].type", is("Water Shoes")))
        .andExpect(jsonPath("$[1].brand", is("Keen")))
        .andExpect(jsonPath("$[1].model", is("Seacamp II CNX Sandals - Kids")));


  }


}