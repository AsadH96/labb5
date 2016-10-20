
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.lang.System;
import javafx.animation.AnimationTimer;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    private ArrayList<Integer> cardsNotAvailable = new ArrayList<Integer>();
    //private ArrayList<Object> rectList=new ArrayList<Object>();
    private boolean cardIsPicked = false;
    private Object previousCard;
    private int tempIndex;
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

//        VBox bottomPane = new VBox();
//        bottomPane.setAlignment(Pos.BASELINE_LEFT);
//        //bottomPane.setVgap(1);
//        bottomPane.setPadding(new Insets(0, 0, 0, 0));
//        bottomPane.getChildren().add(new Button("Hit me!"));
//        bottomPane.getChildren().add(new Button("No, hit me!"));
//        this.setRight(bottomPane);
        updateCards();

        Scene scene = new Scene(this);

        primaryStage.setTitle("Memory");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void updateCards() {
        gridPane = new GridPane();
        //gridPane.setGridLinesVisible(true);
        gridPane.setVgap(20);
        gridPane.setHgap(40);

//        for (int i = 0; i < model.getNrOfCards(); i++) {
//            if (!cardsNotAvailable.contains(i)) {
//                model.getCardFromIndex(i).setOnMouseClicked(new ClickHandler());
//                rectList.add(model.getCardFromIndex(i));
//            }
//        }

        

        int t = 0;
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 4; row++) {
                //gridPane.add(rectList.get(i), col, row);
//                model.getCardFromIndex(t).setOnMouseClicked(new ClickHandler());
                if (!cardsNotAvailable.contains(t)) {
                    model.getCardFromIndex(t).setOnMouseClicked(new ClickHandler());
                    gridPane.add(model.getCardFromIndex(t), col, row);
//                rectList.add(model.getCardFromIndex(i));
                } else {
                    System.out.println("hehhhehhehhehh");
                    gridPane.getChildren().remove(model.getCardFromIndex(t));
                    StackPane sp=new StackPane();
                    Rectangle rect;
                    rect = new Rectangle(70, 100);
                    rect.setFill(Color.RED);
                    rect.setStroke(Color.AQUA);
                    
                    sp.getChildren().addAll(rect, model.getCardFromIndex(t));
                    gridPane.add(sp, col, row);
                }

                t++;
            }
        }

        System.out.println(cardsNotAvailable + " Cards not available");

        this.setCenter(gridPane);
    }

    private class ClickHandler implements EventHandler<MouseEvent> {

        int col, row, preCol, preRow;

        @Override
        public void handle(MouseEvent event) {

            for (int i = 0; i < model.getNrOfCards(); i++) {
                if (model.getCardFromIndex(i).equals(event.getSource())) {
                    clickedIndex = i;
                    System.out.println("CLicked index  " + clickedIndex);
                }
            }
            //controller.hideCard(event.getSource());
//            col = GridPane.getColumnIndex((StackPane) event.getSource());
//            row = GridPane.getRowIndex((StackPane) event.getSource());

            model.setHiddenFalseIndex(clickedIndex);

//            controller.setIndex(clickedIndex);
//            controller.start();
            if (cardIsPicked) {
                System.out.println("Korten som skickas " + model.getCardFromIndex(tempIndex) + model.getCardFromIndex(clickedIndex));

                if (controller.handlePickedEvent(previousCard, event.getSource())) {
                    System.out.println("You have a pair!");

                    cardsNotAvailable.add(tempIndex);
                    cardsNotAvailable.add(clickedIndex);

                } else {
                    int k = 0;
                    long time = System.currentTimeMillis();
                    while (k == 0) {
                        long timeAdd = System.currentTimeMillis();
                        if (timeAdd > (time + 350)) {
                            model.setHiddenTrue(event.getSource());
                            model.setHiddenTrue(previousCard);
                            k = 1;
                        }
                    }
                }
                cardIsPicked = false;
            } else {
                cardIsPicked = true;
                tempIndex = clickedIndex;

                previousCard = event.getSource();
            }

            updateCards();

//            rectList.clear();
//
//            for (int i = 0; i < model.getNrOfCards(); i++) {
//                initTheCards = new CreateRect(i);
//                //initTheCards.setOnMouseClicked(new ClickHandler());
//                rectList.add(initTheCards);
//                //rectList.get(i).setOnMouseClicked(new ClickHandler());
//            }
//
//            for (int i = 0; i < model.getNrOfCards(); i++) {
//                if (!cardsNotAvailable.contains(i)) {
//                    rectList.get(i).setOnMouseClicked(new ClickHandler());
//                    //rectList.add(initTheCards);
//                }
//            }
//
//            int i = 0;
//
//            for (int col = 0; col < 5; col++) {
//                for (int row = 0; row < 4; row++) {
//                    gridPane.add(rectList.get(i), col, row);
//                    i++;
//                }
//            }
        }

    }
