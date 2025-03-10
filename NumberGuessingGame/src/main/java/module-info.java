module javafx.numberguessinggame {
    requires javafx.controls;
    requires javafx.fxml;

    opens javafx.numberguessinggame to javafx.fxml;
    exports javafx.numberguessinggame;
}
