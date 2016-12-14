/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EntertainmentModel;

/**
 *
 * @author Asad
 */
public class EntertainmentController {

    private EntertainmentModel model;
    private EntertainmentView view;

    public EntertainmentController(EntertainmentModel model, EntertainmentView view) {
        this.model = model;
        this.view = view;
    }

    public void search(int category, String searchWord) {
        model.search(category, searchWord);
    }
    
    public boolean addAlbum(String albumName, String artist, String released, String genre){
        try {
            //System.out.println(albumName + artist + released + genre);
            if(!model.addAlbum(albumName, artist, released, genre)){
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(EntertainmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public void addPerson(String name, String role, String nationality){
        try {
            model.addPerson(name, role, nationality);
        } catch (Exception ex) {
            Logger.getLogger(EntertainmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addGenre(String category){
        try {
            model.addGenre(category);
        } catch (Exception ex) {
            Logger.getLogger(EntertainmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addArtist(String albumID, String artistID){
        model.addArtistToAlbum(albumID, artistID);
    }

    public void login() {

    }

    public void exitGame() {
        System.exit(0);
    }
}
