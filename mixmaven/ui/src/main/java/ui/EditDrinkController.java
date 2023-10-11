package ui;

import core.Ingredient;
import core.Drink;
import json.DataHandler;

import static core.Constants.VALIDTYPES;
import static core.Constants.VALIDUNITS;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EditDrinkController {
	@FXML
	private Label errorLabel;
	@FXML
	private TextField drinkNameField;
	@FXML
	private TextField ingredientNameField;
	@FXML
	private TextField alchoholPercentField;
	@FXML
	private TextField amountField;
	@FXML
	private ListView<Ingredient> ingredientList;
	@FXML
	private ChoiceBox<String> typeChoiceBox;
	@FXML
	private ChoiceBox<String> unitChoiceBox;
	private List<Ingredient> selectedIngredients;
	private MixMavenController mixMavenController;

	public EditDrinkController(MixMavenController mixMavenController) {
		this.mixMavenController = mixMavenController;
	}

	/**
	 * Initalizer for EditDrink.fxml Sets the choicebox options Loads the Selected Drink into the
	 * drinkNameField and the corresponding ingredients into the listview.
	 */
	@FXML
	public void initialize() {
		unitChoiceBox.getItems().addAll(VALIDUNITS);
		typeChoiceBox.getItems().addAll(VALIDTYPES);

		selectedIngredients = new ArrayList<>(
				DataHandler.getDrinks().get(mixMavenController.getDrinkIndex()).getIngredients());

		drinkNameField
				.setText(DataHandler.getDrinks().get(mixMavenController.getDrinkIndex()).getName());

		for (Ingredient ingredient : selectedIngredients) {
			ingredientList.getItems().add(ingredient);
		}
		ingredientList.refresh();

		ingredientList.setOnMouseClicked(e -> {
			int index = ingredientList.getSelectionModel().getSelectedIndex();
			Ingredient ingredient = selectedIngredients.get(index);

			ingredientNameField.setText(ingredient.getName());
			alchoholPercentField.setText(String.valueOf(ingredient.getAlcoholPercentage()));
			amountField.setText(String.valueOf(ingredient.getAmount()));
			unitChoiceBox.getSelectionModel().select(ingredient.getUnit());
			typeChoiceBox.getSelectionModel().select(ingredient.getType());
		});

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

	/**
	 * Deletes the selected ingredient in the listview.
	 */
	@FXML
	public void deleteIngredientBtn() {
		try {
			int selectedIndex = ingredientList.getSelectionModel().getSelectedIndex();
			selectedIngredients.remove(selectedIndex);
			ingredientList.getItems().remove(selectedIndex);

			clearFields();
		} catch (IndexOutOfBoundsException e) {
			errorLabel.setText("Select something to delete");
		}

	}

	/**
	 * Changes the attributes of the ingredient selected in the listview based on the texfields and
	 * choiceboxes.
	 */
	@FXML
	public void editIngredientBtn() {
		try {
			String ingredientName = ingredientNameField.getText();
			if (ingredientName.length() == 0)
				throw new IllegalArgumentException();
			int alchoholPercent = Integer.parseInt(alchoholPercentField.getText());
			double amount = Double.parseDouble(amountField.getText());
			String unit = unitChoiceBox.getValue();
			String type = typeChoiceBox.getValue();
			if (!type.equals("extras") && unit.equals("gram")) {
				errorLabel.setText("Insert liquids as volume!");
			} else {
				Ingredient newIngredient =
						new Ingredient(ingredientName, alchoholPercent, amount, unit, type);
				int index = ingredientList.getSelectionModel().getSelectedIndex();
				selectedIngredients.set(index, newIngredient);
				ingredientList.getItems().set(index, newIngredient);
				ingredientList.refresh();

				clearFields();
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			errorLabel.setText("Fill in the fields correct");
		}
	}

	/**
	 * Adds a new ingredient to the drink being updated.
	 */
	@FXML
	public void addIngredientBtn() throws Exception {
		try {
			String ingredientName = ingredientNameField.getText();
			int alchoholPercent = Integer.parseInt(alchoholPercentField.getText());
			double amount = Double.parseDouble(amountField.getText());
			String unit = unitChoiceBox.getValue();
			String type = typeChoiceBox.getValue();
			Ingredient newIngredient =
					new Ingredient(ingredientName, alchoholPercent, amount, unit, type);
			if (!type.equals("extras") && unit.equals("gram")) {
				errorLabel.setText("Insert liquids as volume!");
			} else {
				selectedIngredients.add(newIngredient);
				ingredientList.getItems().add(newIngredient);
				ingredientList.refresh();

				clearFields();
			}
		} catch (Exception e) {
			errorLabel.setText("Fill in the fields correct");
		}
	}

	/**
	 * Returns user to main menu.
	 */
	@FXML
	public void backBtn() {
		mixMavenController.showBrowseDrinks();
	}

	/**
	 * Updates the selected Drink.
	 */
	@FXML
	public void editDrinkBtn() {
		if (selectedIngredients.isEmpty()) {
			errorLabel.setText("Cannot edit a Drink with no ingredients");
		} else if (drinkNameField.getText() == null || drinkNameField.getText().trim().isEmpty()) {
			errorLabel.setText("Write a Drink Name");
		} else {
			DataHandler.replaceDrink(mixMavenController.getDrinkIndex(),
					new Drink(drinkNameField.getText(), selectedIngredients));
			mixMavenController.showBrowseDrinks();
		}
	}
}
