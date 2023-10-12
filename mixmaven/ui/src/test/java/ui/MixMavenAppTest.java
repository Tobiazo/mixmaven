package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@SuppressWarnings("magicnumber")
public class MixMavenAppTest extends ApplicationTest {

    private static HashMap<String, String> testIngredients;
    private Parent root;

    @Override
    public final void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MixMaven.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("styles/MixMaven.css").toExternalForm());
        stage.show();
    }

    @BeforeAll
    static void setup() {
        testIngredients = new HashMap<>();
        testIngredients.put("Vodka", "20 dl Vodka 40 alcohol ");
        testIngredients.put("Fireball", "20 dl Fireball 20 alcohol ");
        testIngredients.put("Tonic", "20 dl Tonic 0 mixer ");
        testIngredients.put("Gin", "20 dl Gin 40 alcohol ");
        testIngredients.put("Lime", "30 gram Lime 0 extras ");
    }

    public final Parent getRootNode() {
        return root;
    }

    private void click(String... labels) {
        for (var label : labels) {
            clickOn(hasText(label));
        }
    }

    private void write(String label, String text) {
        clickOn(label).write(text);
    }

    private void select(String choice) {
        if (choice.equals("alcohol") || choice.equals("mixer") || choice.equals("extras")) {
            if (choice.equals("alcohol"))
                type(KeyCode.ENTER);
            else if (choice.equals("mixer")) {
                type(KeyCode.DOWN);
                type(KeyCode.ENTER);
            } else {
                type(KeyCode.DOWN);
                type(KeyCode.DOWN);
                type(KeyCode.ENTER);
            }
        } else if (choice.equals("ml") || choice.equals("dl") || choice.equals("gram")) {
            if (choice.equals("ml"))
                type(KeyCode.ENTER);
            else if (choice.equals("dl")) {
                type(KeyCode.DOWN);
                type(KeyCode.ENTER);
            } else {
                type(KeyCode.DOWN);
                type(KeyCode.DOWN);
                type(KeyCode.ENTER);
            }
        } else
            throw new IllegalArgumentException("Not a valid Choice!");
    }

    /**
     * @param compareString to check if it is a substring
     * @param targetString to check if compareString is included in
     * @return true if comparestring is a subbstring of target.
     */
    private boolean isSubstring(String compareString, String targetString) {
        for (int i = 0; i < compareString.length(); i++) {
            try {
                if (!(compareString.charAt(i) == targetString.charAt(i)))
                    return false;
            } catch (StringIndexOutOfBoundsException e) {
                return false;
            }
        }
        return true;
    }

    public final boolean searchDrinks(String[] args) {
        // Use FxRobot to interact with the JavaFX application
        FxRobot robot = new FxRobot();

        // Locate the VBox by its parent (the scene)
        VBox drinkContainer = robot.lookup("#drinkContainer").query();

        for (Node drinkBox : drinkContainer.getChildren()) {
            if (drinkBox instanceof VBox) {
                Text nameLabel = (Text) ((VBox) drinkBox).getChildren().get(0);

                String name = nameLabel.getText().replace("Name: ", "");
                String ingredient = ((Text) ((VBox) drinkBox)
                    .getChildren()
                    .get(1))
                    .getText()
                    .replace("Type: ", "")
                    .replace("• ", "").replace(".0", "")
                    .replace("%", "")
                    .replaceAll("\\s+", " ");

                ingredient = ingredient.trim();
                ingredient += " ";

                String[] results = {name, ingredient};
                System.out.println("This is search args: " + args[1]);
                System.out.println("This is results: " + results[1]);
                if (results[0].equals(args[0]) && isSubstring(results[1], args[1]))
                    return true;

            }
        }
        return false;
    }

    @Test
    public void testNavigate() {
        click("Add Drink");
        Assertions.assertTrue(getRootNode().lookup("#addDrinkPane") != null);

        click("Back");
        Assertions.assertTrue(getRootNode().lookup("#browseDrinksPane") != null);
    }

    @Test
    public void testCreateUnnamedDrink() {
        click("Add Drink");
        click("Add New Drink");

        Assertions.assertTrue(getRootNode().lookup("#addDrinkPane") != null);

    }

    @Test
    public void testEditDrinkAddIngredient() {
        click("Your Drinks");
        click("Edit Drink");

        List<String> ingredient = Arrays.asList(testIngredients.get("Lime").split(" "));
        write("#ingredientNameField", ingredient.get(2));
        write("#amountField", ingredient.get(0));

        clickOn("#unitChoiceBox");
        select(ingredient.get(1));

        clickOn("#typeChoiceBox");
        select(ingredient.get(4));

        click("Add New Ingredient");

        click("Update Drink");

        String[] createdDrink = {"Moscow Mule", testIngredients.get("Vodka") + testIngredients.get("Lime")};
        System.out.println(searchDrinks(createdDrink));
        Assertions.assertTrue(searchDrinks(createdDrink));
    }

    @ParameterizedTest
    @MethodSource
    public final void testCreateDrink(String name, String ingredientString) {
        click("Add Drink");

        List<String> ingredients =
                new ArrayList<String>(Arrays.asList(ingredientString.split(" ")));
        for (int i = 0; i < ingredients.size(); i += 5) {
            write("#ingredientNameField", ingredients.get(i + 2));
            write("#amountField", ingredients.get(i));

            clickOn("#unitChoiceBox");
            select(ingredients.get(i + 1));

            clickOn("#typeChoiceBox");
            select(ingredients.get(i + 4));

            write("#alchoholPercentField", ingredients.get(i + 3));

            click("Add Ingredient");
        }

        write("#drinkNameField", name);

        click("Add New Drink");

        String[] createdDrink = {name, ingredientString};
        Assertions.assertTrue(searchDrinks(createdDrink));
    }

    private static Stream<Arguments> testCreateDrink() {
        return Stream.of(
                // Arguments.of("test", ""),
                Arguments.of("Moscow Mule", testIngredients.get("Vodka")),
                Arguments.of("GT", testIngredients.get("Gin") + testIngredients.get("Tonic")
                        + testIngredients.get("Lime"))
        );
    }
}
