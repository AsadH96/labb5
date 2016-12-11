package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asad
 */
public class EntertainmentModel extends Thread implements DatabaseInterface {

    String user, server, pwd, database, category, args[], table, col1, col2, foundArtistName, foundGenreName, foundAlbumFromGenre;
    int whereCond = 0;
    private ObservableList<Person> personList = FXCollections.observableArrayList();
    private ObservableList<MusicAlbum> albumList = FXCollections.observableArrayList();
    private ObservableList<Genre> genreList = FXCollections.observableArrayList();
    private ArrayList<String> albumFromGenre = new ArrayList<>();

    public EntertainmentModel(String args[]) throws Exception {
        this.args = args;
        connect(args);
        //testExample();
    }

    private void connect(String args[]) {
        if (args.length != 2) {
            System.out.println("Usage: java Entertaintment <user> <password>");
            System.exit(0);
        }

        this.user = args[0]; // user name
        this.pwd = args[1]; // password 
        this.database = "Entertainment"; // the name of the database in your DBMS
        this.server
                = "jdbc:mysql://localhost:3306/" + database
                + "?UseClientEnc=UTF8";
    }

    public void search(int category, String searchWord) {
        personList.clear();
        albumList.clear();
        genreList.clear();
        albumFromGenre.clear();

        if (category == 0) {
            this.table = "Person";
            this.col1 = "role";
            this.category = "Artist";
            this.col2 = "personName";
            this.whereCond = 2;
        } else if (category == 1) {
            this.table = "MusicAlbum";
            this.col1 = "albumName";
            this.whereCond = 1;
        } else if (category == 2) {

        } else if (category == 3) {
            this.table = "Genre";
            this.col1 = "Category";
            this.whereCond = 1;
        } else if (category == 4) {
            this.table = "Movie";
            this.col1 = "title";
            this.whereCond = 1;
        } else if (category == 5) {

        } else if (category == 6) {
            this.category = "Director";
            this.table = "Person";
            this.col1 = "role";
            this.col2 = "personName";
            this.whereCond = 2;
        }
        connect(args);
        try {
            new Thread() {

                public void run() {
                    try {
                        testExample(category, searchWord);
                    } catch (Exception ex) {
                        Logger.getLogger(EntertainmentModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    javafx.application.Platform.runLater(new Runnable() {
                        public void run() {
                            //updateUI(empList);
                        }
                    });
                }
            }.start();            
        } catch (Exception ex) {
            Logger.getLogger(EntertainmentModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testExample(int chosenCategory, String searchWord) throws Exception {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Driver d = new com.mysql.jdbc.Driver();
            con = DriverManager.getConnection(server, user, pwd);
            System.out.println("Connected!");
//            System.out.println(this.searchWord + this.table + this.category);

//            executeQuery(con, "SELECT * FROM Person");
//            executeUpdate(con, "INSERT "
//                    + "INTO MusicAlbum (albumID, albumName, artistID, releaseDate, genre)"
//                    + "VALUES ('Avatar', '2009', '')");
            if (whereCond == 1) {
                executeQuery(con, "SELECT * FROM " + this.table
                        + " WHERE " + col1 + " LIKE '%" + searchWord + "%'", chosenCategory);
            } else if (whereCond == 2) {
                executeQuery(con, "SELECT * FROM " + this.table
                        + " WHERE " + col1 + " = '" + this.category
                        + "' AND " + col2 + " LIKE '%" + searchWord + "%'", chosenCategory);
            }
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
            }
        }
    }

    public ObservableList<Person> getPersonList() {
        ObservableList<Person> copyPersonList = FXCollections.observableArrayList();
        copyPersonList = personList;
        return copyPersonList;
    }

    public ObservableList<MusicAlbum> getAlbumList() {
        ObservableList<MusicAlbum> copyAlbumList = FXCollections.observableArrayList();
        copyAlbumList = albumList;
        return copyAlbumList;
    }

    @Override
    public void executeQuery(Connection con, String query, int chosenCategory) throws SQLException {

        Statement stmt = null;
        try {
            // Execute the SQL statement
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            // Get the attribute names
            ResultSetMetaData metaData = rs.getMetaData();
            int ccount = metaData.getColumnCount();
            for (int c = 1; c <= ccount; c++) {
                System.out.print(metaData.getColumnName(c) + "\t");
            }

            System.out.println("");

            // Get the attribute values
            while (rs.next()) {

                if (chosenCategory == 0) {
                    for (int c = 1; c <= ccount; c++) {
                        System.out.print(rs.getObject(c) + "\t");
                        System.out.println("");

                        if ((c % 4) == 0) {
                            Person person = new Person(rs.getString(c - 3), rs.getString(c - 2), rs.getString(c - 1), rs.getString(c));

                            personList.add(person);
                        }
                    }
                } else if (chosenCategory == 1) {
                    for (int c = 1; c <= ccount; c++) {
                        System.out.print(rs.getString(c) + "\t");

                        if ((c % 5) == 0) {
                            searchArtistWithID(con, "Person", rs.getString(c - 2), "PersonID");
                            searchGenreWithID(con, "Genre", rs.getString(c), "genreID");

                            MusicAlbum musicAlbum = new MusicAlbum(rs.getString(c - 4), rs.getString(c - 3), this.foundArtistName, rs.getString(c - 1), this.foundGenreName);
                            albumList.add(musicAlbum);
                        }
                    }
                } else if (chosenCategory == 3) {
                    for (int c = 1; c <= ccount; c++) {
                        System.out.print(rs.getString(c) + "\t");

                        if ((c % 2) == 0) {
                            albumFromGenre.clear();
                            searchAlbumsWithSpecificGenre(con, "MusicAlbum", rs.getString(c - 1), "genreID");
                            //System.out.println(albumFromGenre + " <-- albumFromGenre");

                            for (int i = 0; i < albumFromGenre.size(); i++) {
                                this.table = "MusicAlbum";
                                this.col1 = "albumName";
                                this.whereCond = 1;
                                testExample(1, albumFromGenre.get(i));
                            }
                        }
                    }
                } else {
                    for (int c = 1; c <= ccount; c++) {
                        System.out.print(rs.getString(c) + "\t");
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EntertainmentModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public void executeQueryToFindArtistFromID(Connection con, String query) throws SQLException {

        Statement stmt = null;
        try {
            // Execute the SQL statement
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Get the attribute names
            ResultSetMetaData metaData = rs.getMetaData();
            int ccount = metaData.getColumnCount();
            for (int c = 1; c <= ccount; c++) {
                System.out.print(metaData.getColumnName(c) + "\t");
            }

            System.out.println("");

            while (rs.next()) {
                for (int c = 1; c <= ccount; c++) {
                    this.foundArtistName = rs.getString(c);
                    System.out.println("Found name " + foundArtistName);
                }

            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public void executeQueryToFindGenreFromID(Connection con, String query) throws SQLException {

        Statement stmt = null;
        try {
            // Execute the SQL statement
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Get the attribute names
            ResultSetMetaData metaData = rs.getMetaData();
            int ccount = metaData.getColumnCount();
            for (int c = 1; c <= ccount; c++) {
                System.out.print(metaData.getColumnName(c) + "\t");
            }

            System.out.println("");

            while (rs.next()) {
                for (int c = 1; c <= ccount; c++) {
                    this.foundGenreName = rs.getString(c);
                    System.out.println("Found genre " + foundGenreName);
                }

            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public void executeQueryToFindAlbumsWithSpecificGenre(Connection con, String query) throws SQLException {
        Statement stmt = null;
        try {
            // Execute the SQL statement
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Get the attribute names
            ResultSetMetaData metaData = rs.getMetaData();
            int ccount = metaData.getColumnCount();
            for (int c = 1; c <= ccount; c++) {
                System.out.print(metaData.getColumnName(c) + "\t");
            }

            System.out.println("");

            while (rs.next()) {
                for (int c = 1; c <= ccount; c++) {
                    System.out.println("Found albums with this genre: " + rs.getString(c));
                    albumFromGenre.add(rs.getString(c));
                }
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void searchArtistWithID(Connection con, String table, String searchFor, String catalog) {
        try {
            executeQueryToFindArtistFromID(con, "SELECT personName FROM " + table + " WHERE " + catalog + " = '" + searchFor + "'");
        } catch (Exception e) {
            System.out.println("Error finding artist with ID!");
        }
    }

    public void searchGenreWithID(Connection con, String table, String searchFor, String catalog) {
        try {
            executeQueryToFindGenreFromID(con, "SELECT category FROM " + table + " WHERE " + catalog + " = '" + searchFor + "'");
        } catch (Exception e) {
            System.out.println("Error finding genre with ID!");
        }
    }

    public void searchAlbumsWithSpecificGenre(Connection con, String table, String searchFor, String catalog) {
        try {
            executeQueryToFindAlbumsWithSpecificGenre(con, "SELECT albumName FROM " + table + " WHERE " + catalog + " = '" + searchFor + "'");
        } catch (Exception e) {
            System.out.println("Error finding genre with name!");
        }
    }

    @Override
    public void executeUpdate(Connection con, String update) throws SQLException {

        Statement stmt = null;
        try {
            // Execute the SQL statement
            stmt = con.createStatement();
            stmt.executeUpdate(update);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}

/*

            new Thread() {

                public void run() {
                    javafx.application.Platform.runLater(new Runnable() {
                        public void run() {
                            updateUI(empList);
                        }
                    });
                }
            }.start();

 */
