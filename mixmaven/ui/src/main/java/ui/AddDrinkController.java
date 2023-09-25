package ui;

import core.Ingredient;
import core.DataHandler;
import core.Drink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDrinkController {
	@FXML private Label errorLabel;
	@FXML private TextField drinkNameField;
	@FXML private TextField ingredientNameField;
	@FXML private TextField alchoholPercentField;
	@FXML private TextField amountField;
	@FXML private ListView<Ingredient> ingredientList;
	@FXML private ChoiceBox<String> typeChoiceBox = new ChoiceBox<>();
	@FXML private ChoiceBox<String> unitChoiceBox = new ChoiceBox<>();
	@FXML private ObservableList<String> typeList = FXCollections.observableArrayList("Alchohol", "Mixer", "Extras");
	@FXML private ObservableList<String> unitList = FXCollections.observableArrayList("mL");
	private List<Ingredient> selectedIngredients = new ArrayList<>();
	
	@FXML
	public void initialize() {
		unitChoiceBox.setItems(unitList);
		unitChoiceBox.setValue("Unit of Measurement");
		typeChoiceBox.setItems(typeList);
		typeChoiceBox.setValue("Type");

	}

	@FXML
	public void deleteIngredientBtn(ActionEvent event) throws IOException{
		try {
		int selectedIndex = ingredientList.getSelectionModel().getSelectedIndex();
		selectedIngredients.remove(selectedIndex);
		ingredientList.getItems().remove(selectedIndex);
		
		} catch (IndexOutOfBoundsException e) {
			errorLabel.setText("Select something to delete");
			
		}
		
	}

	@FXML
	public void addIngredientBtn(ActionEvent event) throws IOException {
		try {
			String ingredientName = ingredientNameField.getText();
			int alchoholPercent = Integer.parseInt(alchoholPercentField.getText());
			double amount = Double.parseDouble(amountField.getText());
			String unit = unitChoiceBox.getValue();
			String type = typeChoiceBox.getValue();
			Ingredient newIngredient = new Ingredient(ingredientName, alchoholPercent, amount, unit, type);

			selectedIngredients.add(newIngredient);
			ingredientList.getItems().add(newIngredient);
			ingredientList.refresh();

		} catch (Exception e) {
			errorLabel.setText("Fill in the fields correct");
		}
		

		

	}
	@FXML
	public void backBtn(ActionEvent event) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MixMaven.fxml"));
			Parent root = loader.load();
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			errorLabel.setText(e.getStackTrace().toString());
		}
	}

	@FXML
	public void addDrinkBtn(ActionEvent event) throws IOException {
		String drinkName = drinkNameField.getText();
		Drink newDrink = new Drink(drinkName, selectedIngredients);
		// String s = getClass().getResource("/ui/json/data.json").toString().replace("file:", "");
		DataHandler.addDrink(newDrink);


		// move to Main view with BrowseDrinks pane
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MixMaven.fxml"));
			Parent root = loader.load();
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
        } catch (IOException e) {
                errorLabel.setText(e.getStackTrace().toString());
        }
    };
}

