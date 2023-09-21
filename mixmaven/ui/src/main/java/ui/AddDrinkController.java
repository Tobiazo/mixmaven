package ui;

import core.Ingredient;
import core.MixMaven;
import core.Drink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddDrinkController {
	@FXML private TextField drinkNameField;
	@FXML private TextField ingredientNameField;
	@FXML private TextField alchoholPercentField;
	@FXML private TextField amountField;
	@FXML private TextField unitField;
	@FXML private ListView<Ingredient> ingredientList;
	@FXML private ChoiceBox<String> typeChoiceBox = new ChoiceBox<>();
	@FXML private ObservableList<String> typeList = FXCollections.observableArrayList("Alchohol", "Mixer", "Extras");
	private List<Ingredient> selectedIngredients = new ArrayList<>();
	
	@FXML
	public void initialize() {
		typeChoiceBox.setItems(typeList);
		typeChoiceBox.setValue("Type");

	}

	@FXML
	public void onDeleteIngredientButtonPress(ActionEvent event) throws IOException {
		int selectedIndex = ingredientList.getSelectionModel().getSelectedIndex();
		selectedIngredients.remove(selectedIndex);
		ingredientList.getItems().remove(selectedIndex);

	}

	@FXML
	public void onAddIngredientButtonPress(ActionEvent event) throws IOException {

		String ingredientName = ingredientNameField.getText();
		int alchoholPercent = Integer.parseInt(alchoholPercentField.getText());
		double amount = Double.parseDouble(amountField.getText());
		String unit = unitField.getText();
		String type = typeChoiceBox.getValue();
		Ingredient newIngredient = new Ingredient(ingredientName, alchoholPercent, amount, unit, type);

		selectedIngredients.add(newIngredient);
		ingredientList.getItems().add(newIngredient);
		ingredientList.refresh();

	}

	@FXML
	public void onAddDrinkButtonPress(ActionEvent event) throws IOException {
		String drinkName = drinkNameField.getText();
		Drink newDrink = new Drink(drinkName, selectedIngredients);
		//MixMaven.addDrink(newDrink);

	}
}
