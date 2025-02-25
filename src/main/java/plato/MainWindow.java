package plato;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Plato plato;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Russell.png"));
    private Image platoImage = new Image(this.getClass().getResourceAsStream("/images/Plato.png"));

    @FXML
    public void initialize() {
        assert scrollPane != null : "scrollPane should be initialized";
        assert dialogContainer != null : "dialogContainer should be initialized";
        assert userInput != null : "userInput should be initialized";
        assert sendButton != null : "sendButton should be initialized";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Ensure Plato instance is initialized before greeting
        if (plato != null) {
            showWelcomeMessage();
        }
    }

    /** Injects the Plato instance */
    public void setPlato(Plato p) {
        plato = p;
        showWelcomeMessage(); // Show welcome message as soon as Plato is set
    }

    /** Displays the chatbot's initial greeting */
    private void showWelcomeMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Greetings, I am Plato. Nice to meet you!\nWhat would you like to do?", platoImage)
        );
    }

    /**
     * Handles user input by creating two dialog boxes, one for user input and one for Plato's response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            String response = plato.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, platoImage)
            );
            userInput.clear();
        }
    }
}
