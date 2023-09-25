package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DrinkTest {

    @Test
    public void testCalculateAlcoholContent() {
        Ingredient vodka = new Ingredient("Vodka", 40, 1, "dl", "alcohol");
        Ingredient juice = new Ingredient("Pinapple juice", 0, 1, "dl", "mixer");

        Drink pinappleHell = new Drink("pinappleHell");

        pinappleHell.addIngredient(vodka);
        pinappleHell.addIngredient(juice);

        assertEquals(0.2, pinappleHell.getAlcoholContent());

    }



}
