import org.sql2o.*;
import java.util.List;

public class Event {
  private int id;
  private String event_name;
  private String game_name;
  private int platform_id;
  private int max_players;
  private int event_creator_id;
  private String date;
  private String time;

  public Event(String event_name, String game_name, int platform_id, int max_players, String date, String time, int event_creator_id) {
    this.event_name = event_name;
    this.game_name = game_name;
    this.platform_id = platform_id;
    this.max_players = max_players;
    this.event_creator_id = event_creator_id;
    this.date = date;
    this.time = time;
  }

  public String getEventName() {
    return event_name;
  }

  public String getGameName() {
    return game_name;
  }

  public int getPlatformId() {
    return platform_id;
  }

  public int getMaxPlayers() {
    return max_players;
  }

  public String getDate() {
    return date;
  }

  public String getTime() {
    return time;
  }

  public int getEventCreatorId() {
    return event_creator_id;
  }

  public int getId() {
    return id;
  }

  public static List<Event> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM events;";
      return con.createQuery(sql).executeAndFetch(Event.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO events (event_name, game_name, platform_id, date, time, max_players, event_creator_id) VALUES (:event_name, :game_name, :platform_id, CAST(:date AS date), CAST(:time AS time), :max_players, :event_creator_id);";

      this.id = (int) con.createQuery(sql, true)
        .addParameter("event_name", this.event_name)
        .addParameter("game_name", this.game_name)
        .addParameter("platform_id", this.platform_id)
        .addParameter("date", this.date)
        .addParameter("time", this.time)
        .addParameter("max_players", this.max_players)
        .addParameter("event_creator_id", this.event_creator_id)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public boolean equals(Object otherEvent) {
    if(!(otherEvent instanceof Event)) {
      return false;
    } else {
      Event newEvent = (Event) otherEvent;
      return this.getEventName().equals(newEvent.getEventName())
        && this.getGameName().equals(newEvent.getGameName())
        && this.getPlatformId() == newEvent.getPlatformId()
        && this.getMaxPlayers() == newEvent.getMaxPlayers()
        && this.getDate().equals(newEvent.getDate())
        && this.getTime().equals(newEvent.getTime())
        && this.getEventCreatorId() == newEvent.getEventCreatorId();
    }
  }

  public static Event find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM events WHERE id = :id;";
      Event event = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Event.class);
      return event;
    }
  }

}
