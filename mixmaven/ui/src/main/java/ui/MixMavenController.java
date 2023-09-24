package ui;

import core.DataHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.io.File;
import java.io.IOException;

public class MixMavenController {

  @FXML private StackPane contentPane;
  private final File dataFile = new File("src/main/resources/ui/json/data.json");

  // private Pane addDrinkPane;

  public void initialize() {
      DataHandler.loadDrinks(dataFile);

      showBrowseDrinks();
  }

  public void showAddDrink() {
    showContent("AddDrink");
  }

  public void showBrowseDrinks() {
    showContent("BrowseDrinks");
  }

  private void showContent(String content) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/" + content + ".fxml"));
    try {
      Pane pane = loader.load();
      contentPane.getChildren().setAll(pane);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
