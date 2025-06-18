package equipe5MegaSae.controller;

import equipe5MegaSae.model.Document;
import equipe5MegaSae.model.Festival;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Date;

public class DocumentPopupController {

    @FXML
    private TextField nomDocField;

    @FXML
    private TextField typeDocField;

    @FXML
    private Label cheminFichierLabel;

    private File fichierChoisi;

    private Festival festival;

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    private acceuilController accueilController;

    public void setAccueilController(acceuilController accueilController) {
        this.accueilController = accueilController;
    }


    @FXML
    private void handleChoisirFichier() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier");
        fichierChoisi = fileChooser.showOpenDialog(null);
        if (fichierChoisi != null) {
            cheminFichierLabel.setText(fichierChoisi.getName());
        } else {
            cheminFichierLabel.setText("Aucun fichier sélectionné");
        }
    }

    @FXML
    private void handleAnnuler() {
        ((Stage) nomDocField.getScene().getWindow()).close();
    }

    @FXML
    private void handleValider() {
        try {
            String nom = nomDocField.getText();
            String type = typeDocField.getText();
            Date date = new Date();

            Document doc = new Document(nom, type, fichierChoisi, date, festival);
            festival.ajouterDocument(doc);


            if (accueilController != null) {
                accueilController.refreshAffichageDocuments();
            }


            ((Stage) nomDocField.getScene().getWindow()).close();

        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

