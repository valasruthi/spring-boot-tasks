package com.stackroute.muzixtrack.service;

import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.exception.TrackAlreadyExistsException;
import com.stackroute.muzixtrack.exception.TrackNotFoundException;
import com.stackroute.muzixtrack.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@Primary
public class TrackServiceImpl implements TrackService {
  private TrackRepository trackRepository;

  @Autowired
  public TrackServiceImpl(TrackRepository trackRepository) {

    this.trackRepository = trackRepository;
  }

  @Override
  public Track save(Track track) throws TrackAlreadyExistsException {
    if (trackRepository.existsById(track.getId())) {
      throw new TrackAlreadyExistsException("track already exist");
    }
    Track savedTrack = trackRepository.save(track);
    if (savedTrack == null) {
      throw new TrackAlreadyExistsException("track is null");
    }
    return savedTrack;
  }

  @Override
  public Track getById(int id) throws TrackNotFoundException {
    if (!trackRepository.existsById(id)) {
      throw new TrackNotFoundException("track not found");
    }
    Track retriveTrack = trackRepository.findById(id);
    return retriveTrack;
  }


  @Override
  public List<Track> getAllTracks() throws Exception {
    if (trackRepository.findAll().isEmpty()) {
      throw new Exception("Internal server error");
    } else {
      List<Track> allTracks = trackRepository.findAll();
      return allTracks;
    }


  }

  @Override
  public Track deleteTrackById(int id) throws TrackNotFoundException {
    if (trackRepository.existsById(id)) {
      Track retrivedTrack = trackRepository.findById(id);
      trackRepository.deleteById(id);
      return retrivedTrack;
    } else {
      throw new TrackNotFoundException("Track not found");
    }
  }

  @Override
  //update a particular  part of a track by its id
  public Track updateTrack(int id, Track track) throws TrackNotFoundException {
//        delete the track
    if (trackRepository.existsById(id)) {
      Track updateTrackById = trackRepository.save(track);
      return updateTrackById;
    } else {
      throw new TrackNotFoundException("track not found");
    }
  }

  @Profile("dev")
  //get the track by name
  @Override
  public List<Track> findByName(String name) throws Exception {
    if (trackRepository.findByName(name).isEmpty()) {
      throw new Exception("no track with this name");
    } else {
      List<Track> allTracksByName = trackRepository.findByName(name);
      return allTracksByName;
    }
  }

}

