package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

@SuppressWarnings("magicnumber")
public class IngredientTest {

    /**
     * Tests creating a new drink without alcohol.
     */
    @Test
    public void testConstructorForMixerAndExtras() {
        Ingredient ingredient = new Ingredient("lime", 1, "ml", "extras");
        assertTrue(ingredient.getName().equals("lime"));
        assertEquals(1, ingredient.getAmount());
        assertTrue(ingredient.getUnit().equals("ml"));
        assertTrue(ingredient.getType().equals("extras"));
    }

    /**
     * Tests creating a new drink with alcohol.
     */
    @Test
    public void testConstructorForAlcohol() {
        Ingredient ingredient = new Ingredient("vodka", 40, 4, "ml", "alcohol");
        assertTrue(ingredient.getName().equals("vodka"));
        assertEquals(40, ingredient.getAlcoholPercentage());
        assertEquals(4, ingredient.getAmount());
        assertTrue(ingredient.getUnit().equals("ml"));
        assertTrue(ingredient.getType().equals("alcohol"));
    }

    /**
     * Test creating new drink with invalid type.
     * Should throw illegalArgumentException.
     * @throws IllegalArgumentException
     */
    @Test
    public void testInvalidType() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class,
                () -> new Ingredient("invalid", 1, "ml", "invalidType"));


    }

    /**
     * Test creating new drink with invalid alcohol percentage.
     * You should not be able to add an ingredient with alcohol over 100%.
     * Should throw illegalArgumentException.
     * @throws IllegalArgumentException
     */
    @Test
    public void testInvalidAlcoholPercentage() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class,
                () -> new Ingredient("invalid", 120, 1, "ml", "invalidType"));
    }
}
