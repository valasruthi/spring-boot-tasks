/*
package com.stackroute.muzixtrack.seeddata;


import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

// It will get execute, after the server get starts

@Component
//Property source annotation is used to externalize configuration to a property file.
@PropertySource("classpath:application.properties")
public class CommandLineRunnerImpl implements CommandLineRunner {
  //    Value annotation is used for injecting values into fields in Spring-managed beans
  @Value("${track.id}")
  private int id;
  @Value("${track.name}")
  private String name;
  @Value("${track.comment}")
  private String comment;
  Track track = new Track();
  private TrackRepository trackRepository;

  //    constructor
  @Autowired
  public CommandLineRunnerImpl(TrackRepository trackRepository) {
    this.trackRepository = trackRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    track.setId(id);
    track.setName(name);
    track.setComment(comment);
    trackRepository.save(track);
  }

}
*/
