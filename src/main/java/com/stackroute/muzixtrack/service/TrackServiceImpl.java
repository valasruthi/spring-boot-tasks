package com.stackroute.muzixtrack.service;

import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
  private TrackRepository trackRepository;

  @Autowired
  public TrackServiceImpl(TrackRepository trackRepository) {
    this.trackRepository = trackRepository;
  }

  @Override
  public Track save(Track track) {
    Track savedTrack = trackRepository.save(track);
    return savedTrack;
  }

  @Override
  public Track getById(int id) {
    Track retriveTrack = trackRepository.findById(id).get();
    return retriveTrack;
  }


  @Override
  public List<Track> getAllTracks() {
    return trackRepository.findAll();

  }

  @Override
  public Optional<Track> deleteTrackById(int id) {
    Optional<Track> optional = trackRepository.findById(id);
    trackRepository.deleteById(id);
    if (optional.isPresent()) {
    }
    return optional;
  }

  @Override
  //update a particular  part of a track by its id
  public Track updateTrack(int id, Track track) {
//        delete the track
    trackRepository.deleteById(id);
//        edit the track and save it
    Track updateTrackById = trackRepository.save(track);
    return updateTrackById;
  }

  //get the track by name
  @Override
  public List<Track> trackByName(String name) {
    return trackRepository.trackByName(name);
  }

}

