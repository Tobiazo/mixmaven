package ui;

import core.Ingredient;
import core.Drink;

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
		unitChoiceBox.setValue("Unit of measurement");
		typeChoiceBox.setValue("Ingredient Type");

		selectedIngredients = new ArrayList<>(
			mixMavenController
			.getDataAccess()
            .getModel()
			.getDrink(mixMavenController.getDrinkId())
			.getIngredients());

		drinkNameField
				.setText(mixMavenController
				.getDataAccess()
                .getModel()
				.getDrink(mixMavenController.getDrinkId())
				.getName());

		for (Ingredient ingredient : selectedIngredients) {
			ingredientList.getItems().add(ingredient);
		}
		ingredientList.refresh();

		ingredientList.setOnMouseClicked(e -> {
			int index = ingredientList.getSelectionModel().getSelectedIndex();
			if (index < 0) return;
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
		errorLabel.setText("");
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
		String ingredientName = ingredientNameField.getText();
		int alchoholPercent;
		double amount;
		String unit = unitChoiceBox.getValue();
		String type = typeChoiceBox.getValue();

		//Checks if an ingredient has been selected in the listview
		if (ingredientList.getSelectionModel().getSelectedItems().isEmpty()) {
			errorLabel.setText("Select an ingredient to edit!");
			return;
		}

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

		//Creates a new ingredient and replaces the selected ingredient, from the view, with the new ingredient.
		Ingredient newIngredient =
			new Ingredient(ingredientName, alchoholPercent, amount, unit, type);
		int index = ingredientList.getSelectionModel().getSelectedIndex();
		selectedIngredients.set(index, newIngredient);
		ingredientList.getItems().set(index, newIngredient);
		ingredientList.refresh();
		clearFields();
	}

	/**
	 * Adds a new ingredient to the drink being updated.
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
			System.out.println("ID" + mixMavenController.getDrinkId());
			mixMavenController.getDataAccess().editDrink(mixMavenController.getDrinkId(),
					new Drink(drinkNameField.getText(), selectedIngredients));
			mixMavenController.showBrowseDrinks();
		}
	}
}
