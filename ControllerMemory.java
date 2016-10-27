/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.animation.AnimationTimer;
import model.*;

/**
 *
 * @author Asad
 */
public class ControllerMemory {
    ModelMemory model;
    ViewMemory view;
    Object temp;
    private int index;
    private int clickedIndex, tempIndex;
    
    public ControllerMemory(ViewMemory view, ModelMemory model){
        this.view = view;
        this.model = model;
    }
    
    public void setHiddenFalse(Object card){
        model.setHiddenFalse(card);
    }
    
    public void setHiddenTrue(Object card){
        model.setHiddenTrue(card);
    }
    
    public boolean handlePickedEvent(Object firstCard, Object secondCard){
        if(model.handleCardsPicked(firstCard.toString(), secondCard.toString())){
            return true;
        }else{
            return false;
        }
        /*if(firstCard.equals(secondCard)){
            System.out.println("Du har par");
        }*/
    }
    
    public void setTempIndex(int index){
        this.tempIndex=index;
    }
    
    public void setClickedIndex(int index){
        this.clickedIndex=index;
    }
    
    public int getTempIndex(){
        return tempIndex;
    }
    
    public int getClickedIndex(){
        return clickedIndex;
    }
    
    public String getPlayer(int index){
        return "Name: " + model.getPlayerName(index) + "\nPoint(s): " + model.getPlayerPoints(index);
    }
    
    public String getPlayerName(int index){
        return model.getPlayerName(index);
    }
    
    public void addPoint(int index){
        model.addPoint(index);
    }
    
    public void switchPlayerTurn(){
        model.switchPlayerTurn();
    }
    
    public int getPlayerTurn(){
        return model.getPlayerTurn();
    }
    
    public int getPlayerPoints(int index){
        return model.getPlayerPoints(index);
    }
    
    public void resetGame(){
        model.reset();
        view.initView();
    }
    
    public void exitGame(){
        System.exit(0);
    }
    
    /*public void hideCard(Object card){
        System.out.println(card);
        model.hideCard(card);
        //System.out.println("hej");
    }*/

}
