package org.gearbuddy;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import java.util.Optional;
import org.gearbuddy.data.Gear;
import org.gearbuddy.data.GearRepository;
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
@OverrideAutoConfiguration(enabled=true)
public class GearControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  GearRepository gearRepository;

  @Before
  public void setup() {
    Gear gear = new Gear();
    gear.setId(1);
    gear.setBrand("MSR");
    gear.setModel("PocketRocket 2");
    gear.setType("Stove");
    given(this.gearRepository.findById(1L)).willReturn(Optional.ofNullable(gear));
  }

  @Test
  public void testGetGear() throws Exception {
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



}