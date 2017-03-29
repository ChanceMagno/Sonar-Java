import org.sql2o.*;
import java.util.List;

public class Gamer {
  private String name;
  private int id;

  public Gamer(String name) {
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Gamer> all(){
    try(Connection con= DB.sql2o.open()){
      String sql = "SELECT * FROM gamers";
      return con.createQuery(sql).executeAndFetch(Gamer.class);
    }
  }

  public void save(){
    try(Connection con= DB.sql2o.open()){
      String sql = "INSERT INTO gamers (name)  VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public boolean equals(Object otherGamer){
  if (!(otherGamer instanceof Gamer)) {
    return false;
  } else {
    Gamer newGamer = (Gamer) otherGamer;
    return this.getName().equals(newGamer.getName()) && this.getId() == newGamer.getId();
    }
  }

  public static Gamer find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM gamers WHERE id = :id;";
      Gamer gamer = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Gamer.class);
      return gamer;
    }
  }
}
