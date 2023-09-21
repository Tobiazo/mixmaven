package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


import java.io.IOException;

public class MixMavenController {

  @FXML private StackPane contentPane;
  // private Pane addDrinkPane;

  public void initialize() {
    
    // showAddDrinks();
    showBrowseDrinks();
  }

  public void showAddDrinks() {
    setContent("AddDrink");
  }

  public void showBrowseDrinks() {
    setContent("BrowseDrinks");
  }

  private void setContent(String content) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/" + content + ".fxml"));
    try {
      Pane pane = loader.load();
      contentPane.getChildren().setAll(pane);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
