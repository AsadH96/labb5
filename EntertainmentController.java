
import Model.EntertainmentModel;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class EntertainmentController {
    private EntertainmentView view;
    private EntertainmentModel model;
    public EntertainmentController(EntertainmentView view, EntertainmentModel model)
    {
        this.view = view;
        this.model = model;
    }
    public void exitGame()
    {
        System.exit(0);
    }
    public void searchBy(int index, String searchFor) throws Exception{
        model.search(index,searchFor);
    }
    public boolean addAlbum(String title, String artist, String year, String genre){
        try {
            if(!model.addAlbum(title,artist,year,genre))
            {
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
}
