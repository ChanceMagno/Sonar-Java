import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PlatformTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void getAllPlatforms_true() {
    assertTrue("PC".equals(Platform.all().get(0).getName()));
  }

  @Test
  public void find_returnsPlatformWithSameID_true() {
    assertEquals(Platform.find(1).getName(), "PC");
  }
}
