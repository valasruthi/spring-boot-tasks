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
  public ResponseEntity<?> savedTrack(@RequestBody Track track) throws TrackAlreadyExistsException,HttpServerErrorException.InternalServerError {
    ResponseEntity responseEntity;
      Track savedTrack = trackService.save(track);
      return new ResponseEntity<>(savedTrack, HttpStatus.CREATED);
    }

  @GetMapping("track/{id}")
  //to get the track by id
  public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException,HttpServerErrorException.InternalServerError{
    ResponseEntity responseEntity;
      Track trackDetails = trackService.getById(id);
      return new ResponseEntity<>(trackDetails, HttpStatus.OK);
    }
  @GetMapping("tracks")
  //to get the List of track
  public ResponseEntity<?> getAllTracks() throws Exception,HttpServerErrorException.InternalServerError {
    ResponseEntity responseEntity;
      return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
    }


  @DeleteMapping("track/{id}")
  //to delete the track
  public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) throws TrackNotFoundException,HttpServerErrorException.InternalServerError {
    ResponseEntity responseEntity;
      Track tracksList = (Track) trackService.deleteTrackById(id);
      return new ResponseEntity<>(tracksList, HttpStatus.CONFLICT);
    }
    @PutMapping("tracks/{id}")
  //to update the track
  public ResponseEntity<?> updateTrackById(@PathVariable int id,@RequestBody Track track) throws TrackNotFoundException,HttpServerErrorException.InternalServerError{
    ResponseEntity responseEntity;
      Track trackUpdated = trackService.updateTrack(id,track);
      return new ResponseEntity<>(trackUpdated, HttpStatus.OK);
    }

  @GetMapping("tracks/{name}")
  //to get the track by name
  public ResponseEntity<?> getTrackByName(@PathVariable String name) throws Exception,HttpServerErrorException.InternalServerError {
    ResponseEntity responseEntity;
      List<Track> trackDetails = trackService.findByName(name);
      return new ResponseEntity<>(trackDetails, HttpStatus.OK);
    }
}
