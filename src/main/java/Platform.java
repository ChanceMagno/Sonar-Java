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

  public String getPlatformName() {
    return name;
  }

  @Override
  public boolean equals(Object otherPlatform) {
    if(!(otherPlatform instanceof Platform)) {
      return false;
    } else {
      Platform newPlatform = (Platform) otherPlatform;
      return this.getId() == newPlatform.getId() && this.getPlatformName().equals(newPlatform.getPlatformName());
    }
  }

  public static List<Platform> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM platforms;";
      return con.createQuery(sql).executeAndFetch(Platform.class);
    }
  }

  public static Platform find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM platforms WHERE id = :id;";
      Platform platform = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Platform.class);
      return platform;
    }
  }
}
