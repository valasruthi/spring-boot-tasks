package com.stackroute.muzixtrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2  //Swagger2 is an open source project used to generate the REST API documents for RESTful web services.
public class MuzixTrackApplication {

  public static void main(String[] args) {

    SpringApplication.run(MuzixTrackApplication.class, args);
  }

}
