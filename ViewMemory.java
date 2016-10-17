
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Card;

import model.ModelMemory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asad
 */
public class ViewMemory extends BorderPane {

    private final ModelMemory model;
    private ControllerMemory controller;
    private ArrayList<CopyCard> rectList = new ArrayList<CopyCard>();
    private boolean cardIsPicked = false;
    private Object temp;
    CopyCard initTheCards;
    GridPane gridPane;
    private int clickedIndex;

    public ViewMemory(ModelMemory model, Stage primaryStage) {
        this.model = model;
        controller = new ControllerMemory(this, this.model);
        initView(primaryStage);
    }

    public void initView(Stage primaryStage) {
        //this.setPadding(new Insets(20, 20, 20, 20));

        //BorderPane border = new BorderPane();
        VBox root = new VBox();

        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().add(exitItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        root.getChildren().add(menuBar);

        this.setTop(root);

        FlowPane bottomPane = new FlowPane();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setHgap(10);
        bottomPane.setPadding(new Insets(5, 0, 0, 5));
        bottomPane.getChildren().add(new Button("Hit me!"));
        bottomPane.getChildren().add(new Button("No, hit me!"));
        this.setBottom(bottomPane);

        gridPane = new GridPane();
        //gridPane.setGridLinesVisible(true);
        gridPane.setVgap(20);
        gridPane.setHgap(40);

        for (int i = 0; i < model.getNrOfCards(); i++) {
            initTheCards = new CopyCard(i);
            initTheCards.setOnMouseClicked(new ClickHandler());
            rectList.add(initTheCards);
        }

        int i = 0;
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 4; row++) {
                gridPane.add(rectList.get(i), col, row);
                i++;
            }
        }

        this.setCenter(gridPane);

        Scene scene = new Scene(this);

        primaryStage.setTitle("Memory");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private class ClickHandler implements EventHandler<MouseEvent> {

        int col, row;

        @Override
        public void handle(MouseEvent event) {

            //controller.hideCard(event.getSource());
            //col = GridPane.getColumnIndex((StackPane) event.getSource());
            //row = GridPane.getRowIndex((StackPane) event.getSource());

            for (int i = 0; i < rectList.size(); i++) {
                if (rectList.get(i).equals(event.getSource())) {
                    clickedIndex = rectList.get(i).getIndex();
                }
            }

            System.out.println(event.getSource() + " source");

            model.setHiddenFalse(clickedIndex);
            
            rectList.clear();
            
            for (int i = 0; i < model.getNrOfCards(); i++) {
                initTheCards = new CopyCard(i);
                initTheCards.setOnMouseClicked(new ClickHandler());
                rectList.add(initTheCards);
            }

            int i = 0;
            
            for (int col = 0; col < 5; col++) {
                for (int row = 0; row < 4; row++) {
                    gridPane.add(rectList.get(i), col, row);
                    i++;
                }
            }

            //initTheCards.hide(col, row, event.getSource());
            System.out.println("Column: " + col + " Row: " + row);

            //initTheCards.open();
            /*if (cardIsPicked == true) {
                System.out.println("Korten som skickas " + temp + event.getSource());
                if (controller.handlePickedEvent(event.getSource().toString(), temp.toString())) {
                    System.out.println("You have a pair!");

                }
                cardIsPicked = false;
            } else {
                cardIsPicked = true;
                temp = event.getSource();
            }*/
        }
    }

    private class CopyCard extends StackPane {

        char letter;
        private Rectangle rect, rect2;
        private Text text;
        private int index;

        public CopyCard(int index) {
            this.index = index;
            initCard(index);
            letter = model.getCharacterOfCard(index);
            //model.hideCard();
        }

        public void initCard(int index) {

            getChildren().removeAll();
            
            rect = new Rectangle(70, 100);
            rect.setFill(Color.RED);
            rect.setStroke(Color.AQUA);
            getChildren().addAll(rect, model.getCardFromIndex(index));
        }

        /*public void realRect() {
            rect2 = new Rectangle(70, 100);
            rect2.setFill(Color.RED);
            rect2.setStroke(Color.AQUA);
            
            //getChildren().removeAll(rect);
            //getChildren().addAll(rect2, model.getCardFromIndex(index));
        }*/

 /*public void hide(int col, int row, Object card) {

            
            //gridPane.getChildren().remove(card);

            realRect();

            rect2 = new Rectangle(70, 100);
            rect2.setFill(Color.RED);
            rect2.setStroke(Color.AQUA);
            
            //getChildren().removeAll(rect);
            
            getChildren().addAll(rect2, model.getCardFromIndex(clickedIndex));
            
            //System.out.println(getIndex() + " index");
            System.out.println(col + "row" + row);
            System.out.println(model.getCardFromIndex(clickedIndex) + "the index clicked");
            //gridPane.add(this, col, row);
            
            
            
            
            
            
            
            
            
            
            //n String.valueOf(letter);
        }*/
        public int getIndex() {
            return index;
        }
    }

    /*private class InitCard extends StackPane {

        char letter;
        private Rectangle rect, rect2;
        private Text text;
        private int index;

        public InitCard(int index) {
            this.index = index;
            initCard(index);
            letter = model.getCharacterOfCard(index);
            //model.hideCard();
        }

        public void initCard(int index) {

            rect = new Rectangle(70, 100);
            rect.setFill(Color.RED);
            rect.setStroke(Color.AQUA);*/
    //getChildren().add(rect/*, model.getCardFromIndex(index)*/);
    //}

    /*public void realRect() {
            rect2 = new Rectangle(70, 100);
            rect2.setFill(Color.RED);
            rect2.setStroke(Color.AQUA);
            
            //getChildren().removeAll(rect);
            getChildren().addAll(rect2, model.getCardFromIndex(index));
        }

        public void hide(int col, int row, Object card) {

            gridPane.getChildren().remove(card);

            realRect();

            rect2 = new Rectangle(70, 100);
            rect2.setFill(Color.RED);
            rect2.setStroke(Color.AQUA);
            
            //getChildren().removeAll(rect);
            
            getChildren().addAll(rect2, model.getCardFromIndex(clickedIndex));
            
            //System.out.println(getIndex() + " index");
            System.out.println(col + "row" + row);
            System.out.println(model.getCardFromIndex(clickedIndex) + "the index clicked");
            gridPane.add(this, col, row);
            
            //getChildren().addAll(rect2, model.getCardFromIndex(index));

            /*rect2 = new Rectangle(70, 100);
            rect2.setFill(Color.RED);
            rect2.setStroke(Color.RED);*/
    //getChildren().addAll(this.rect2, model.getCardFromIndex(index2));*/
//        }
/*
        public int getIndex() {
            return index;
        }

        @Override
        public String toString() {
            return String.valueOf(letter);
        }
    }*/
}
