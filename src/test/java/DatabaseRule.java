import org.sql2o.*;
import org.junit.rules.ExternalResource;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/sonar_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteEventQuery = "DELETE FROM events *;";
      String deleteGamerQuery = "DELETE FROM gamers *;";
      String deleteSignupsQuery = "DELETE FROM signups *;";
      con.createQuery(deleteEventQuery).executeUpdate();
      con.createQuery(deleteGamerQuery).executeUpdate();
      con.createQuery(deleteSignupsQuery).executeUpdate();
    }
  }
}
