/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import model.EntertainmentModel;
/**
 *
 * @author Asad
 */
public class EntertainmentController {
    
    private EntertainmentModel model;
    private EntertainmentView view;
    
    public EntertainmentController(EntertainmentModel model, EntertainmentView view){
        this.model = model;
        this.view = view;
    }
    
    public void search(int category, String searchWord){
        model.search(category, searchWord);
    }
    
    public void login(){
        
    }
    
    public void exitGame() {
        System.exit(0);
    }
}
