/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.ModelMemory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Asad
 */
public class MainMemory extends Application {

    @Override
    public void start(Stage primaryStage) {

        ModelMemory model = new ModelMemory();
        ViewMemory view = new ViewMemory(model);
        model.addObserver(view);

        Scene scene = new Scene(view);
        primaryStage.setTitle("Memory");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
