package memory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MemoryView extends BorderPane implements Observer {

    private MemoryModel model;
    private MemoryController controller;
    private boolean cardIsPicked = false;
    private Object temp, temp1;
    private FlowPane middlePane;
    private Rectangle borders;
    private Text text;
    private ArrayList<StackPane> sp;
    private ArrayList<Object> cards;

    public MemoryView(MemoryModel model) {
        this.model = model;
        controller = new MemoryController(this, model);
        initView();
        //updateFromModel();
    }

    public void updateOnReset() {
        updateFromModel();
        updatePlayers();
        cardIsPicked = false;
//        this.getChildren().clear(); //updated
//        initView(); // updated
    }

    private void initView() {
        try {
            model.readHighscore();
        } catch (IOException ex) {
            System.out.println("Error reading highscore from file");
        }
        //Menu
        VBox root = new VBox();
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
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
        MenuItem rules = new MenuItem("Show rules");
        rules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.showRules();
            }
        }
        );
        MenuItem highscore = new MenuItem("Highscore");
        highscore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.getHighScore();
            }
        });
        MenuItem pause = new MenuItem("Pause");
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.pause();
            }
        });
        fileMenu.getItems().addAll(rules, reset, highscore, pause, exitItem);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        root.getChildren().add(menuBar);
        this.setTop(root);

        //Center of the box
        updateFromModel();
        updatePlayers();

    }

    public void updatePlayers() {
        AnchorPane ap = new AnchorPane();
        HBox box = new HBox();
        HBox box1 = new HBox();
        HBox turn = new HBox();
        box.getChildren().add(new Text(model.getPlayer(0)));
        box1.getChildren().add(new Text(model.getPlayer(1)));
        turn.getChildren().add(new Text("Player turn:\n" + model.getPlayerName(model.getPlayerTurn())));

        ap.getChildren().addAll(box1, box, turn);
        AnchorPane.setLeftAnchor(box, 1.1);
        AnchorPane.setLeftAnchor(turn, 220.0);
        AnchorPane.setRightAnchor(box1, 1.1);

        if (model.getPlayerPoints(0) + model.getPlayerPoints(1) >= 10) {
            System.out.println("It's done");
            if(model.getPlayerPoints(0)>model.getPlayerPoints(1)){
                model.getHighestScore(0);
            }else if(model.getPlayerPoints(0)<model.getPlayerPoints(1)){
                model.getHighestScore(1);
            }else{
                System.out.println("It's a draw!");
            }
        }

        this.setBottom(ap);
    }

    public void updateFromModel() {
        //sp.clear();
        // cards.clear();
        sp = new ArrayList<StackPane>();
        cards = new ArrayList<Object>();
        cards = model.getCards();

        FlowPane middlePane = new FlowPane();
        middlePane.setPadding(new Insets(50, 50, 50, 50));
        middlePane.setVgap(10);
        middlePane.setHgap(10);

        for (int i = 0; i < model.getNrOfCards(); i++) {
            StackPane stack = new StackPane();
            borders = new Rectangle(70, 100);
            borders.setFill(Color.WHITE);
            borders.setStroke(Color.BLACK);

            text = new Text(String.valueOf(model.getCardContent(i)));
            text.setStroke(Color.BLACK);
            text.setFont(Font.font(30));
            stack.getChildren().addAll(borders, text);

            sp.add(stack);
            middlePane.getChildren().add(stack);
            stack.setOnMouseClicked(new MouseClickHandler());
            hideFromStart();
        }
        this.setCenter(middlePane);
    }

    public void showCard(Object stack) {
        for (int i = 0; i < sp.size(); i++) {
            if (stack.equals(sp.get(i))) {
                System.out.println(sp.get(i).getChildren().get(1));
                FadeTransition fade = new FadeTransition(Duration.seconds(0.5), sp.get(i).getChildren().get(1));
                fade.setToValue(1);
                fade.play();
                model.showCard(i);
            }
        }
    }

    public void hideFromStart() {
        FadeTransition fade = new FadeTransition(Duration.seconds(0.001), text);
        fade.setToValue(0);
        fade.play();

    }

    public void hideCard(Object stack) {
        for (int i = 0; i < sp.size(); i++) {
            if (stack.equals(sp.get(i))) {
                FadeTransition fade = new FadeTransition(Duration.seconds(1), sp.get(i).getChildren().get(1));
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.play();

                model.hideCard(i);
            }
        }
    }

    @Override
    public String toString() {
        String info = "Text: " + text;
        return info;
    }

    @Override
    public void update(Observable o, Object o1) {
        updateOnReset();
    }

    public Object getCard(Object stack) {
        for (int i = 0; i < sp.size(); i++) {
            if (stack.equals(sp.get(i))) {
                return cards.get(i);
            }
        }
        return null;
    }

    private class MouseClickHandler implements EventHandler<MouseEvent> {

        @Override

        public void handle(MouseEvent event) {
            //showCard(event.getSource());
//hej
            if (model.getChangeable(getCard(event.getSource()))) {
                showCard(event.getSource());
                if (cardIsPicked) {
                    if (!controller.compareCards(getCard(event.getSource()), getCard(temp))) {
                        System.out.println("Not equal");
                        hideCard(temp);
                        hideCard(event.getSource());
                        model.switchPlayerTurn();
                    } else {
                        model.addPoint(model.getPlayerTurn());
                    }
                    cardIsPicked = false;
                } else {
                    cardIsPicked = true;
                    temp = event.getSource();
                }
            }
            updatePlayers();
        }
    }
}
