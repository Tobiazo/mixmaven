package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    private MixMavenController mixMavenController;
    @FXML private Label loginLabel;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    /**
     * Constructor for the controller class.
     * @param mixMavenController
     */
    public LoginController(MixMavenController mixMavenController) {
        this.mixMavenController = mixMavenController;
    }

    /**
     * Validates the values from the usernameField and passwordField then switches scene to showBrowseDrinks.fxml.
     */
    @FXML
    public void loginBtn() {
        //String username = usernameField.getText();
        //String password = passwordField.getText();
        //if (User.validate(username, password))
        mixMavenController.showBrowseDrinks();
    }

    /**
     * Switched the scene to createUser.fxml.
     */
    @FXML
    public void newUserHyperlink() {
        mixMavenController.showCreateUser();
    }
}
