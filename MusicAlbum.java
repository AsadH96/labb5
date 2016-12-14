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
public class MusicAlbum {

    private String albumID;
    private String albumName;
    private String releaseDate;
    private String genreID;

    public MusicAlbum(String albumID, String albumName, String releaseDate, String genreID) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.releaseDate = releaseDate;
        this.genreID = genreID;
    }

    public String getAlbumID() {
        return this.albumID;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public String getGenreID() {
        return this.genreID;
    }

    public String toString() {
        return this.albumID + this.albumName + this.releaseDate + this.genreID + "\n";
    }
}
