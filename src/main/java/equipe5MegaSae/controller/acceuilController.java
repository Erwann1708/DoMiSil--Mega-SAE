package equipe5MegaSae.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class acceuilController {

    @FXML private TitledPane artistePane;
    @FXML private TitledPane logistiquePane;
    @FXML private TitledPane planningPane;
    @FXML private TitledPane billetteriePane;
    @FXML private TitledPane documentsPane;
    @FXML private Button CreerFestivalButton;

    private void setSectionsEnabled(boolean enabled) {
        artistePane.setDisable(!enabled);
        logistiquePane.setDisable(!enabled);
        planningPane.setDisable(!enabled);
        billetteriePane.setDisable(!enabled);
        documentsPane.setDisable(!enabled);
    }

    public void initialize() {
        // Désactiver tous les TitledPane au départ
        setSectionsEnabled(false);
    }

    @FXML
    private void handleCreerFestival(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/equipe5MegaSae/view/CreerFestival.fxml"));
            Parent root = loader.load();

            // Récupère le contrôleur de la nouvelle fenêtre
            CreerFestivalController controller = loader.getController();

            // Crée la nouvelle fenêtre
            Stage stage = new Stage();
            stage.setTitle("Créer un Festival");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // bloque la fenêtre principale
            stage.showAndWait(); // attend que l'utilisateur ferme
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}