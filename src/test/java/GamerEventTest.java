import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;


public class GamerEventTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void classInstantiates_true() {
    GamerEvent newGamerEvent = new GamerEvent(1, 2);
    assertTrue(newGamerEvent instanceof GamerEvent);
  }

  @Test
  public void classInstantiateswithMembers_true() {
    GamerEvent newGamerEvent = new GamerEvent(1, 2);
    assertEquals(1, newGamerEvent.getGamerId());
    assertEquals(2, newGamerEvent.getEventId());
  }

  @Test
  public void all_getAllEvents_true() {
    GamerEvent newGamerEvent1 = new GamerEvent(1, 2);
    newGamerEvent1.save();
    GamerEvent newGamerEvent2 = new GamerEvent(2, 2);
    newGamerEvent2.save();
    assertTrue(newGamerEvent1.equals(GamerEvent.all().get(0)));
    assertTrue(newGamerEvent2.equals(GamerEvent.all().get(1)));
  }

  @Test
  public void save_returnsTrueIfNamesAreTheSame_true() {
    GamerEvent newGamerEvent = new GamerEvent(1, 2);
    newGamerEvent.save();
    assertTrue(GamerEvent.all().get(0).equals(newGamerEvent));
  }

  @Test
  public void find_returnsGamerEventWithSameId_newGamerEvent2() {
    GamerEvent newGamerEvent1 = new GamerEvent(1, 2);
    newGamerEvent1.save();
    GamerEvent newGamerEvent2 = new GamerEvent(2, 2);
    newGamerEvent2.save();
    assertEquals(GamerEvent.find(newGamerEvent2.getId()), newGamerEvent2);
  }

}
