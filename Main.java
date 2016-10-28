/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage){
        //Pane pane = new Pane();
        MemoryModel model = new MemoryModel();
        MemoryView view = new MemoryView(model);
        model.addObserver(view);
        
        Scene scene = new Scene(view,500,600);
        primaryStage.setTitle("Memory");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
