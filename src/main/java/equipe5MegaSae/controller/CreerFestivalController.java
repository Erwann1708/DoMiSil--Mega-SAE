package equipe5MegaSae.controller;

import equipe5MegaSae.model.Festival;
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


public class CreerFestivalController {

    @FXML
    private Button Quitter;
    @FXML
    private TextField nomFestival;
    @FXML
    private Label ErreurNom;
    @FXML
    private TextField BudgetFestival;
    @FXML
    private Label ErreurBudget;

    @FXML
    private TextField jourDebut;
    @FXML
    private TextField moisDebut;
    @FXML
    private TextField anneeDebut;
    @FXML
    private TextField jourFin;
    @FXML
    private TextField moisFin;
    @FXML
    private TextField anneeFin;
    @FXML
    private Label ErreurDate;
    @FXML
    private Button Lieu;



    @FXML
    public void handleQuitter(ActionEvent event) {
        Stage stage = (Stage) Quitter.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void handleCreerFestival(ActionEvent event) {
        // Réinitialiser les messages
        ErreurNom.setText("");
        ErreurBudget.setText("");
        ErreurDate.setText("");

        boolean erreur = false;

        String nom = nomFestival.getText();
        String budgetStr = BudgetFestival.getText();
        double budget = 0;

        // Vérif nom
        if (nom == null || nom.trim().isEmpty()) {
            ErreurNom.setText("Le nom du festival ne peut pas être vide.");
            erreur = true;
        }

        // Vérif budget
        if (budgetStr == null || budgetStr.trim().isEmpty()) {
            ErreurBudget.setText("Le budget ne peut pas être vide.");
            erreur = true;
        } else {
            try {
                budget = Double.parseDouble(budgetStr);
            } catch (NumberFormatException e) {
                ErreurBudget.setText("Le budget doit être un nombre.");
                erreur = true;
            }
        }

        // Lecture des dates
        LocalDate dateDebut = null, dateFin = null;
        try {
            int jourD = Integer.parseInt(jourDebut.getText());
            int moisD = Integer.parseInt(moisDebut.getText());
            int anneeD = Integer.parseInt(anneeDebut.getText());

            int jourF = Integer.parseInt(jourFin.getText());
            int moisF = Integer.parseInt(moisFin.getText());
            int anneeF = Integer.parseInt(anneeFin.getText());

            dateDebut = LocalDate.of(anneeD, moisD, jourD);
            dateFin = LocalDate.of(anneeF, moisF, jourF);
        } catch (DateTimeException | NumberFormatException e) {
            ErreurDate.setText("Les dates sont invalides.");
            erreur = true;
        }

        // si erreur = true, on va tenter setBudget / setDate pour déclencher les erreurs du modèle
        try {
            Festival festival = new Festival();

            // On tente chaque set individuellement, avec catch autour
            try {
                festival.setNom(nom);
            } catch (IllegalArgumentException e) {
                ErreurNom.setText(e.getMessage());
            }

            try {
                festival.setBudget(budget);
            } catch (IllegalArgumentException e) {
                ErreurBudget.setText(e.getMessage());
            }

            try {
                if (dateDebut != null) {
                    festival.setDateDebut(
                            dateDebut.getDayOfMonth(),
                            dateDebut.getMonthValue(),
                            dateDebut.getYear()
                    );
                }

                if (dateFin != null) {
                    festival.setDateFin(
                            dateFin.getDayOfMonth(),
                            dateFin.getMonthValue(),
                            dateFin.getYear()
                    );
                }
            } catch (IllegalArgumentException e) {
                ErreurDate.setText(e.getMessage());
            }

            // S’il n’y avait aucune erreur au départ + aucune levée ensuite, succès
            if (!erreur &&
                    ErreurNom.getText().isEmpty() &&
                    ErreurBudget.getText().isEmpty() &&
                    ErreurDate.getText().isEmpty()) {
                ErreurDate.setText("Festival créé avec succès !");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Pour debug s’il y a autre chose d’inattendu
        }
    }

    @FXML
    private void handleLieu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/equipe5MegaSae/view/ChoisirLieu.fxml"));
            Parent root = loader.load();

            // Récupère le contrôleur de la nouvelle fenêtre
            ChoisirLieuController controller = loader.getController();

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
