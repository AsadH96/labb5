package memory;

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MemoryView extends BorderPane {

    private MemoryModel model;
    private MemoryController controller;
    private boolean cardIsPicked = false;
    private Object temp, temp1;
    private FlowPane middlePane;

    public MemoryView(MemoryModel model) {
        this.model = model;
        controller = new MemoryController(this, model);
        initView();
        //updateFromModel();
    }

    public void UpdateOnReset() {
        updateFromModel();
        updatePlayers();
    }

    private void initView() {
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
        MenuItem pause = new MenuItem("Pause");
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.pause();
            }
        });
        fileMenu.getItems().addAll(rules, reset, pause, exitItem);
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

        this.setBottom(ap);
    }

    public void updateFromModel() {
        FlowPane middlePane = new FlowPane();
        middlePane.setPadding(new Insets(50, 50, 50, 50));
        middlePane.setVgap(10);
        middlePane.setHgap(10);
        for (int i = 0; i < model.getNrOfCards(); i++) {
            middlePane.getChildren().add(model.getCard(i));
            model.getCard(i).setOnMouseClicked(new MouseClickHandler());
        }
        this.setCenter(middlePane);
    }

    private class MouseClickHandler implements EventHandler<MouseEvent> {

        @Override

        public void handle(MouseEvent event) {
                if (model.getChangeable(event.getSource())) {

                    System.out.println(event.getSource());
                    model.showCard(event.getSource());
                    model.rememberCard(event.getSource());
                    if (cardIsPicked) {
                        if (!controller.compareCards(event.getSource(), temp)) {
                            System.out.println("Not equal");
                            model.hideCard(temp);
                            model.hideCard(event.getSource());
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
