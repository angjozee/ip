package plato;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class to run Plato
 */
public class Main extends Application {
    private Plato plato = new Plato(); // Create Plato instance

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();

            // Inject Plato instance into the controller
            MainWindow controller = fxmlLoader.getController();
            controller.setPlato(plato);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Plato Chatbot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
