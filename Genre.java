/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class Genre {
    private String genreID, category;
    public Genre(String genreID, String category){
        this.genreID=genreID;
        this.category=category;
    }
    public String getGenreID(){
        return genreID;
    }
    public String getCategory(){
        return category;
    }
    @Override
    public String toString(){
        String info= "GenreID:"+genreID+" Category:"+category;
        return info;
    }
}
