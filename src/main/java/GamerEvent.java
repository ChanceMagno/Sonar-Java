import org.sql2o.*;
import java.util.List;

public class GamerEvent {
  private int id;
  private int gamer_id;
  private int event_id;

  public GamerEvent(int gamerId, int eventId) {
    this.gamer_id = gamerId;
    this.event_id = eventId;
  }

  public int getGamerId() {
    return gamer_id;
  }

  public int getEventId() {
    return event_id;
  }

  public int getId() {
    return id;
  }

  public static List<GamerEvent> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM signups;";
      return con.createQuery(sql).executeAndFetch(GamerEvent.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO signups (gamer_id, event_id) VALUES(:gamer_id, :event_id);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("gamer_id", this.gamer_id)
        .addParameter("event_id", this.event_id)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public boolean equals(Object otherGamerEvent) {
    if(!(otherGamerEvent instanceof GamerEvent)) {
      return false;
    } else {
      GamerEvent newGamerEvent = (GamerEvent) otherGamerEvent;
      return this.getGamerId() == newGamerEvent.getGamerId() && this.getEventId() == newGamerEvent.getEventId();
    }
  }

  public static GamerEvent find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM signups WHERE id = :id;";
      GamerEvent gamerEvent = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(GamerEvent.class);
      return gamerEvent;
    }
  }
}
