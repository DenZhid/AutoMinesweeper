package view;

import controller.ControllerOfMainScreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScreenView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
        Parent root = loader.load();
        ControllerOfMainScreen controller = loader.getController();
        controller.setStage(primaryStage);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("AutoMinesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
