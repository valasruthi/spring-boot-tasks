package muzixtrack.exception;

public class TrackNotFoundException extends Exception {

  private String exmessage;

  public TrackNotFoundException() {
  }

  public TrackNotFoundException(String exmessage) {
    super(exmessage);
    this.exmessage = exmessage;
  }
}
