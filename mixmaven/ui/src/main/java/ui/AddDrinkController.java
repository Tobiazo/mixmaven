package ui;

import core.Ingredient;
import core.Drink;
import core.DataHandler;
import static core.Constants.VALIDUNITS;
import static core.Constants.VALIDTYPES;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public final class AddDrinkController {
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

	/**
	 * Initialized the choiceboxes with values.
	 */
	@FXML
	public void initialize() {
		unitChoiceBox.setValue("Unit of Measurement");
		unitChoiceBox.getItems().addAll(VALIDUNITS);
		typeChoiceBox.setValue("Type");
		typeChoiceBox.getItems().addAll(VALIDTYPES);

		typeChoiceBox.setOnAction(event -> {
			if (typeChoiceBox.getValue() != "alcohol") {
				alchoholPercentField.setEditable(false);
				alchoholPercentField.clear();
			} else {
				alchoholPercentField.setEditable(true);
			}
		});
	}

	/**
	 * Deletes the ingredient selected in the listview.
	 */
	@FXML
	public void deleteIngredientBtn() {
		try {
			int selectedIndex = ingredientList.getSelectionModel().getSelectedIndex();
			selectedIngredients.remove(selectedIndex);
			ingredientList.getItems().remove(selectedIndex);
		} catch (IndexOutOfBoundsException e) {
			errorLabel.setText("Select something to delete");
		}
	}

	/**
	 * Adds a ingredient to the listview and to the list of ingredients to be added to the drink.
	 */
	@FXML
	public void addIngredientBtn() {
		try {
			String ingredientName = ingredientNameField.getText();
			double amount = Double.parseDouble(amountField.getText());
			String unit = unitChoiceBox.getValue();
			String type = typeChoiceBox.getValue();
            int alchoholPercent;
            if (!(type.equals("alcohol"))) alchoholPercent = 0;
            else alchoholPercent = Integer.parseInt(alchoholPercentField.getText());
			Ingredient newIngredient = new Ingredient(ingredientName, alchoholPercent, amount, unit, type);

			selectedIngredients.add(newIngredient);
			ingredientList.getItems().add(newIngredient);
			ingredientList.refresh();

            ingredientNameField.clear();
            amountField.clear();
            unitChoiceBox.setValue(null);
            typeChoiceBox.setValue(null);
		} catch (Exception e) {
			errorLabel.setText("Fill in the fields correct");
		}
	}

	/**
	 * Returns the user to the main screen.
	 */
	@FXML
	public void backBtn() {
		mixMavenController.showBrowseDrinks();
	}

	/**
	 * Creates a new drink object with name from the drinknameLabel
	 * and ingredients from the selectedIngredients List
	 * Then returns user to main screen.
	 */
	@FXML
	public void addDrinkBtn() {
		if (selectedIngredients.isEmpty()) {
			errorLabel.setText("Cannot make a Drink with no ingredients");
		} else if (drinkNameField.getText() == null || drinkNameField.getText().trim().isEmpty()) {
			errorLabel.setText("Write a Drink Name");
		} else {
			DataHandler.addDrink(new Drink(drinkNameField.getText(), selectedIngredients));
			mixMavenController.showBrowseDrinks();
		}
	};

	/**
	 * Clears the parameter fields.
	 */
	private void clearFields() {
		ingredientNameField.clear();
		amountField.clear();
		alchoholPercentField.clear();
		unitChoiceBox.setValue(null);
		typeChoiceBox.setValue(null);
		alchoholPercentField.setEditable(true);
	}
}


