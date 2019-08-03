package com.stackroute.muzixtrack.controller;

import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.exception.TrackAlreadyExistsException;
import com.stackroute.muzixtrack.exception.TrackNotFoundException;
import com.stackroute.muzixtrack.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class TrackController {
  private TrackService trackService;

  public TrackController() {
  }

  @Autowired
  public TrackController(TrackService trackService) {
    this.trackService = trackService;
  }

  @PostMapping("track")
  //to post the track
  public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
    ResponseEntity responseEntity;
      Track savedTrack = trackService.save(track);
      return new ResponseEntity<>(savedTrack, HttpStatus.OK);
    }

  @GetMapping("track/{id}")
  //to get the track by id
  public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {
    ResponseEntity responseEntity;
    System.out.println(id);
      Track trackDetails = trackService.getById(id);
      return new ResponseEntity<>(trackDetails, HttpStatus.CREATED);
    }
  @GetMapping("tracks")
  //to get the List of track
  public ResponseEntity<?> getAllTracks() throws Exception {
    ResponseEntity responseEntity;
      return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.CREATED);
    }


  @DeleteMapping("track/{id}")
  //to delete the track
  public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) throws TrackNotFoundException {
    ResponseEntity responseEntity;
      Track tracksList = (Track) trackService.deleteTrackById(id);
      return new ResponseEntity<>(tracksList, HttpStatus.OK);
    }
    @PutMapping("track/{id}")
  //to update the track
  public ResponseEntity<?> updateTrackById(@PathVariable int id,@RequestBody Track track) throws TrackNotFoundException {
    ResponseEntity responseEntity;
      Track trackUpdated = trackService.updateTrack(id,track);
      return new ResponseEntity<>(trackUpdated, HttpStatus.UPGRADE_REQUIRED);
    }

  @GetMapping("tracks/{name}")
  //to get the track by name
  public ResponseEntity<?> getTrackByName(@PathVariable String name) throws Exception {
    ResponseEntity responseEntity;
    System.out.println(name);
      List<Track> trackDetails = trackService.trackByName(name);
      return new ResponseEntity<>(trackDetails, HttpStatus.OK);
    }
}
