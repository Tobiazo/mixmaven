package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateUserController {

    private MixMavenController mixMavenController;
    @FXML private Label logInLabel;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField passwordField2;

    /**
     * Constructor for the CreateUserController.
     * @param mixMavenController
     */
    public CreateUserController(MixMavenController mixMavenController) {
        this.mixMavenController = mixMavenController;
    }

    /**
     * Creates a new User with the data from the TextField and PasswordFields.
     */
    @FXML
    public void createUserBtn() {
        // String username = usernameField.getText();
        // String password = passwordField.getText();
        // String confirmedPassword = passwordField2.getText();
        //Further functionality to be implemented. Awaiting User Class.
        mixMavenController.showBrowseDrinks();
    }
}
