package model;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.shape.Rectangle;

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

    /*public Card createCard(char character){
        Card card=new Card(character);
        return card;
    }*/
    public void addCards() {
        for (char alphabet = 'A'; alphabet <= 'J'; alphabet++) {

            card = new Card(alphabet);
            cardlist.add(card);
            card = new Card(alphabet);
            cardlist.add(card);
        }
        shuffleCards();
    }

    public void shuffleCards() {
        Collections.shuffle(cardlist);
    }

    public void printList() {
        System.out.println(cardlist.toString());
    }

    public Card getCardFromIndex(int index) {
        /*Card c = new Card(cardlist.get(index).getContent());
        return c;*/
        return cardlist.get(index);
    }

    public char getCharacterOfCard(int index) {
        return cardlist.get(index).getContent();
    }

    public void showCard() {
        card.show();
    }
    
    public void setHiddenTrue(Card card){
        
    }
    
    public void setHiddenFalse(int index){
        /*for(int i=0; i<cardlist.size();i++){
            if(cardlist.get(i).toString().equals(card.toString())){
                System.out.println("hejsansvejsan");
            }
        }*/
        
        cardlist.get(index).setHiddenFalse();
        System.out.println("Index: (model) " + index);
    }

    public void hideCard(Object card) {
        
        //this.card.hide(card);
        
        //System.out.println(card + " card");
        
        /*for (int i = 0; i < cardlist.size(); i++) {
            //System.out.println(cardlist.get(i) + " - cardlist");
            if (cardlist.get(i).toString().equals(card.toString())) {
                System.out.println(this.card.getContent() + " + " + card.toString());
                cardlist.get(i).hide();
                //cardlist.remove(i);
            }
        }*/
    }

    /*public Rectangle getCardRect(int index){
        Rectangle rect=new Rectangle();
        rect=cardlist.get(index).getRectangle();
        return rect;
    }*/
    public int getNrOfCards() {
        return cardlist.size();
    }

    public boolean handleCardsPicked(Object firstCard, Object secondCard) {
        if (firstCard.equals(secondCard)) {
            //System.out.println(cardlist.contains(secondCard.toString())+"hej");
            //System.out.println(cardlist);
            for (int i = 0; i < cardlist.size(); i++) {
                if (cardlist.get(i).equals(secondCard)) {
                    cardlist.remove(i);
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
