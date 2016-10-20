/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.ModelMemory;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Asad
 */
public class MainMemory extends Application {

    private TextField skrInput;
    private Label usdValue;

    @Override
    public void start(Stage primaryStage) {

//        GridPane pane = new GridPane();
//        Scene scene = new Scene(pane, 1000, 800);
//
//        primaryStage.setTitle("Empty stage");
//        primaryStage.setScene(scene);
//
//        primaryStage.show();
//        skrInput = new TextField("Enter value...");
//        usdValue = new Label("- - -");
        ModelMemory model = new ModelMemory();
        ViewMemory view = new ViewMemory(model, primaryStage);

//        view.initView(primaryStage);
//        BorderPane border = new BorderPane();
//
//        VBox root = new VBox();
//        VBox root2 = new VBox();
//        Menu fileMenu = new Menu("File");
//        MenuItem exitItem = new MenuItem("Exit");
//        fileMenu.getItems().add(exitItem);
//
//        MenuBar menuBar = new MenuBar();
//        menuBar.getMenus().addAll(fileMenu);
//        root.getChildren().add(menuBar);
//
//        Label topLabel = new Label("This is the top area");
//        topLabel.setPadding(new Insets(5, 5, 5, 5));
//        border.setTop(root);
//
//        TilePane tile = new TilePane();
//        tile.setHgap(8);
//        tile.setPrefColumns(4);
//        for (int i = 0; i < 20; i++) {
//            tile.getChildren().add(root);
//        }
//        root2.getChildren().add(tile);
//
//        FlowPane bottomPane = new FlowPane();
//        bottomPane.setAlignment(Pos.CENTER);
//        bottomPane.setHgap(10);
//        bottomPane.setPadding(new Insets(5, 5, 5, 5));
//        bottomPane.getChildren().add(new Button("Hit me!"));
//        bottomPane.getChildren().add(new Button("No, hit me!"));
//        border.setBottom(bottomPane);
//
//        TextArea textArea = new TextArea("Bla bla bla...");
//        textArea.setPrefRowCount(10);
//        textArea.setPrefColumnCount(40);
//        textArea.setWrapText(true);
//        border.setCenter(root);
//
//        Scene scene = new Scene(border);
//
//        primaryStage.setTitle("BorderPane example");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        for (int i = 0; i < 500; i++) {
//            textArea.appendText("more...");
//        }
//
//        primaryStage.setTitle("Memory");
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
//        primaryStage.show();
//
//        model.printList();
//        if (model.handleCardsPicked(1, 2)) {
//            System.out.println("hejjjjjjj");
//        }
//
//        System.out.println(model.getCardFromIndex(0));
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//
//        Scene scene = new Scene(root, 300, 250);
//
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
