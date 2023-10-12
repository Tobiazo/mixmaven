package json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import core.Drink;

public class DataHandlerTest {

    private static File testfile;
    private static List<Drink> testDrinks;

    /**
     * Sets up the test class by creating a new empty file for testing.
     */
    @BeforeAll
    public static void setUpClass() throws IOException {
        // testfile = File.createTempFile("test", ".json", new File("src/test/java/json/"));
        testfile = new File("src/test/java/json/abc.json");
        FileWriter writer = new FileWriter(testfile);
        writer.write("[]");
        writer.close();

        testDrinks = new ArrayList<>();
        testDrinks.add(new Drink("testDrink1"));
        testDrinks.add(new Drink("testDrink2"));
    }

    /**
     * Tests loading drinks from file, when the list of drinks is empty.
     */
    @Test
    public void testLoadDrinks() {
        DataHandler.loadDrinks(testfile);
        List<Drink> loadeDrinks = DataHandler.getDrinks();
        assertNotNull(loadeDrinks);
        assertEquals(0, loadeDrinks.size());
    }

    /**
     * Tests adding a drink to file. Tests removing the drink, that was added, from file.
     */
    @Test
    public void testAddAndRemoveDrink() {
        DataHandler.addDrink(new Drink("newAddDrink"), testfile);
        List<Drink> updatedDrinks = DataHandler.getDrinks();
        assertEquals(1, updatedDrinks.size());
        assertTrue(updatedDrinks.get(0).getName().equals("newAddDrink"));

        DataHandler.removeDrink(0);
        List<Drink> updatedDrink2 = DataHandler.getDrinks();
        assertEquals(0, updatedDrink2.size());
    }

    /**
     * Tests the method replace drink. newDrink should replace oldDrink
     */
    @Test
    public void testReplaceDrink() {
        DataHandler.addDrink(new Drink("oldDrink"), testfile);
        DataHandler.replaceDrink(0, new Drink("newDrink"));
        List<Drink> updatedDrinks = DataHandler.getDrinks();
        assertEquals(1, updatedDrinks.size());
        assertTrue(updatedDrinks.get(0).getName().equals("newDrink"));

        DataHandler.removeDrink(0);
    }

}
