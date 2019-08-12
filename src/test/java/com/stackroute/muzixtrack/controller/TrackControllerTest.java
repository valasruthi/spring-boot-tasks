package com.stackroute.muzixtrack.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.exception.GlobalException;
import com.stackroute.muzixtrack.exception.TrackAlreadyExistsException;
import com.stackroute.muzixtrack.service.TrackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
// It is used to provide a bridge between Spring Boot test features and JUnit.
@RunWith(SpringRunner.class)
//Annotation that can be used in combination with @RunWith(SpringRunner.class) for a typical Spring MVC test. Can be used when a test focuses only on Spring MVC components.
@WebMvcTest
public class TrackControllerTest {

  @Autowired
  private MockMvc mockMvc;
  private Track track;
//Annotation that can be used to add mocks to a Spring ApplicationContext. Can be used as a class level annotation or on fields in either @Configuration classes, or test classes that are @RunWith the SpringRunner.
  @MockBean
  private TrackService trackService;
  //@InjectMock creates an instance of the class and injects the mocks that are marked with the annotations @Mock into it.
  @InjectMocks
  private TrackController trackController;

  private List<Track> list =null;

  @Before
  public void setUp(){

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(new GlobalException()).build();

    track = new Track();
    track.setName("sruthi");
    track.setId(10);
    track.setComment("good");
    
    track1 = new Track();
    track1.setName("chinni");
    track1.setId(-10);
    track1.setComment("good");
    list = new ArrayList();

    list.add(track);
  }
 @After
  public void tearDown() throws Exception {
   track=null;
  }
  
//it is used for saving the track
  @Test
  public void saveTrack() throws Exception {
    when(trackService.save(any())).thenReturn(track);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());


  }
  @Test
  public void givenUrlShouldSaveUserFailure() throws Exception {
    when(trackService.save(any())).thenThrow(TrackAlreadyExistsException.class);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isConflict())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void givenUrlShouldReturnAllTracks() throws Exception {
    when(trackService.getAllTracks()).thenReturn(list);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());

  }
  @Test
  public void givenIdShouldDeleteTrack() throws Exception {
    when(trackService.deleteTrackById(anyInt())).thenReturn(track);
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/10")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isConflict())
      .andDo(MockMvcResultHandlers.print());
  }
  @Test
  public void givenNameShouldGetTrack() throws Exception{
    when(trackService.findByName(anyString())).thenReturn(list);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/sruthi")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }
  @Test
  public void givenIdShouldGetTrack() throws Exception{
    when(trackService.getById(anyInt())).thenReturn(track);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/10")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }
  @Test
  public void givenInputShouldUpdateTrack() throws Exception{
   when(trackService.updateTrack(track)).thenReturn(track);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/10")
     .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
 }
  
  @Test
  public void givenIdShouldGetNegativeTrack() throws Exception{
    when(trackService.getById(anyInt())).thenReturn(track);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/-10")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }

  private static String asJsonString(final Object obj)
  {
    try{
      return new ObjectMapper().writeValueAsString(obj);

    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
}
