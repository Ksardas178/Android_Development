package name.ank.lab4;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class BibDatabaseTest {

  private BibDatabase openDatabase(String s) throws IOException {
    try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(s))) {
      return new BibDatabase(reader);
    }
  }

  @Test
  public void getFirstEntry() throws IOException {
    BibDatabase database = openDatabase("/references.bib");
    BibEntry first = database.getEntry(0);
    Assert.assertEquals(Types.ARTICLE, first.getType());
    Assert.assertEquals("The semantic web", first.getField(Keys.TITLE));
    Assert.assertNull("Field 'chapter' does not exist", first.getField(Keys.CHAPTER));
  }

  @Test
  public void normalModeDoesNotThrowException() throws IOException {
    BibDatabase database = openDatabase("/mixed.bib");
    BibConfig cfg = database.getCfg();
    cfg.strict = false;

    BibEntry first = database.getEntry(0);
    for (int i = 0; i < cfg.maxValid + 1; i++) {
      BibEntry unused = database.getEntry(0);
      assertNotNull("Should not throw any exception @" + i, first.getType());
    }
  }

  @Test
  public void canReadAllItemsFromMixed() throws IOException {
    BibDatabase database = openDatabase("/mixed.bib");

    for (int i = 0; i < database.size(); i++) {
      BibEntry entry = database.getEntry(i);
      assertNotNull(entry.getType());
    }
  }

  @Test
  public void strictModeThrowsException() throws IOException {
    BibDatabase database = openDatabase("/mixed.bib");
    BibConfig cfg = database.getCfg();
    cfg.strict = true;
    BibEntry first = database.getEntry(0);

    for (int i = 0; i < cfg.maxValid; i++) {
      database.getEntry(0);
      try {
        Assert.assertNotNull("Should not throw any exception @" + i, first.getType());
      } catch (IllegalStateException exp) {
        System.out.println("strictModeThrowsException IllegalStateException: " + exp.getMessage());
      }
    }
    try {
      BibEntry unused = database.getEntry(0);
      first.getType();
      fail();
    }
    catch (IllegalStateException e) {
      System.out.println("strictModeThrowsException IllegalStateException: " + e.getMessage());
    }
  }

    @Test
    public void shuffleFlag() {
      BibConfig cfg = new BibConfig();
      cfg.shuffle = false;
      boolean check = false;
      BibDatabase notMixed;
      BibDatabase mixed;

      for (int i = 0; i < 15 && !check; i++) {
        try (InputStreamReader reader =
                     new InputStreamReader(getClass().getResourceAsStream("/mixed.bib"))) {
          notMixed = new BibDatabase(reader, cfg);
          mixed = openDatabase("/mixed.bib");

          if (!mixed.getEntry(0).getField(Keys.AUTHOR)
                  .equals(notMixed.getEntry(0).getField(Keys.AUTHOR))) {
            check = true;
          }
        } catch (IOException exp) {
          System.out.println("shuffleFlag IOE: " + exp.getMessage());
        }
      }
      assertTrue(check);
    }
}
