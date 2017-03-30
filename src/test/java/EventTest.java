import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class EventTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void classInstantiates_true() {
    Event event = new Event("halo tournement", "halo 2", 2, 16, "2017-03-30", "18:30:00", 1);
    assertTrue(event instanceof Event);
  }

  @Test
  public void eventInstantiateswithobjects_true() {
    Event event = new Event("halo tournement", "halo 2", 2, 16, "2017-03-30", "18:30:00", 1);
    assertEquals("halo tournement", event.getEventName());
    assertEquals("halo 2", event.getGameName());
    assertEquals(2, event.getPlatformId());
    assertEquals(16, event.getMaxPlayers());
    assertEquals("2017-03-30", event.getDate());
    assertEquals("18:30:00", event.getTime());
    assertEquals(1, event.getEventCreatorId());
  }

  @Test
  public void all_getAllEvents_true() {
    Event event1 = new Event("halo tournement", "halo 2", 2, 16, "2017-03-30", "18:30:00", 1);
    event1.save();
    Event event2 = new Event("battlefield", "battlefield 1", 2, 64, "2017-03-30", "18:30:00", 1);
    event2.save();
    assertTrue(event1.equals(Event.all().get(0)));
    assertTrue(event2.equals(Event.all().get(1)));
  }

  @Test
  public void save_returnsTrueIfNamesAreTheSame_trueEvent() {
    Event event = new Event("halo tournement", "halo 2", 2, 16, "2017-03-30", "18:30:00", 1);
    event.save();
    assertTrue(Event.all().get(0).equals(event));
  }

  @Test
  public void find_returnsEventWithSameId_test2() {
    Event event1 = new Event("halo tournement", "halo 2", 2, 16, "2017-03-30", "18:30:00", 1);
    event1.save();
    Event event2 = new Event("battlefield", "battlefield 1", 2, 64, "2017-03-30", "18:30:00", 1);
    event2.save();
    assertEquals(Event.find(event2.getId()), event2);
  }

  @Test
  public void getEventPlatformName_returnsEventPlatformName() {
    Event event1 = new Event("halo tournement", "halo 2", 2, 16, "2017-03-30", "18:30:00", 1);
    event1.save();
    Event event2 = new Event("battlefield", "battlefield 1", 2, 64, "2017-03-30", "18:30:00", 1);
    event2.save();
    assertEquals("Xbox One", event1.getEventPlatformName());
  }

}
