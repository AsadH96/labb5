/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class AI {

    private int points;
    private String name;
    private boolean stage;
    private MemoryModel model;
    private ArrayList<Object> remember = new ArrayList<Object>();

    public AI(String name, MemoryModel model, boolean stage) {
        this.model = model;
        this.name = name;
        this.points = 0;
        this.stage = stage;
    }

    public void rememberCard(Object card) {
        if (remember.size() > 3) {
            remember.remove(0);
        }
        remember.add(card);
        System.out.println(remember);
    }

    public boolean sameCards() {
        for (int i = 0; i < remember.size(); i++) {
            for (int u = 0; u < remember.size(); u++) {
                if (remember.get(i).toString().equals(remember.get(u).toString()) && i != u) {
                    return true;
                }
            }
        }
        return false;
    }

    public void showSameCards() {
        for (int i = 0; i < remember.size(); i++) {
            for (int u = 0; u < remember.size(); u++) {
                if (remember.get(i).toString().equals(remember.get(u).toString()) && i != u) {
//                    model.showCard(remember.get(i));
//                    model.showCard(remember.get(u));
                }
            }
        }
    }//Hej

    public void test(Object card) {
        compareCardList(card);
    }

    public int getSizeList() {
        return remember.size();
    }

    public boolean compareCardList(Object card) {
        for (int i = 0; i < remember.size(); i++) {
            if (remember.get(i).toString().contains(card.toString())) {
//                model.showCard(remember.get(i));
                return true;
            }
            //forgetCard(remember.get(i));
        }
        return false;
    }

    private void forgetCard(Object card) {
        remember.remove(card);
    }

    public void addPoint() {
        this.points = this.points + 1;
    }

    public int getPoints() {
        return points;
    }

    public void resetPoints() {
        points = 0;
    }

    public String getName() {
        return name;
    }
    public void pickCard(){
        //model.showCard(name);
    }

}
