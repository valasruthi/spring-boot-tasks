package com.stackroute.muzixtrack.service;

import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.exception.TrackAlreadyExistsException;
import com.stackroute.muzixtrack.exception.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TrackService {
  public Track save(Track track)throws TrackAlreadyExistsException;//post the track with track obj

  public Track getById(int id)throws TrackNotFoundException;//method for getting an id

  public List<Track> getAllTracks()throws Exception;//methods for getting all  tracks

  public Track deleteTrackById(int id)throws TrackNotFoundException;//methods for deleting the tracks

  public Track updateTrack(int id,Track track)throws TrackNotFoundException;//updating the tracks

  public List<Track> findByName(String name)throws Exception;
}
