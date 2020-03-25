package code;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fractal.fxml"));
        Parent root = loader.load();
        FractalController controller = (FractalController) loader.getController();
        controller.setModel(new FractalModel());
        primaryStage.setTitle("Fractal");
        primaryStage.setScene(new Scene(root, 1080, 640));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
