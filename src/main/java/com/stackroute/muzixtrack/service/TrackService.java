package com.stackroute.muzixtrack.service;

import com.stackroute.muzixtrack.domain.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
  public Track save(Track track);
  public Track getById(int id);
  public List<Track> getAllTracks();
   public Optional<Track> deleteTrackById(int id);
 public Track updateTrack(int id,Track track);
}
