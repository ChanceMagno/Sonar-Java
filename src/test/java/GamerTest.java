import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GamerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void gameInstantiatesCorrectly_true() {
    Gamer test = new Gamer();
    assertEquals(true, test instanceof Gamer);
  }
}
