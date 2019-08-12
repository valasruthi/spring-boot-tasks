package com.stackroute.muzixtrack.service;


import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.exception.TrackAlreadyExistsException;
import com.stackroute.muzixtrack.exception.TrackNotFoundException;
import com.stackroute.muzixtrack.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
 private Track track;

  //Create a mock for UserRepository
  @Mock
  TrackRepository trackRepository;

  //Inject the mocks as dependencies into UserServiceImpl
  @InjectMocks
  TrackServiceImpl trackService;
  List<Track> list = null;

  @Before
  public void setUp() throws Exception {
//Initialising the mock object
    MockitoAnnotations.initMocks(this);
    track = new Track();
    track.setId(10);
    track.setName("chinni");
    track.setComment("best");
    list = new ArrayList<>();
    list.add(track);
  }

  @After
  public void tearDown() throws Exception {
   track=null;
  }

  @Test
  public void saveTrackTestSuccess() throws TrackAlreadyExistsException {
    when(trackRepository.existsById(track.getId())).thenReturn(false);
    when(trackRepository.save((Track) any())).thenReturn(track);
    Track savedTrack = trackService.save(track);
    Assert.assertEquals(track,savedTrack);
    //verify here verifies that userRepository save method is only called once
    verify(trackRepository, Mockito.times(1)).save(track);

  }
  @Test
  public void getAllTracks() throws Exception {
    trackRepository.save(track);
    //stubbing the mock to return specific data
    when(trackRepository.findAll()).thenReturn(list);
    List<Track> trackList = trackService.getAllTracks();
    Assert.assertEquals(list, trackList);
   verify(trackRepository, Mockito.times(1)).findAll(track);
  }

  @Test(expected = TrackAlreadyExistsException.class)
  public void saveTrackTestFailure() throws TrackAlreadyExistsException {
    when(trackRepository.save(any())).thenReturn(null);
    Track savedTrack = trackService.save(track);
    System.out.println("savedTrack" + savedTrack);
   verify(trackRepository, Mockito.times(0)).save(track);

  }
  @Test
  public void givenIdShouldReturnTrack() throws TrackNotFoundException {
    trackRepository.save(track);
    //stubbing the mock to return specific data
    when(trackRepository.existsById(10)).thenReturn(true);
    when(trackRepository.findById(10)).thenReturn(track);
    Track gettrack = trackService.getById(10);
    Assert.assertEquals(track, gettrack);
   verify(trackRepository, Mockito.times(1)).findById(track);
  }
  @Test
  public void givenNameShouldReturnTrack() throws Exception{
    trackRepository.save(track);
    when(trackRepository.findByName(any())).thenReturn(list);
    List<Track> tracks=trackService.findByName("sruthi");
    Assert.assertEquals(list,tracks);
   verify(trackRepository, Mockito.times(1)).findByName(track);
  }
  @Test
  public void givenIdShouldReturnDeletedTrack() throws TrackNotFoundException{
    trackRepository.save(track);
    when(trackRepository.existsById(10)).thenReturn(true);
    when(trackRepository.findById(10)).thenReturn(track);
    Track deleteTrack=trackService.deleteTrackById(10);
    Assert.assertEquals(track,deleteTrack);
   verify(trackRepository, Mockito.times(1)).findById(track);
  }
@Test
  public void givenIdShouldUpdateTrack() throws TrackNotFoundException{
    trackRepository.save(track);
    when(trackRepository.existsById(10)).thenReturn(true);
    when(trackRepository.save(track)).thenReturn(track);
    Track updateTrack=trackService.updateTrack(10,track);
    Assert.assertEquals(track,updateTrack);
   verify(trackRepository, Mockito.times(1)).save(track);
}
}

