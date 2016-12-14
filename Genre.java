/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Asad
 */
public class Genre {

    private String genreID;
    private String category;

    public Genre(String genreID, String category) {
        this.genreID = genreID;
        this.category = category;
    }

    public String getGenreID() {
        return this.genreID;
    }

    public String getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return this.genreID + this.category;
    }
}
