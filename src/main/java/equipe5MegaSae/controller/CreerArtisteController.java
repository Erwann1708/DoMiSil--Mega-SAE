package equipe5MegaSae.controller;

import equipe5MegaSae.model.Artiste;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private javafx.scene.control.Label erreurLabel;

    private acceuilController accueilController;

    public void setAccueilController(acceuilController controller) {
        this.accueilController = controller;
    }

    public static boolean isValidEmail(String email) {
        //if (email == null) return false;
        //email = email.trim();
        //return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
        return true;
    }

    // Java
    @FXML
    private void handleValider() {
        try {
            String nom = nomArtisteField.getText().trim();
            String telephone = telephoneField.getText().trim();
            String email = emailField.getText().trim();

            System.out.println("Email saisi : '" + email + "'");
            System.out.println("Résultat validation : " + isValidEmail(email));
            if (!isValidEmail(email)) {
                erreurLabel.setText("Adresse mail invalide.");
                return;
            }
            erreurLabel.setText("");

            Artiste artiste = new Artiste(nom, telephone, email);

            if (accueilController != null) {
                accueilController.ajouterArtiste(artiste);
            }

            ((Stage) nomArtisteField.getScene().getWindow()).close();

        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAnnuler() {
        Stage stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }
}
