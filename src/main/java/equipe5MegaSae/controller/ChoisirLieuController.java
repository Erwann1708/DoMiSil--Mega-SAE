package equipe5MegaSae.controller;

import equipe5MegaSae.model.Festival;
import equipe5MegaSae.model.Lieu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;


public class ChoisirLieuController {

    @FXML
    private Button Quitter;
    @FXML
    private TextField nomLieu;
    @FXML
    private Label ErreurNomLieu;
    @FXML
    private TextField villeLieu;
    @FXML
    private Label ErreurVilleLieu;
    @FXML
    private TextField codePostalLieu;
    @FXML
    private Label ErreurCodePostalLieu;
    @FXML
    private TextField adresseLieu;
    @FXML
    private Label ErreurAdresseLieu;
    @FXML
    private TextField superficieLieu;
    @FXML
    private Label ErreurSuperficieLieu;
    @FXML
    private Button ChoisirLieu;

    private CreerFestivalController creerFestivalController;


    public void creerFestivalController(CreerFestivalController FestivalController) {
        this.creerFestivalController = FestivalController;
    }

    @FXML
    public void handleQuitter(ActionEvent event) {
        Stage stage = (Stage) Quitter.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleChoisirLieu(ActionEvent event) {
        // 1) On vide d’abord tous les labels d’erreur
        ErreurNomLieu.setText("");
        ErreurVilleLieu.setText("");
        ErreurCodePostalLieu.setText("");
        ErreurAdresseLieu.setText("");
        ErreurSuperficieLieu.setText("");

        // 2) On lit les saisies
        String nom   = nomLieu.getText().trim();
        String ville = villeLieu.getText().trim();
        String cp    = codePostalLieu.getText().trim();
        String adr   = adresseLieu.getText().trim();
        String supStr= superficieLieu.getText().trim();

        boolean valid = true;
        Lieu lieuTemp = new Lieu();

        // 3) Validation PAR SETTERS

        // 3.1 Nom
        try {
            lieuTemp.setLabelLieu(nom);
        } catch (IllegalArgumentException ex) {
            ErreurNomLieu.setText(ex.getMessage());
            valid = false;
        }

        // 3.2 Ville
        try {
            lieuTemp.setVille(ville);
        } catch (IllegalArgumentException ex) {
            ErreurVilleLieu.setText(ex.getMessage());
            valid = false;
        }

        // 3.3 Code Postal
        try {
            lieuTemp.setCodePostal(cp);
        } catch (IllegalArgumentException ex) {
            ErreurCodePostalLieu.setText(ex.getMessage());
            valid = false;
        }

        // 3.4 Adresse
        try {
            lieuTemp.setAdresse(adr);
        } catch (IllegalArgumentException ex) {
            ErreurAdresseLieu.setText(ex.getMessage());
            valid = false;
        }

        // 3.5 Superficie : parsing + setter
        int sup = 0;
        try {
            sup = Integer.parseInt(supStr);
            lieuTemp.setSuperficie(sup);
        } catch (NumberFormatException nfe) {
            ErreurSuperficieLieu.setText("La superficie doit être un entier.");
            valid = false;
        } catch (IllegalArgumentException ex) {
            ErreurSuperficieLieu.setText(ex.getMessage());
            valid = false;
        }

        // 4) Si un seul champ est invalide, on arrête ici
        if (!valid) {
            return;
        }

        // 5) Si tout est valide, on transmet le Lieu au controller parent
        creerFestivalController.setLieuChoisi(lieuTemp);

        // 6) Puis on ferme la popup
        Stage stage = (Stage) ChoisirLieu.getScene().getWindow();
        stage.close();
    }







}


