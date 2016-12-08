package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EntertainmentModel implements DatabaseInterface {

    private String user, pwd, database, server, tmp;
    private int condition;
    private ObservableList<Person> personList = FXCollections.observableArrayList();
    private ObservableList<Album> albumList = FXCollections.observableArrayList();

    public EntertainmentModel(String args[]) {
        connect(args);
    }

    private void connect(String args[]) {
        if (args.length != 2) {
            System.out.println("Usage: java Entertainment <user> <password>");
            System.exit(0);
        }

        user = args[0]; // user name
        pwd = args[1]; // password 
        database = "Entertainment"; // the name of the database in your DBMS
        server
                = "jdbc:mysql://localhost:3306/" + database
                + "?UseClientEnc=UTF8";
    }

    public void executeQuery(Connection con, String query) throws SQLException {
        personList.clear();
        albumList.clear();
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
            System.out.println();
            
            // Get the attribute values
            while (rs.next()) {
                if (condition == 2) {
                    for (int c = 1; c <= ccount; c++) {
                        System.out.print(rs.getString(c) + "\t");
                        if (c % 4 == 0) {
                            Person person = new Person(rs.getString(c - 3), rs.getString(c - 2), rs.getString(c - 1), rs.getString(c));
                            personList.add(person);
                        }
                    }
                    System.out.println();
                } else if (condition == 1) {
                    for (int c = 1; c <= ccount; c++) {
                        System.out.print(rs.getString(c) + "\t");
                        System.out.println("Hejsaaan "+c);
                        if (c % 5 == 0) {
                            searchInDB("Person", rs.getString(c-2), "personID");
                            Album album = new Album(rs.getString(c - 4), rs.getString(c - 3), this.tmp, rs.getString(c - 1), rs.getString(c));
                            tmp=null;
                            albumList.add(album);
                        }
                    }
                    System.out.println();
                }
                else{
                    for(int c=1; c<=ccount;c++){
                        System.out.print(rs.getString(c)+"\t");
                        if(c%4==0){
                            tmp=rs.getString(c-2);
                        }
                    }
                    System.out.println();
                }
            }

        } catch (Exception ex) {
            System.out.println("Error while searching");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public ObservableList getPersonList() {
        ObservableList<Person> ps = FXCollections.observableArrayList();
        ps = personList;
        return ps;
    }

    public ObservableList getAlbumList() {
        ObservableList<Album> ps = FXCollections.observableArrayList();
        ps = albumList;
        return ps;
    }

    public void searchBy(int item, String searchFor) throws Exception {
        String table = null, catalog = null, role = null;
//        if(item ==0){
//            this.table="Movie";
//            this.catalog="title";
//        }
        switch (item) {
            case 0:
                table = "Album";
                catalog = "albumName";
                this.condition = 1;
                break;
            case 1:
                table = "Person";
                catalog = "PersonName";
                role = "Artist";
                this.condition = 2;
                break;
            case 4:
                table = "Person";
                catalog = "personName";
                break;
            default:
                break;
        }
        searchInDB(table, searchFor, catalog, role);
    }
    public void searchInDB(String table, String searchFor, String catalog) throws Exception {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(server, user, pwd);
            System.out.println("Connected!");
            condition =0;
                executeQuery(con, "SELECT * FROM " + table + " WHERE " + catalog + " = '" + searchFor + "'");

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
    public void searchInDB(String table, String searchFor, String catalog, String role) throws Exception {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //java.sql.Driver d=new com.mysql.jdbc.Driver();
            con = DriverManager.getConnection(server, user, pwd);
            System.out.println("Connected!");
            if (condition == 1) {
                executeQuery(con, "SELECT * FROM " + table + " WHERE " + catalog + " LIKE '%" + searchFor + "%'");
            } else if (condition == 2) {
                executeQuery(con, "SELECT * FROM " + table + " WHERE " + catalog + " LIKE '%" + searchFor + "%'" + " AND role='" + role + "'");
            }
//            executeUpdate(con,"INSERT "
//                    + "INTO Person(personID, personName, role, nationality)"
//                    + "VALUES ('4', 'Anders', 'Teacher', 'Sverige')");
//             executeQuery(con, "SELECT * FROM Person");
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

    public void executeUpdate(Connection con, String update) throws SQLException {
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(update);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
