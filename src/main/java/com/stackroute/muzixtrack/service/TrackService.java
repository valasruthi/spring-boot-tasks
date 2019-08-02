package com.stackroute.muzixtrack.service;

import com.stackroute.muzixtrack.domain.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
  public Track save(Track track);//post the track with track obj

  public Track getById(int id);//method for getting an id

  public List<Track> getAllTracks();//methods for getting all  tracks

  public Optional<Track> deleteTrackById(int id);//methods for deleting the tracks

  public Track updateTrack(int id, Track track);//updating the tracks

  public List<Track> trackByName(String name);
}
