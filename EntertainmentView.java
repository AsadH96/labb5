
import Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EntertainmentView extends BorderPane {

    private EntertainmentModel model;
    private EntertainmentController controller;

    public EntertainmentView(EntertainmentModel model) {
        this.model = model;
        controller = new EntertainmentController(this, model);
        initView();
    }

    private void initLogin() {
        Stage access = new Stage();
        access.initModality(Modality.APPLICATION_MODAL);
        access.setResizable(false);
        access.setTitle("Login");

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(10);

        TextField userName = new TextField();
        PasswordField pass = new PasswordField();
        Button button = new Button();
        button.setText("Login");

        pane.add(new Label("User name:"), 1, 2);
        pane.add(userName, 2, 2);
        pane.add(new Label("Password:"), 1, 3);
        pane.add(pass, 2, 3);

        pane.add(button, 2, 5);

        Scene loginScene = new Scene(pane, 300, 200);
        access.setScene(loginScene);
        access.show();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(userName.getText() + " " + pass.getText());
                access.close();
            }
        });
    }

    private void initSignIn() {

        Stage access = new Stage();
        access.initModality(Modality.APPLICATION_MODAL);
        access.setResizable(false);
        access.setTitle("Sign in");

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(10);

        TextField userName = new TextField();
        PasswordField pass = new PasswordField();
        PasswordField reapPass = new PasswordField();
        Button button = new Button();
        button.setText("Sign in");

        pane.add(new Label("User name:"), 1, 2);
        pane.add(userName, 2, 2);
        pane.add(new Label("Password:"), 1, 3);
        pane.add(pass, 2, 3);
        pane.add(new Label("Repeat password:"), 1, 4);
        pane.add(reapPass, 2, 4);
        pane.add(button, 2, 5);

        Scene loginScene = new Scene(pane, 300, 200);
        access.setScene(loginScene);
        access.show();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (pass.getText().contains(reapPass.getText())) {
                    System.out.println(userName.getText() + " " + pass.getText() + " " + reapPass.getText());
                    access.close();
                } else {
                    System.out.println("Password does not match. Try again");
                }
            }
        });
    }

    private void initView() {
        VBox root = new VBox();
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.exitGame();
            }
        });
        MenuItem login = new MenuItem("Login");
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initLogin();
            }
        });
        MenuItem signIn = new MenuItem("Sign in");
        signIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initSignIn();
            }
        });

        fileMenu.getItems().addAll(login, signIn, exitItem);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        root.getChildren().add(menuBar);
        this.setTop(root);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(10);

        TextField searchFor = new TextField();
        PasswordField pass = new PasswordField();
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("Album", "Artist", "Music Rating", "Genre");
        Button button = new Button();
        button.setText("Search");

        pane.add(new Label("Search by:"), 1, 2);
        pane.add(comboBox, 2, 2);
        pane.add(new Label("Search for: "), 1, 3);
        pane.add(searchFor, 2, 3);
        pane.add(button, 2, 5);
        this.setCenter(pane);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    controller.searchBy(comboBox.getSelectionModel().getSelectedIndex(), searchFor.getText());
                    if (comboBox.getSelectionModel().getSelectedIndex() == 1) {
                        updateFromModelPerson();
                    }
                    if (comboBox.getSelectionModel().getSelectedIndex() == 0) {
                        updateFromModelAlbum();
                    }
                } catch (Exception ex) {
                    System.out.println("Error comboBox search");
                }
            }
        });

    }

    private void updateFromModelAlbum() {
        Stage result = new Stage();

        TableView<Album> resultTable = new TableView<Album>();
        TableColumn<Album, String> idCol = new TableColumn("Album ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("albumID"));

        TableColumn<Album, String> titleCol = new TableColumn("Title");
        titleCol.setMinWidth(100);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("albumName"));

        TableColumn<Album, String> artistCol = new TableColumn("Artist ID");
        artistCol.setMinWidth(100);
        artistCol.setCellValueFactory(new PropertyValueFactory<>("artistID"));

        TableColumn<Album, String> releaseCol = new TableColumn("Release date");
        releaseCol.setMinWidth(100);
        releaseCol.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        TableColumn<Album, String> genreIDCol = new TableColumn("GenreID");
        genreIDCol.setMinWidth(100);
        genreIDCol.setCellValueFactory(new PropertyValueFactory<>("genreID"));

        resultTable.setItems(model.getAlbumList());
        resultTable.getColumns().addAll(idCol, titleCol, artistCol, releaseCol,genreIDCol);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(resultTable);

        Scene scene = new Scene(vbox);

        result.initModality(Modality.NONE);
        result.setTitle("Result from search");
        result.setScene(scene);
        result.show();

    }

    private void updateFromModelPerson() {
        Stage result = new Stage();

        TableView<Person> resultTable = new TableView<Person>();
        TableColumn<Person, String> idCol = new TableColumn("Person ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("personID"));

        TableColumn<Person, String> nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("personName"));

        TableColumn<Person, String> roleCol = new TableColumn("Role");
        roleCol.setMinWidth(100);
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Person, String> nationalityCol = new TableColumn("Nationality");
        nationalityCol.setMinWidth(100);
        nationalityCol.setCellValueFactory(new PropertyValueFactory<>("nationality"));

        resultTable.setItems(model.getPersonList());
        resultTable.getColumns().addAll(idCol, nameCol, roleCol, nationalityCol);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(resultTable);

        Scene scene = new Scene(vbox);

        result.initModality(Modality.NONE);
        result.setTitle("Result from search");
        result.setScene(scene);
        result.show();
    }
}
