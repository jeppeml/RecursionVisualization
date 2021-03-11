package dk.moritzled.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {
    public void simpleStart(ActionEvent actionEvent) {
        openWindow("/dk/moritzled/gui/simple/SimpleTree.fxml", "Simple Tree Draw");
    }

    public void advancedStart(ActionEvent actionEvent) {
        openWindow("/dk/moritzled/gui/advanced/AdvancedTree.fxml", "Advanced Tree Draw");
    }

    public void sierpinsky(ActionEvent actionEvent) {
        openWindow("/dk/moritzled/gui/simple/sierpinsky.fxml", "Sierpinsky Triangle Draw");
    }

    private void openWindow(String fxmlPath, String title)  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
