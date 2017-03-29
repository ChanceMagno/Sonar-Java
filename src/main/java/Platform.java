import org.sql2o.*;
import java.util.List;

public class Platform {

  private int id;
  private String name;

  public Platform(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static List<Platform> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM platforms;";
      return con.createQuery(sql).executeAndFetch(Platform.class);
    }
  }
}
