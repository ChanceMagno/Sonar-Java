import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;


public class GamerEventTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void classInstantiates_true() {
    GamerEvent event = new GamerEvent();
    assertTrue(event instanceof GamerEvent);
  }
}
