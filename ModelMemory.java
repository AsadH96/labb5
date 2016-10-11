package model;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asad
 */
public class ModelMemory {

    private ArrayList<Card> cardlist;
    private Card card;

    public ModelMemory() {
        cardlist = new ArrayList<Card>();
        addCards();
    }

    public void addCards() {
        for (char alphabet = 'A'; alphabet <= 'J';alphabet++) {
            
            card = new Card(alphabet);
            cardlist.add(card);
            
            if(alphabet=='A'){
                card = new Card('A');
                cardlist.add(card);
            }
        }
    }

    public void printList() {
        System.out.println(cardlist.toString());
    }
    
    public boolean handleCardsPicked(){
        
        if(cardlist.get(0).toString().equals(cardlist.get(1).toString())){
            return true;
        }
        
        return false;
    }

}
