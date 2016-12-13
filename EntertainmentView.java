/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import model.*;

/**
 *
 * @author Asad
 */
public class EntertainmentView extends BorderPane {

    private EntertainmentController controller;
    private EntertainmentModel model;

    public EntertainmentView(EntertainmentModel model) {
        this.model = model;
        controller = new EntertainmentController(this.model, this);
        startMenu();
    }

    private void startMenu() {
        VBox root = new VBox();

        Menu fileMenu = new Menu("File");

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
                initSingIn();
            }
        });
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.exitGame();
            }
        });

        fileMenu.getItems().addAll(login, signIn, exit);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);
        root.getChildren().add(menuBar);

        GridPane gp = new GridPane();

        Text text = new Text("Search By: ");
        Text text2 = new Text("Search Word: ");
        ComboBox categories = new ComboBox();
        categories.getItems().addAll("Artist", "Music Album", "Music Rating (1-5)", "Genre", "Movie", "Movie Rating (1-5)", "Director");

        TextField searchWord = new TextField();

        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(categories.getSelectionModel().getSelectedIndex() + " " + searchWord.getText());
                controller.search(categories.getSelectionModel().getSelectedIndex(), searchWord.getText());
                if (categories.getSelectionModel().getSelectedIndex() == 0) {
                    showPersonResult();
                } else if (categories.getSelectionModel().getSelectedIndex() == 1 || categories.getSelectionModel().getSelectedIndex() == 3) {
                    showAlbumResult();
                }
            }
        });

        Button addAlbumBtn = new Button("Add album");
        addAlbumBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addAlbum();
            }
        });

        gp.add(text, 0, 0);
        gp.add(categories, 1, 0);
        gp.add(text2, 0, 1);
        gp.add(searchWord, 1, 1);
        gp.add(searchBtn, 0, 2);
        gp.add(addAlbumBtn, 0, 3);

        this.setCenter(gp);

        this.setTop(root);
    }

    private void initLogin() {
        Stage login = new Stage();

        login.initModality(Modality.APPLICATION_MODAL);

        GridPane gp = new GridPane();

        login.setTitle("Login Screen");

        Text text = new Text("Username: ");
        Text text2 = new Text("Password: ");

        gp.add(text, 0, 0);
        gp.add(text2, 0, 1);

        TextField username = new TextField();
        gp.add(username, 1, 0);
        PasswordField password = new PasswordField();
        gp.add(password, 1, 1);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(username.getText() + " " + password.getText());
                login.close();
            }

        });

        gp.add(loginButton, 1, 3);

        Scene loginScene = new Scene(gp, 400, 200);
        login.setScene(loginScene);
        login.show();
    }

    public void showPersonResult() {

        Stage result = new Stage();
        TableView<Person> resultTable = new TableView<>();
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
        result.setTitle("Result from artist search");
        result.setScene(scene);
        result.show();
    }

    public void showAlbumResult() {
        Stage result = new Stage();

        TableView<MusicAlbum> resultTable2 = new TableView<>();

        TableColumn<MusicAlbum, String> albumCol = new TableColumn("Album ID");
        albumCol.setMinWidth(100);
        albumCol.setCellValueFactory(new PropertyValueFactory<>("albumID"));

        TableColumn<MusicAlbum, String> nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("albumName"));

        TableColumn<MusicAlbum, String> artistCol = new TableColumn("Artist ID");
        artistCol.setMinWidth(100);
        artistCol.setCellValueFactory(new PropertyValueFactory<>("artistID"));

        TableColumn<MusicAlbum, String> releaseCol = new TableColumn("Released");
        releaseCol.setMinWidth(100);
        releaseCol.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        TableColumn<MusicAlbum, String> genreCol = new TableColumn("Genre");
        genreCol.setMinWidth(100);
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genreID"));

        resultTable2.setItems(model.getAlbumList());
        resultTable2.getColumns().addAll(albumCol, nameCol, artistCol, releaseCol, genreCol);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(resultTable2);
        Scene scene = new Scene(vbox);

        result.initModality(Modality.NONE);
        result.setTitle("Result from album search");
        result.setScene(scene);
        result.show();
    }

    public void addAlbum() {
        Stage addAlbumStg = new Stage();
        addAlbumStg.initModality(Modality.APPLICATION_MODAL);
        addAlbumStg.setTitle("Add album");

        GridPane gp = new GridPane();
        gp.hgapProperty();

        Text text = new Text("Album name:* ");
        Text text2 = new Text("Artist:* (name & surname) ");
        Text text3 = new Text("Released: (yyyy) ");
        Text text4 = new Text("Genre: ");
        Text text5 = new Text("\nExample:\n\n"
                + "Album name: Thriller\n"
                + "Artist: Michael Jackson\n"
                + "Released: 1982\n"
                + "Genre: Pop");

        TextField albumName = new TextField();
        TextField artist = new TextField();
        TextField released = new TextField();
        TextField genre = new TextField();

        Button addBtn = new Button("Add album");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!controller.addAlbum(albumName.getText(), artist.getText(), released.getText(), genre.getText())) {
                    if(!model.getPersonSuccess()){
                        addPerson();
                    }else if(!model.getGenreSuccess()){
                        addGenre();
                    }
                }
            }

        });

        gp.add(text, 0, 0);
        gp.add(text2, 0, 1);
        gp.add(text3, 0, 2);
        gp.add(text4, 0, 3);
        gp.add(albumName, 1, 0);
        gp.add(artist, 1, 1);
        gp.add(released, 1, 2);
        gp.add(genre, 1, 3);
        gp.add(addBtn, 0, 4);
        gp.add(text5, 0, 5);

        Scene addAlbumScn = new Scene(gp);
        addAlbumStg.setScene(addAlbumScn);
        addAlbumStg.show();
    }

    public void addPerson() {

        Stage updatePerson = new Stage();
        updatePerson.initModality(Modality.APPLICATION_MODAL);
        updatePerson.setTitle("Person doesn't exist, please add information about the person");

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(10);

        TextField name = new TextField();
        TextField role = new TextField();
        TextField nationality = new TextField();
        Button add = new Button();
        add.setText("Add");

        pane.add(new Label("Person Name:"), 1, 1);
        pane.add(name, 2, 1);
        pane.add(new Label("Role: (Artist/Director)"), 1, 2);
        pane.add(role, 2, 2);
        pane.add(new Label("Nationality:"), 1, 3);
        pane.add(nationality, 2, 3);
        pane.add(add, 1, 5);
        

        Scene loginScene = new Scene(pane);
        updatePerson.setScene(loginScene);
        updatePerson.show();
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.addPerson(name.getText(), role.getText(), nationality.getText());
                updatePerson.close();
            }
        });

    }
    
    public void addGenre(){
        Stage updateGenre = new Stage();
        updateGenre.initModality(Modality.APPLICATION_MODAL);
        updateGenre.setTitle("Genre doesn't exist, please add information about the genre");

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(10);

        TextField category = new TextField();
        Button add = new Button();
        add.setText("Add");

        pane.add(new Label("New Category:"), 1, 1);
        pane.add(category, 2, 1);
        pane.add(add,1,3);

        Scene loginScene = new Scene(pane, 300, 200);
        updateGenre.setScene(loginScene);
        updateGenre.show();
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.addGenre(category.getText());
                updateGenre.close();
            }
        });
    }

    private void initSingIn() {
        Stage signIn = new Stage();

        signIn.initModality(Modality.APPLICATION_MODAL);

        GridPane gp = new GridPane();
        gp.hgapProperty();

        Button signInButton;

        signInButton = new Button("Sign in");
        signIn.setTitle("Sign In Screen");

        Text text = new Text("Username: ");
        Text text2 = new Text("Password: ");
        Text text3 = new Text("Repeat Password: ");
        gp.add(text, 0, 0);
        gp.add(text2, 0, 1);
        gp.add(text3, 0, 2);

        TextField username = new TextField();
        gp.add(username, 1, 0);
        PasswordField password = new PasswordField();
        gp.add(password, 1, 1);
        PasswordField repPassword = new PasswordField();
        gp.add(repPassword, 1, 2);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (repPassword.getText().equals(password.getText())) {
                    System.out.println(username.getText() + " " + password.getText());
                    signIn.close();
                } else {
                    System.out.println("The password you wrote did not match. Please try again!");
                }
            }

        });

        gp.add(signInButton, 1, 3);

        Scene loginScene = new Scene(gp, 400, 200);
        signIn.setScene(loginScene);
        signIn.show();
    }

}
