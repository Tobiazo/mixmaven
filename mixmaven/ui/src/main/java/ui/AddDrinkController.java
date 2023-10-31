package ui;

import core.Ingredient;
import core.Drink;
import static core.Constants.VALIDUNITS;
import static core.Constants.VALIDTYPES;
import json.DataHandler;

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
    private DataHandler dataHandler;

	public AddDrinkController(MixMavenController mixMavenController) {
		this.mixMavenController = mixMavenController;
	}

	/**
	 * Initialized the choiceboxes with values.
	 */
	@FXML
	public void initialize() {
        dataHandler = mixMavenController.getDataHandler();
		unitChoiceBox.setValue("Unit of Measurement");
		unitChoiceBox.getItems().addAll(VALIDUNITS);
		typeChoiceBox.setValue("Ingredient Type");
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
		String ingredientName = ingredientNameField.getText();
		int alchoholPercent;
		double amount;
		String unit = unitChoiceBox.getValue();
		String type = typeChoiceBox.getValue();

		//Verifies the ingredient name Parameter.
		if (ingredientName.length() == 0) {
			errorLabel.setText("Name the ingredient");
			return;
		}

		//Verifies the amount Parameter.
		try {
			amount = Double.parseDouble(amountField.getText());
		} catch (NumberFormatException e) {
			errorLabel.setText("Amount must be a number!");
			return;
		}

		//Verifies the choiceboxes, unit and type.
		if (unit.equals("Unit of measurement") || type.equals("Ingredient Type")) {
			errorLabel.setText("Choose options from both the choiceboxes!");
			return;
		}
		//Verifies the alchohol Percent parameter.
		try {
			if (alchoholPercentField.getText().equals("")) {
				alchoholPercent = 0;
			} else {
				alchoholPercent = Integer.parseInt(alchoholPercentField.getText());
			}
		} catch (NumberFormatException e) {
			errorLabel.setText("AlchoholPercentage must be a number!");
			return;
		}

		//Verifies that a liquid is measured in volume.
		if (!type.equals("extras") && unit.equals("gram")) {
			errorLabel.setText("Insert liquids as volume!");
			return;
		}

		//Creates a new ingredient and adds it to the view.
		Ingredient newIngredient =
					new Ingredient(ingredientName, alchoholPercent, amount, unit, type);
			selectedIngredients.add(newIngredient);
			ingredientList.getItems().add(newIngredient);
			ingredientList.refresh();
			clearFields();
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
			mixMavenController.getMixMavenModel().
			addDrink(new Drink(drinkNameField.getText(), selectedIngredients));
            dataHandler.saveModel();
			mixMavenController.showBrowseDrinks();
		}
	}

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
		errorLabel.setText("");
	}
}


