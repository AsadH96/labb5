/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.animation.AnimationTimer;
import model.ModelMemory;

/**
 *
 * @author Asad
 */
public class ControllerMemory extends AnimationTimer {
    ModelMemory model;
    ViewMemory view;
    Object temp;
    private int index;
    
    public ControllerMemory(ViewMemory view, ModelMemory model){
        this.view = view;
        this.model = model;
    }
    
    public boolean handlePickedEvent(Object firstCard, Object secondCard){
        System.out.println(firstCard + " " + secondCard);
        if(model.handleCardsPicked(firstCard.toString(), secondCard.toString())){
            return true;
        }else{
            return false;
        }
        /*if(firstCard.equals(secondCard)){
            System.out.println("Du har par");
        }*/
    }
    
    public void showCard(){
        
    }
    
    public void setIndex(int index){
        this.index=index;
    }
    
    /*public void hideCard(Object card){
        System.out.println(card);
        model.hideCard(card);
        //System.out.println("hej");
    }*/

    @Override
    public void handle(long now) {
        model.setHiddenFalseIndex(index);
    }
}
