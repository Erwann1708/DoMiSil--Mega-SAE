package equipe5MegaSae.controller;

import equipe5MegaSae.model.Document;
import equipe5MegaSae.model.Festival;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
    @FXML private Button ajouterArtisteButton;
    @FXML
    private Button ajouterDevis;
    @FXML
    private Button quitterButton;
    @FXML
    private VBox containerDocs;

    @FXML
    private ScrollPane scrollPaneDocs;

    private Festival festival;

    public void initialize() {
        // Création d'un festival temporaire pour test
        this.festival = new Festival(
                "Festival Test",
                5000,
                java.time.LocalDate.of(2025, 7, 1),
                java.time.LocalDate.of(2025, 7, 3),
                new equipe5MegaSae.model.Lieu("Lieu Test", "Grenoble", "38000", "1 rue Test", 500)
        );
    }


/*
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
        */


    @FXML
    private void handleCreerFestival(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/equipe5MegaSae/view/CreerFestival.fxml"));
            Parent root = loader.load();

            // Récupère le contrôleur de la nouvelle fenêtre
            CreerFestivalController controller = loader.getController();

            // Crée la nouvelle fenêtre
            Stage stage = new Stage();
            stage.setTitle("DoMiSi'l ManagerApp");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // bloque la fenêtre principale
            stage.showAndWait(); // attend que l'utilisateur ferme
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleAjouterArtiste(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/equipe5MegaSae/view/CreerArtiste.fxml"));
            Parent root = loader.load();

            // Crée la nouvelle fenêtre
            Stage stage = new Stage();
            stage.setTitle("Créer Artiste");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // bloque la fenêtre principale
            stage.showAndWait(); // attend que l'utilisateur ferme
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleAjouterDevis(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/equipe5MegaSae/view/document_popup.fxml"));
            Parent root = loader.load();

            DocumentPopupController controller = loader.getController();

            controller.setFestival(this.festival);         // ✅ Utilise le festival courant
            controller.setAccueilController(this);         // ✅ Pour permettre le refresh

            Stage stage = new Stage();
            stage.setTitle("Ajouter un document");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleQuitter(ActionEvent event) {
        Stage stage = (Stage) quitterButton.getScene().getWindow();
        stage.close();
    }


    public void refreshAffichageDocuments() {
        containerDocs.getChildren().clear();

        for (Document doc : festival.getDocuments()) {
            HBox ligne = new HBox(10);
            ligne.setStyle("-fx-background-color: #F4F4F4; -fx-padding: 10; -fx-alignment: CENTER_LEFT;");
            ligne.setPrefHeight(50);

            Label nomLabel = new Label(doc.getNom());
            nomLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label typeLabel = new Label("(" + doc.getType() + ")");
            typeLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            Button ouvrirBtn = new Button("Ouvrir");
            ouvrirBtn.setStyle("-fx-background-color: #6e3d96; -fx-text-fill: white; -fx-background-radius: 15px;");
            ouvrirBtn.setOnAction(e -> {
                try {
                    doc.ouvrir();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            VBox blocTexte = new VBox(nomLabel, typeLabel);
            ligne.getChildren().addAll(blocTexte, spacer, ouvrirBtn);
            containerDocs.getChildren().add(ligne);
        }
    }





}