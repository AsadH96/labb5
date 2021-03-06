
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
public class ViewMemory extends BorderPane implements Observer {

    private ModelMemory model;
    private ControllerMemory controller;
    private ArrayList<Integer> cardsNotAvailable = new ArrayList<Integer>();
    private boolean cardIsPicked;
    private Object previousCard;

    public ViewMemory(ModelMemory model) {
        this.model = model;
        controller = new ControllerMemory(this, this.model);
        initView();
    }

    public void initView() {

        cardsNotAvailable.clear();

        cardIsPicked = false;
        previousCard = null;

        try {
            model.readHighscore();
        } catch (IOException ex) {
            System.out.println("Error reading highscore from file");
        }
        updatePlayers();
        updateCards();
        initMenu();

    }

    public void initMenu() {

        cardsNotAvailable.clear();

        VBox root = new VBox();

        Menu fileMenu = new Menu("File");

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.exitGame();
            }
        });

        MenuItem reset = new MenuItem("Reset");
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.resetGame();
            }
        });

        MenuItem rules = new MenuItem("How To Play");
        rules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showRules();
            }
        });

        MenuItem pause = new MenuItem("Pause Game");
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pauseGame();
            }
        });

        MenuItem highscore = new MenuItem("Highscore");
        highscore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getHighScore();
            }
        });

        fileMenu.getItems().addAll(pause, rules, highscore, reset, exit);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        root.getChildren().add(menuBar);

        this.setTop(root);
    }

    public void resetGame() {
        this.model = new ModelMemory();
        controller = new ControllerMemory(this, this.model);
        //initView();
        controller.resetGame();
    }

    public void showRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("How to play the game Memory:\n\n"
                + "Pick one card from the board and the card will flip and reveal "
                + "the content of the card. After you've picked a card, pick another card. "
                + "Your goal is to find the matching pair of the card you first picked up.\n\n"
                + "If you didn't find the matching pair, it's the other player's turn to try and "
                + "find a pair.\n\nYou need to keep your attention on the board all the time and "
                + "try to remember the position of the cards that's been revealed. The player with "
                + "the highest score wins the game.\n\nGood luck and have fun!");
        alert.setHeaderText("How To Play");
        alert.setResizable(true);
        alert.setTitle("How To Play");
        alert.show();
    }

    public void pauseGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PAUSE");
        //alert.setResizable(true);
        alert.setHeaderText("The game has been paused!\n\nClick OK to continue playing");
        alert.show();
    }

    public void updatePlayers() {
        AnchorPane anchor = new AnchorPane();
        HBox player1Box = new HBox();
        HBox player2Box = new HBox();

        String player1 = model.getPlayerName(0) + "\nPoint(s): " + model.getPlayerPoints(0);
        String player2 = model.getPlayerName(1) + "\nPoint(s): " + model.getPlayerPoints(1);

        player1Box.getChildren().add(new Text(player1));
        player2Box.getChildren().add(new Text(player2));

        HBox turnBox = new HBox();
        turnBox.getChildren().add(new Text("Player turn:\n" + model.getPlayerName(model.getPlayerTurn())));

        anchor.getChildren().addAll(player1Box, player2Box, turnBox);
        AnchorPane.setLeftAnchor(player1Box, 1.1);
        AnchorPane.setRightAnchor(player2Box, 1.1);
        AnchorPane.setLeftAnchor(turnBox, 220.0);
        this.setBottom(anchor);

        if (model.getPlayerPoints(0) + model.getPlayerPoints(1) >= 10) {
            finishGame();
        }
    }

    public void updateCards() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(40);

        int t = 0;
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 4; row++) {
                if (cardsNotAvailable.contains(t)) {
                    gridPane.getChildren().remove(model.getCardFromIndex(t));
                    StackPane sp = new StackPane();
                    Rectangle rect;
                    rect = new Rectangle(70, 100);
                    rect.setFill(Color.RED);
                    rect.setStroke(Color.AQUA);

                    Text text = new Text(String.valueOf(model.getCharacterOfCard(t)));
                    text.setFont(Font.font(50));
                    text.setStroke(Color.BLACK);
                    setAlignment(text, Pos.CENTER);

                    sp.getChildren().addAll(rect, text);
                    gridPane.add(sp, col, row);

                } else {
                    model.getCardFromIndex(t).setOnMouseClicked(new ClickHandler());
                    gridPane.add(model.getCardFromIndex(t), col, row);
                }

                t++;
            }
        }

        this.setCenter(gridPane);
    }

    public void finishGame() {
        if (model.getPlayerPoints(0) > model.getPlayerPoints(1)) {
            System.out.println("");
            model.saveHighscore(0);
        } else if (model.getPlayerPoints(0) < model.getPlayerPoints(1)) {
            model.saveHighscore(1);
        } else {
            System.out.println("It's a draw!");
        }
    }

    public void getHighScore() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HIGHSCORE");
        alert.setHeaderText("Highscore:");
        String temp = "Position   Points\n------------------\n";
        for (int i = 0; i < model.getHighscoreSize(); i++) {
            temp += i + 1 + ": \t\t" + model.getHighscore(i) + "\n\n";
        }
        alert.setContentText(temp);
        alert.show();
    }

    @Override
    public void update(Observable o, Object o1) {
        cardsNotAvailable.clear();
        updateCards();
        updatePlayers();
        cardIsPicked = false;
    }

    private class ClickHandler implements EventHandler<MouseEvent> {

        int col, row, preCol, preRow;

        @Override
        public void handle(MouseEvent event) {

            for (int i = 0; i < model.getNrOfCards(); i++) {
                if (model.getCardFromIndex(i).equals(event.getSource())) {
                    controller.setClickedIndex(i);
                }
            }

            controller.setHiddenFalse(event.getSource());

            model.handleAICardsPicked(event.getSource());

            if (cardIsPicked) {

                if (controller.handlePickedEvent(previousCard, event.getSource())) {

                    cardsNotAvailable.add(controller.getClickedIndex());
                    controller.addPoint(controller.getPlayerTurn());

                    //model.removeCardFromAI(event.getSource());
                } else {

                    cardsNotAvailable.remove(cardsNotAvailable.size() - 1);

                    int k = 0;
                    long time = System.currentTimeMillis();

                    while (k == 0) {
                        long timeAdd = System.currentTimeMillis();
                        if (timeAdd > (time + 350)) {
                            controller.setHiddenTrue(event.getSource());
                            controller.setHiddenTrue(previousCard);
                            k = 1;
                        }
                    }
                    controller.switchPlayerTurn();
                }
                cardIsPicked = false;
            } else {
                cardIsPicked = true;
                cardsNotAvailable.add(controller.getClickedIndex());
                controller.setTempIndex(controller.getClickedIndex());
                previousCard = event.getSource();
            }
        }

    }
}
