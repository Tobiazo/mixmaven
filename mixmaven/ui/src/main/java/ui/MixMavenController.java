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
  private int selectedDrinkIndex; //Muligens ikke en god løsning, men fikset alle mine problemer - Tobias
  private final File dataFile = new File("src/main/resources/ui/json/data.json");
  private final AddDrinkController addDrinkController = new AddDrinkController(this);
  private final BrowseDrinksController browseDrinksController = new BrowseDrinksController(this);
  private final EditDrinkController editDrinkController = new EditDrinkController(this);

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
   * Loads the fxml file EditDrinks.fxml, sets corresponding controller and passes the selectedDrinkindex on.
   * @param id
   */
  public void showEditDrink(int id) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/editDrink.fxml"));
    loader.setController(editDrinkController);
    showContentEdit(loader, id);
  }

  /**
<<<<<<< HEAD
   * Loads in the given loader
   * 
=======
   * Loads in the given loader.
   *
>>>>>>> 5188efae3cca40f804514f1e47c4d50443a13c19
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
/**
 * Loads in the given loader and sets the selected drink index
 * @param loader
 * @param id
 */
  private void showContentEdit(FXMLLoader loader, int id) {
    try {
      setSelectedDrinkIndex(id);
      Parent root = loader.load();
      contentPane.getChildren().setAll(root);
     
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
/**
 * 
 * @return selectedDrinkIndex
 */
  public int getDrinkIndex() {
    return this.selectedDrinkIndex;
  }
/**
 * setter for SelectedDrinkindex
 * @param index
 */
  private void setSelectedDrinkIndex(int index){
    this.selectedDrinkIndex = index;
  }

}
