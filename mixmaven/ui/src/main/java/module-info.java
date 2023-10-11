module mixmaven.ui {
    requires mixmaven.core;
    requires mixmaven.json;
    requires javafx.controls;
    requires javafx.fxml;

    opens ui to javafx.graphics, javafx.fxml;
}
