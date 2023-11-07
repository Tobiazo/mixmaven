package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URI;

public class MixMavenController {

  //TODO:
  //Logging and feedback.

  @FXML private StackPane contentPane;
  private String drinkId;
  private final AddDrinkController addDrinkController = new AddDrinkController(this);
  private final BrowseDrinksController browseDrinksController = new BrowseDrinksController(this);
  private final EditDrinkController editDrinkController = new EditDrinkController(this);
  private DataAccess dataAccess;
  

  /**
   * Loads drinks from file Shows the BrowseDrinks page with the loaded drinks.
   */
  public void initialize() {
    if (!syncWithServer()) this.dataAccess = new DirectDataAccess();
    showBrowseDrinks();
  }

  private boolean syncWithServer() {
    try {
        URI baseURI = new URI("http://10.22.14.153:8000/drinks/");
        this.dataAccess = new RemoteDataAccess(baseURI);
        System.out.println("Connected to server @" + baseURI);
        return true;
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Couldnt connect to server.");
        return false;
    }
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
   * Loads the fxml file EditDrinks.fxml, sets corresponding controller and passes the drinkId on.
   * @param drinkId
   */
  public void showEditDrink(String drinkId) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/EditDrink.fxml"));
    loader.setController(editDrinkController);
    showContentEdit(loader, drinkId);
  }

  /**
   * Loads in the given loader and switches the scene to the corresponding fxml file.
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

    /**
 * Loads in the given loader and sets the selectedDri.
 * @param loader
 * @param drinkId
 */
  private void showContentEdit(FXMLLoader loader, String drinkId) {
    try {
      setDrinkId(drinkId);
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
  public String getDrinkId() {
    return this.drinkId;
  }

/**
   * setter for drinkId.
   * @param drinkId
   */
  private void setDrinkId(String drinkId) {
    this.drinkId = drinkId;
  }

  public final DataAccess getDataAccess() {
    return dataAccess;
  }
}
