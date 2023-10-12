package core.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core.Drink;
import core.Ingredient;

public class UtilityJsonTest {

    private List<Drink> drinks;
    private File testfile;

    /**
     * Method that runs before test methods. Creates two drinks and adds them to the list of drinks.
     */
    @BeforeEach
    public void setUp() {
        testfile = new File("src/test/java/core/json/test.json");

        drinks = new ArrayList<>();

        List<Ingredient> ingredients1 = new ArrayList<>();
        ingredients1.add(new Ingredient("Vodka", 40, 4, "ml", "alcohol"));
        ingredients1.add(new Ingredient("Tonic", 8, "ml", "mixer"));
        Drink drink1 = new Drink("Vodka tonic", ingredients1);

        List<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(new Ingredient("Vodka", 40, 4, "ml", "alcohol"));
        ingredients2.add(new Ingredient("Orange juice", 8, "ml", "mixer"));
        Drink drink2 = new Drink("Screwdriver", ingredients2);

        drinks.add(drink1);
        drinks.add(drink2);
    }

    /**
     * Tests saving drinks to Json.
     */
    @Test
    public void saveObjectToJsonFileTest() {
        UtilityJson.saveObjectToJsonFile(drinks, testfile);

        List<Drink> loadedDrinks = UtilityJson.loadObjectFromJson(testfile);

        assertNotNull(loadedDrinks);
        assertEquals(drinks.size(), loadedDrinks.size());

        for (int i = 0; i < drinks.size(); i++) {
            Drink originalDrink = drinks.get(i);
            Drink loadedDrink = loadedDrinks.get(i);

            assertEquals(originalDrink.getName(), loadedDrink.getName());
            assertTrue(originalDrink.getIngredients().toString().equals(loadedDrink.getIngredients().toString()));
            assertEquals(originalDrink.getAlcoholContent(), loadedDrink.getAlcoholContent());
        }
    }
}