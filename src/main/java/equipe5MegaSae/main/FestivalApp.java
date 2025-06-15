package equipe5MegaSae.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class FestivalApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FestivalApp.class.getResource("/equipe5MegaSae/view/acceuil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1404, 740);
        scene.setFill(Color.GRAY);
        stage.setTitle("DoMiSi'l ManagerApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}