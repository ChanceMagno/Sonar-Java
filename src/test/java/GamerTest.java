import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GamerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void gameInstantiatesCorrectly_true() {
    Gamer test = new Gamer("john");
    assertEquals(true, test instanceof Gamer);
  }

  @Test
  public void getName_name() {
    Gamer test = new Gamer("john");
    assertEquals("john", test.getName());
  }

  @Test
  public void getId_true() {
    Gamer test = new Gamer("john");
    test.save();
    assertTrue(test.getId()>0);
  }

  @Test
  public void all_getAllGamers_true() {
    Gamer test1 = new Gamer("john");
    test1.save();
    Gamer test2 = new Gamer("bill");
    test2.save();
    assertTrue(test1.equals(Gamer.all().get(0)));
    assertTrue(test2.equals(Gamer.all().get(1)));
  }

  @Test
  public void save_returnsTrueIfNamesAreTheSame_true() {
    Gamer test = new Gamer("john");
    test.save();
    assertTrue(Gamer.all().get(0).equals(test));
  }

  @Test
  public void find_returnsGamerWithSameId_test2() {
    Gamer test1 = new Gamer("john");
    test1.save();
    Gamer test2 = new Gamer("bill");
    test2.save();
    assertEquals(Gamer.find(test2.getId()), test2);
  }
}
