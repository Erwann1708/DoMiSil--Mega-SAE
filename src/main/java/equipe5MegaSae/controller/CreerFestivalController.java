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


public class CreerFestivalController {

    @FXML
    Button Quitter;
    @FXML
    TextField nomFestival;
    @FXML
    TextField BudgetFestival;


    @FXML
    public void handleQuitter(ActionEvent event) {
        Stage stage = (Stage) Quitter.getScene().getWindow();
        stage.close();
    }

    /*
    @FXML
    public void initialize() {
        Festival festival = new Festival();
        festival.setNom(nomFestival.getText());
        festival.setBudget(Double.parseDouble(BudgetFestival.getText()));
    }

     */
}
