package com.stackroute.muzixtrack.seeddata;

import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


public class CommandLineRunnerImpl implements CommandLineRunner {
private TrackRepository trackRepository;
@Autowired
  public CommandLineRunnerImpl(TrackRepository trackRepository) {
    this.trackRepository = trackRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Track track1=new Track(1,"sruthi","nice");
    trackRepository.save(track1);
    Track track2=new Track(3,"vala","nice");
    trackRepository.save(track2);
  }
}

