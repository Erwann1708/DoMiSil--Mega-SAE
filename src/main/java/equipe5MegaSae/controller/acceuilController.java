package equipe5MegaSae.controller;

import equipe5MegaSae.model.Artiste;
import equipe5MegaSae.model.Document;
import equipe5MegaSae.model.Festival;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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

    @FXML
    private VBox artistesListVBox;

    public void initialize() {
        // Création d'un festival temporaire pour test
        this.festival = new Festival(
                "Festival Test",
                5000,
                java.time.LocalDate.of(2025, 7, 1),
                java.time.LocalDate.of(2025, 7, 3),
                new equipe5MegaSae.model.Lieu("Lieu Test", "Grenoble", "38000", "1 rue Test", 500)
        );
        //On ouvre le TitledPane dès le départ
        documentsPane.setExpanded(true);

        //On contraint la largeur de la VBox à celle du ScrollPane moins un petit padding
        containerDocs.prefWidthProperty()
                .bind(scrollPaneDocs.widthProperty().subtract(20));

        // On remplit tout de suite la liste (avec juste le bouton "Ajouter" si pas de docs)
        refreshAffichageDocuments();
        refreshAffichageArtistes();
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

            CreerArtisteController controller = loader.getController();
            controller.setAccueilController(this);

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
    public void ajouterArtiste(Artiste artiste) {
        festival.ajouterArtiste(artiste);
        refreshAffichageArtistes();
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
        // Vide le conteneur
        containerDocs.getChildren().clear();

        // ─── Ligne "Ajouter vos Devis" ────────────────────────────────────────────
        HBox addLine = new HBox(10);
        addLine.setAlignment(Pos.CENTER_LEFT);
        addLine.setStyle(
                "-fx-background-color: #F4F4F4;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10;"
        );

        Label addLabel = new Label("Ajouter vos Devis");
        addLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Region spacerAdd = new Region();
        HBox.setHgrow(spacerAdd, Priority.ALWAYS);

        Button addBtn = new Button("+");
        addBtn.setStyle(
                "-fx-background-color: #6e3d96;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;"
        );
        addBtn.setOnAction(e -> handleAjouterDevis(null));

        addLine.getChildren().addAll(addLabel, spacerAdd, addBtn);
        containerDocs.getChildren().add(addLine);

        // ─── Lignes Document existants ───────────────────────────────────────────
        for (Document doc : festival.getDocuments()) {
            HBox ligne = new HBox(10);
            ligne.setAlignment(Pos.CENTER_LEFT);
            ligne.setStyle(
                    "-fx-background-color: #F4F4F4;" +
                            "-fx-background-radius: 10;" +
                            "-fx-padding: 10;"
            );
            ligne.setPrefHeight(50);

            // Bloc texte : nom + type
            Label nomLabel = new Label(doc.getNom());
            nomLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label typeLabel = new Label("(" + doc.getType() + ")");
            typeLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");

            VBox blocTexte = new VBox(2, nomLabel, typeLabel);

            // Espaceur flexible
            Region spacerDoc = new Region();
            HBox.setHgrow(spacerDoc, Priority.ALWAYS);

            // Bouton Ouvrir (violet)
            Button ouvrirBtn = new Button("Ouvrir");
            ouvrirBtn.setStyle(
                    "-fx-background-color: #6e3d96;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 15;"
            );
            ouvrirBtn.setOnAction(ev -> {
                try {
                    doc.ouvrir();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            // Bouton Supprimer (texte rouge)
            Button delBtn = new Button("Supprimer");
            delBtn.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-text-fill: #d64545;" +
                            "-fx-font-weight: bold;" +
                            "-fx-cursor: hand;"
            );
            delBtn.setOnAction(e -> {
                festival.supprimerDocument(doc);
                refreshAffichageDocuments();
            });

            // Ajout de tous les éléments à la ligne
            ligne.getChildren().addAll(blocTexte, spacerDoc, ouvrirBtn, delBtn);

            // Injection dans la VBox principale
            containerDocs.getChildren().add(ligne);
        }
    }

    // Affiche la liste des artistes dans la VBox artistesListVBox
    public void refreshAffichageArtistes() {
        artistesListVBox.getChildren().clear();

        // Ligne "Ajouter un artiste"
        HBox addLine = new HBox(10);
        addLine.setAlignment(Pos.CENTER_LEFT);
        addLine.setStyle(
                "-fx-background-color: #F4F4F4;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10;"
        );

        Label addLabel = new Label("Ajouter un artiste");
        addLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Region spacerAdd = new Region();
        HBox.setHgrow(spacerAdd, Priority.ALWAYS);

        Button addBtn = new Button("+");
        addBtn.setStyle(
                "-fx-background-color: #6e3d96;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;"
        );
        addBtn.setOnAction(e -> handleAjouterArtiste(null));

        addLine.getChildren().addAll(addLabel, spacerAdd, addBtn);
        artistesListVBox.getChildren().add(addLine);

        // Lignes pour chaque artiste existant
        for (Artiste artiste : festival.getArtistes()) {
            HBox ligne = new HBox(10);
            ligne.setAlignment(Pos.CENTER_LEFT);
            ligne.setStyle(
                    "-fx-background-color: #F4F4F4;" +
                            "-fx-background-radius: 10;" +
                            "-fx-padding: 10;"
            );
            ligne.setPrefHeight(50);

            Label nomLabel = new Label(artiste.getNom());
            nomLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label telLabel = new Label(artiste.getTelephone());
            telLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");

            VBox blocTexte = new VBox(2, nomLabel, telLabel);

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            Button delBtn = new Button("Supprimer");
            delBtn.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-text-fill: #d64545;" +
                            "-fx-font-weight: bold;" +
                            "-fx-cursor: hand;"
            );
            delBtn.setOnAction(e -> {
                festival.supprimerArtiste(artiste);
                refreshAffichageArtistes();
            });

            ligne.getChildren().addAll(blocTexte, spacer, delBtn);
            artistesListVBox.getChildren().add(ligne);
        }
    }
}