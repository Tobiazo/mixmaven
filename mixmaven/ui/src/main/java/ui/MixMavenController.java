package ui;

import json.DataHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MixMavenController {

  @FXML private StackPane contentPane;
  private int selectedDrinkIndex;
  private File dataFile;
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

  public final void setFilePath(String fileName) {
    String userHome = System.getProperty("user.home");
    File folder = new File(userHome, "MixMaven");

    if (!folder.exists()) {
        if (folder.mkdir()) {
            System.out.println("Folder created successfully.");
        } else {
            System.err.println("Failed to create the folder.");
            return;
        }
    }
    dataFile = new File(folder, fileName);

    try {
        if (dataFile.createNewFile()) {
            FileWriter writer = new FileWriter(dataFile);
            writer.write("[]");
            writer.close();
            System.out.println("dataFile created.");
        } else {
            System.err.println("File exists");
        }

    DataHandler.loadDrinks(dataFile);

    } catch (IOException e) {
        System.err.println("An error occurred while creating the file: " + e.getMessage());
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
    DataHandler.loadDrinks(dataFile);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/BrowseDrinks.fxml"));
    loader.setController(browseDrinksController);
    showContent(loader);
  }

  /**
   * Loads the fxml file EditDrinks.fxml, sets corresponding controller and passes the selectedDrinkindex on.
   * @param selectedDrinkIndex
   */
  public void showEditDrink(int selectedDrinkIndex) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/EditDrink.fxml"));
    loader.setController(editDrinkController);
    showContentEdit(loader, selectedDrinkIndex);
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
 * Loads in the given loader and sets the selectedDrinkIndex.
 * @param loader
 * @param selectedDrinkIndex
 */
  private void showContentEdit(FXMLLoader loader, int selectedDrinkIndex) {
    try {
      setSelectedDrinkIndex(selectedDrinkIndex);
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
   * setter for SelectedDrinkindex.
   * @param index
   */
  private void setSelectedDrinkIndex(int index) {
    this.selectedDrinkIndex = index;
  }

}
