package equipe5MegaSae.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreerArtisteController {

    @FXML
    private TextField nomArtisteField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField emailField;

    @FXML
    private Button ajouterButton;

    @FXML
    private Button annulerButton;

    @FXML
    private void initialize() {
        // Initialization logic if needed
    }

    @FXML
    private void handleAjouter() {
        // Handle the ajouter button action
        String nomArtiste = nomArtisteField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();
        // Add logic to handle adding the artist
    }

    @FXML
    private void handleAnnuler() {
        // Handle the annuler button action
        // Add logic to handle canceling the action
    }


}
