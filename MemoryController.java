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
        System.exit(0);
    }

    public void resetGame() {
        model.resetGame();
        view.UpdateOnReset();
    }

    public void showRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(model.showRules());
        alert.setHeaderText("How To Play");
        alert.setResizable(true);
        alert.setTitle("How To Play");
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
