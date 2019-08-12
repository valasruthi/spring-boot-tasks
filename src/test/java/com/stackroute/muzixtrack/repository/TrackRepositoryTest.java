package com.stackroute.muzixtrack.repository;

import com.stackroute.muzixtrack.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

// It is used to provide a bridge between Spring Boot test features and JUnit.
@RunWith(SpringRunner.class)
//@DataJPATest provides the following features
//Configure in-memory test database
//Auto scan @Entity classes
//Auto configure Spring Data JPA, Hibernate and Data Source
//@DataJpaTest
@SpringBootTest
public class TrackRepositoryTest {
  @Autowired
  TrackRepository trackRepository;
  Track track;

  @Before
  public void setUp() throws Exception {
    track = new Track();
    track.setName("sruthi");
    track.setComment("great");
    track.setId(62);
  }

  @After
  public void tearDown() throws Exception {
    trackRepository.deleteAll();
  }

  @Test
  public void givenNameShouldReturnTrackDetails() {
    Track track = new Track(63, "chinni", "great");
    trackRepository.save(track);
    List<Track> list = trackRepository.findAll();
    Assert.assertEquals("chinni", list.get(0).getName());
  }

  @Test
  public void givenIdShouldReturnTrackDetails() {
    trackRepository.save(track);
    Track fetchTrack = trackRepository.findById(track.getId());
    Assert.assertEquals(62, fetchTrack.getId());

  }

  @Test
  public void givenIdShouldReturnTrackFailure() {
    trackRepository.save(track);
    Track fetchTrack = trackRepository.findById(track.getId());
    Assert.assertNotSame(40, fetchTrack.getId());
  }
}
