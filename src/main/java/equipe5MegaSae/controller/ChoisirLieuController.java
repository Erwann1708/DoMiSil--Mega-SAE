package equipe5MegaSae.controller;

import equipe5MegaSae.model.Festival;
import equipe5MegaSae.model.Lieu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class ChoisirLieuController {

    @FXML
    private Button Quitter;
    @FXML
    private TextField nomLieu;
    @FXML
    private TextField villeLieu;
    @FXML
    private TextField codePostalLieu;
    @FXML
    private TextField adresseLieu;
    @FXML
    private TextField superficieLieu;
    @FXML
    private TextField test;


    @FXML
    public void handleQuitter(ActionEvent event) {
        Stage stage = (Stage) Quitter.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleChoisirLieuButton(ActionEvent event) {
        try {
            // Lecture des valeurs des TextField
            String label   = nomLieu.getText().trim();
            String ville   = villeLieu.getText().trim();
            String cp      = codePostalLieu.getText().trim();
            String adresse = adresseLieu.getText().trim();
            int superficie = Integer.parseInt(superficieLieu.getText().trim());

            // Création de l'objet Lieu (les setters valident et lèvent IllegalArgumentException si besoin)
            Lieu lieu = new Lieu(label, ville, cp, adresse, superficie);

            // Fermeture de la fenêtre
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

        } catch (IllegalArgumentException e) {
            test.setText(e.getMessage());
        }
    }

}
