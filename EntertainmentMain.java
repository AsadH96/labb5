/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.EntertainmentModel;

/**
 *
 * @author Asad
 */
public class EntertainmentMain extends Application {

    private static EntertainmentModel model;

    public static void main(String[] args) throws Exception {
        model = new EntertainmentModel(args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        EntertainmentView view = new EntertainmentView(model);

        Scene scene = new Scene(view, 400, 400);
        primaryStage.setTitle("Entertainment");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
