package javafx.numberguessinggame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;


public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene scene=new Scene(root);
        Image icon=new Image("file:///C:/Users/tausi/OneDrive/Documents/Education/Programming/JavaFX/NumberGuessingGame/src/main/resources/javafx/numberguessinggame/Icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Guess The Number");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
    public static void main(String[] args) {
        launch();
    }
}