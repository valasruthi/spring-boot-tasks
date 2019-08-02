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
    try {
      Track savedTrack = trackService.save(track);
      return new ResponseEntity<>(savedTrack, HttpStatus.OK);
    } catch (TrackAlreadyExistsException ex) {
      responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
      return responseEntity;
    }
  }

  @GetMapping("track/{id}")
  //to get the track by id
  public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {
    ResponseEntity responseEntity;
    System.out.println(id);
    try {
      Track trackDetails = trackService.getById(id);
      return new ResponseEntity<>(trackDetails, HttpStatus.CREATED);
    } catch (TrackNotFoundException exe) {
      responseEntity = new ResponseEntity(exe.getMessage(), HttpStatus.NOT_FOUND);
      exe.printStackTrace();
    }
    return responseEntity;

  }

  @GetMapping("tracks")
  //to get the List of track
  public ResponseEntity<?> getAllTracks() throws Exception {
    ResponseEntity responseEntity;
    try {
      responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.CREATED);
    } catch (Exception e) {
      responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
      e.printStackTrace();
    }
    return responseEntity;
  }

  @DeleteMapping("track/{id}")
  //to delete the track
  public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) throws TrackNotFoundException {
    ResponseEntity responseEntity;
    try {
      Track tracksList = (Track) trackService.deleteTrackById(id);
      return new ResponseEntity<>(tracksList, HttpStatus.OK);
    } catch (TrackNotFoundException ex) {
      responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    return responseEntity;
  }

  @PutMapping("track/{id}")
  //to update the track
  public ResponseEntity<?> updateTrackById(@PathVariable int id,@RequestBody Track track) throws TrackNotFoundException {
    ResponseEntity responseEntity;
    try {
      Track trackUpdated = trackService.updateTrack(id,track);
      responseEntity = new ResponseEntity<>(trackUpdated, HttpStatus.UPGRADE_REQUIRED);
    } catch (TrackNotFoundException ex) {
      responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
      ex.printStackTrace();
    }
    return responseEntity;
  }

  @GetMapping("tracks/{name}")
  //to get the track by name
  public ResponseEntity<?> getTrackByName(@PathVariable String name) throws Exception {
    ResponseEntity responseEntity;
    System.out.println(name);
    try {
      List<Track> trackDetails = trackService.trackByName(name);
      responseEntity = new ResponseEntity<>(trackDetails, HttpStatus.OK);
    } catch (Exception ae) {
      responseEntity = new ResponseEntity(ae.getMessage(), HttpStatus.NOT_FOUND);
      ae.printStackTrace();
    }
    return responseEntity;
  }
}
