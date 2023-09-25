package ui;

import core.Ingredient;
import core.Drink;
import core.DataHandler;
import static core.Constants.validUnits;
import static core.Constants.validTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddDrinkController {
	@FXML private Label errorLabel;
	@FXML private TextField drinkNameField;
	@FXML private TextField ingredientNameField;
	@FXML private TextField alchoholPercentField;
	@FXML private TextField amountField;
	@FXML private ListView<Ingredient> ingredientList;
	@FXML private ChoiceBox<String> typeChoiceBox;
	@FXML private ChoiceBox<String> unitChoiceBox;
	private List<Ingredient> selectedIngredients = new ArrayList<>();
	private MixMavenController mixMavenController;

	public AddDrinkController(MixMavenController mixMavenController) {
		this.mixMavenController = mixMavenController;
	}

	@FXML
	public void initialize() {
		unitChoiceBox.setValue("Unit of Measurement");
		unitChoiceBox.getItems().addAll(validUnits);
		typeChoiceBox.setValue("Type");
		typeChoiceBox.getItems().addAll(validTypes);
	}

	@FXML
	public void deleteIngredientBtn(ActionEvent event) throws IOException {
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
			if (ingredientName.length() == 0) throw new IllegalArgumentException();
			
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
		mixMavenController.showBrowseDrinks();
	}

	@FXML
	public void addDrinkBtn(ActionEvent event) throws IOException {
		DataHandler.addDrink(new Drink(drinkNameField.getText(), selectedIngredients));
		mixMavenController.showBrowseDrinks();
	};
}

