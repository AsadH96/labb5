/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public interface DatabaseInterface {

    public void executeQuery(Connection con, String query, int chosenCategory) throws SQLException;

    public void executeUpdate(Connection con, String update) throws SQLException;

    public void executeQueryToFindArtistFromID(Connection con, String query) throws SQLException;

    public void executeQueryToFindGenreFromID(Connection con, String query) throws SQLException;

    public void executeQueryToFindAlbumsWithSpecificGenre(Connection con, String query) throws SQLException;
}
