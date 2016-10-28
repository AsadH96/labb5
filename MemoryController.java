/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;
import javafx.scene.control.Alert;

/**
 *
 * @author LENOVO
 */
public class MemoryController {

    private MemoryView view;
    private MemoryModel model;
    private Object temp1, temp2;

    public MemoryController(MemoryView view, MemoryModel model) {
        this.model = model;
        this.view = view;
    }

    public void exitGame() {
        model.exitGame();
    }

    public void resetGame() {
        model.resetGame();
        //view.updateOnReset();
    }//Hej

    public void showRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(model.showRules());
        alert.setHeaderText("How To Play");
        alert.setResizable(true);
        alert.setTitle("How To Play");
        alert.show();
    }
    public void getHighScore(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HIGHSCORE");
        //alert.setResizable(true);
        alert.setHeaderText("Highscore:");
        String temp = "Position   Points\n------------------\n";
        for (int i = 0; i < model.getHighscoreSize(); i++) {
            temp+= i+1 + ": \t\t" + model.getHighscore(i) + "\n\n";
        }
        alert.setContentText(temp);
        alert.show();

    }
    public void pause(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PAUSE");
        //alert.setResizable(true);
        alert.setHeaderText("The game has been paused!\n\nClick OK to continue playing");
        alert.show();

    }

    public boolean compareCards(Object one, Object two) {

        if (model.compareCards(one, two)) {
            return true;
        } else {
            return false;
        }
    }

}
