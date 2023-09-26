package ui;

import core.DataHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;

public class MixMavenController {

  @FXML private StackPane contentPane;
  private final File dataFile = new File("src/main/resources/ui/json/data.json");
  private final AddDrinkController addDrinkController = new AddDrinkController(this);
  private final BrowseDrinksController browseDrinksController = new BrowseDrinksController(this);

  /**
   * Loads drinks from file Shows the BrowseDrinks page with the loaded drinks.
   */
  public void initialize() {
    DataHandler.loadDrinks(dataFile);
    showBrowseDrinks();
  }

  /**
   * Loads the fxml file AddDrink.fxml and sets corresponding controller.
   */
  public void showAddDrink() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/AddDrink.fxml"));
    loader.setController(addDrinkController);
    showContent(loader);
  }

  /**
   * Loads the fxml file BrowseDrinks.fxml and sets corresponding controller.
   */
  public void showBrowseDrinks() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/BrowseDrinks.fxml"));
    loader.setController(browseDrinksController);
    showContent(loader);
  }

  /**
   * Loads in the given loader.
   *
   * @param loader
   */
  private void showContent(FXMLLoader loader) {
    try {
      Parent root = loader.load();
      contentPane.getChildren().setAll(root);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