//
//    public void sleep() {
//        try {
//            Thread t= new Thread();
//            t.join();
//            t.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ViewMemory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    private class CreateRect extends StackPane {
//
//        char letter;
//        private Rectangle rect, rect2;
//        private Text text;
//        private int index;
//
//        public CreateRect(int index) {
//            this.index = index;
//            initCard(index);
//            letter = model.getCharacterOfCard(index);
//            //model.hideCard();
//        }
//
//        public void initCard(int index) {
//
//            getChildren().removeAll();
//
//            rect = new Rectangle(70, 100);
//
//            getChildren().addAll(rect, model.getCardFromIndex(index));
//        }
//
//        public int getIndex() {
//            return index;
//        }
//    }

//    private class InitCard extends StackPane {
//
//        char letter;
//        private Rectangle rect, rect2;
//        private Text text;
//        private int index;
//
//        public InitCard(int index) {
//            this.index = index;
//            initCard(index);
//            letter = model.getCharacterOfCard(index);
//            model.hideCard();
//        }
//
//        public void initCard(int index) {
//
//            rect = new Rectangle(70, 100);
//            rect.setFill(Color.RED);
//            rect.setStroke(Color.AQUA);
//    getChildren().add(rect, model.getCardFromIndex(index));
//    }
//    public void realRect() {
//            rect2 = new Rectangle(70, 100);
//            rect2.setFill(Color.RED);
//            rect2.setStroke(Color.AQUA);
//            
//            getChildren().removeAll(rect);
//            getChildren().addAll(rect2, model.getCardFromIndex(index));
//        }
//
//        public void hide(int col, int row, Object card) {
//
//            gridPane.getChildren().remove(card);
//
//            realRect();
//
//            rect2 = new Rectangle(70, 100);
//            rect2.setFill(Color.RED);
//            rect2.setStroke(Color.AQUA);
//            
//            getChildren().removeAll(rect);
//            
//            getChildren().addAll(rect2, model.getCardFromIndex(clickedIndex));
//            
//            System.out.println(getIndex() + " index");
//            System.out.println(col + "row" + row);
//            System.out.println(model.getCardFromIndex(clickedIndex) + "the index clicked");
//            gridPane.add(this, col, row);
//            
//            getChildren().addAll(rect2, model.getCardFromIndex(index));
//
//            rect2 = new Rectangle(70, 100);
//            rect2.setFill(Color.RED);
//            rect2.setStroke(Color.RED);
//    getChildren().addAll(this.rect2, model.getCardFromIndex(index2));
//        }
//
//        public int getIndex() {
//            return index;
//        }
//
//        @Override
//        public String toString() {
//            return String.valueOf(letter);
//        }
//    }
// public void realRect() {
//            rect2 = new Rectangle(70, 100);
//            rect2.setFill(Color.RED);
//            rect2.setStroke(Color.AQUA);
//            
//            //getChildren().removeAll(rect);
//            //getChildren().addAll(rect2, model.getCardFromIndex(index));
//        }
//
// public void hide(int col, int row, Object card) {
//
//            
//            gridPane.getChildren().remove(card);
//
//            realRect();
//
//            rect2 = new Rectangle(70, 100);
//            rect2.setFill(Color.RED);
//            rect2.setStroke(Color.AQUA);
//            
//            getChildren().removeAll(rect);
//            
//            getChildren().addAll(rect2, model.getCardFromIndex(clickedIndex));
//            
//            System.out.println(getIndex() + " index");
//            System.out.println(col + "row" + row);
//            System.out.println(model.getCardFromIndex(clickedIndex) + "the index clicked");
//            gridPane.add(this, col, row);
//            
//            
//            
//            
//            
//            
//            
//            
//            
//            
//            //n String.valueOf(letter);
//        }
}
