package com.stackroute.muzixtrack.service;

import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.exception.TrackAlreadyExistsException;
import com.stackroute.muzixtrack.exception.TrackNotFoundException;
import com.stackroute.muzixtrack.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("prod")

@Service


/*  Qualifier annotation is used to resolve the autowiring conflict,
    when there are multiple beans of same type "Primary" annotation will execute
*/
@Qualifier
public class TrackDummyServiceImplementation implements TrackService {
  private TrackRepository trackRepository;

  @Override
  public Track save(Track track) throws TrackAlreadyExistsException {
    System.out.println("save track");
    return null;
  }

  @Override
  public Track getById(int id) throws TrackNotFoundException {
    System.out.println("get track by id");
    return null;
  }

  @Override
  public List<Track> getAllTracks() {
    System.out.println("retrieve tracks");
    return null;
  }

  @Override
  public Track deleteTrackById(int id) throws TrackNotFoundException {
    System.out.println("delete track");
    return null;
  }

  @Override
  public Track updateTrack(int id, Track track) throws TrackNotFoundException {
    System.out.println("update track");
    return null;
  }

  @Override
  public List<Track> findByName(String name) throws Exception {
    System.out.println("track by name");
    return null;
  }

}
