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
public class Album {
    private String albumID, albumName, artistID, releaseDate, genreID;
            
    public Album(String albumID, String albumName, String artistID, String releaseDate, String genreID){
        this.albumID=albumID;
        this.albumName=albumName;
        this.artistID=artistID;
        this.releaseDate=releaseDate;
        this.genreID=genreID;
    }
    public String getAlbumID(){
        return albumID;
    }
    public String getAlbumName(){
        return albumName;
    }
    public void setArtistID(String S){
        artistID=S;
    }
    public String getArtistID(){
        return artistID;
    }
    public String getReleaseDate(){
        return releaseDate;
    }
    public String getGenreID(){
        return genreID;
    }
    @Override
    public String toString(){
        String info = "AlbumID: "+albumID+" Album title:"+albumName+" ArtistID:"+artistID+" Release date:"+releaseDate+" GenreID"+genreID;
        return info;
    }
}
