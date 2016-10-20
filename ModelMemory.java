package model;

import java.util.ArrayList;
import java.util.Collections;

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

    /*public char getCharacterOfCard(int index) {
        return cardlist.get(index).getContent();
    }*/

//    public void showCard() {
//        card.show();
//    }

    public void setHiddenTrue(Object card) {
        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                cardlist.get(i).setHiddenTrue();
            }
        }

    }

    public void setHiddenFalse(Object card) {

        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                cardlist.get(i).setHiddenFalse();
            }
        }
//        cardlist.get(index).setHiddenFalse();
//        System.out.println("Index: (model) " + index);
    }
    
    public void setHiddenFalseIndex(int index) {

//        for (int i = 0; i < cardlist.size(); i++) {
//            if (cardlist.get(i).equals(card)) {
//                cardlist.get(i).setHiddenFalse();
//            }
//        }
        cardlist.get(index).setHiddenFalse();
        //System.out.println("Index: (model) " + index);
    }

    public int getNrOfCards() {
        return cardlist.size();
    }
    
    public boolean getHidden(Object card){
        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                return cardlist.get(i).getHidden();
            }
        }
        return false;
    }

    public boolean handleCardsPicked(String firstCard, String secondCard) {
        if (firstCard.equals(secondCard)) {
            return true;
        } else {
            return false;
        }
    }

}
