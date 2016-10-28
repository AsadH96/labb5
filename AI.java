/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Asad
 */
public class AI {

    private String name;
    private int score;
    private boolean difficulty;
    private ModelMemory model;
    private ArrayList<Object> remember = new ArrayList<Object>();

    public AI(ModelMemory model) {
        this.model = model;
    }

    public void checkIfRemember(Object card1) {
        for (int i = 0; i < remember.size(); i++) {
            if (remember.get(i).equals(card1)) {
                model.setHiddenFalse(card1);
            }
        }
    }

    public void addToRememberList(Object card) {
        if (remember.size() > 3) {
            remember.remove(0);
        }
        remember.add(card);
        //compareCardList();
    }

    public void removeFromRememberList(Object card) {
        while (remember.contains(card)) {
            remember.remove(card);
        }
    }

    /*private void compareCardList(){
        if
    }*/
    public Object getAll() {
        return remember.clone();
    }
}
