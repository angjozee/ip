package plato;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the Plato chatbot.
 * Manages user interactions, message display, and chatbot responses.
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

    /**
     * Initializes the GUI components and sets up scrolling behavior.
     * Ensures UI elements are properly initialized.
     */
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

    /**
     * Injects the Plato instance into the controller.
     * Ensures that the chatbot can process user input.
     *
     * @param p The Plato chatbot instance.
     */
    public void setPlato(Plato p) {
        plato = p;
        showWelcomeMessage(); // Show welcome message as soon as Plato is set
    }

    /**
     * Displays the chatbot's initial greeting message in the chat window.
     */
    private void showWelcomeMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Greetings, I am Plato. Nice to meet you!\nWhat would you like to do?",
                        platoImage)
        );
    }

    /**
     * Handles user input by displaying it in the chat window and generating Plato's response.
     * The response is added to the dialog container, and the input field is cleared afterward.
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
