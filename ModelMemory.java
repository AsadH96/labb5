package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private ArrayList<Card> cardlist = new ArrayList<Card>();
    private Card card;
    private Player player;
    private ArrayList<Player> playerlist = new ArrayList<Player>();
    private int playerTurn;
    private AI ai;
    private boolean playWithAI;
    private ArrayList<Integer> highscore = new ArrayList<Integer>();

    public ModelMemory() {
        ai = new AI(this); //Testing phase
        reset();
    }

    public void reset() {
        addCards();
        shuffleCards();
        initPlayers();
        playerTurn = 0;
        playerlist.get(0).resetPoints();
        playerlist.get(1).resetPoints();
    }

    public void playWithAI() {
        playWithAI = true;
        ai = new AI(this);
    }

    /*public Card createCard(char character){
        Card card=new Card(character);
        return card;
    }*/
    public void addCards() {

        cardlist.clear();

        for (char alphabet = 'A'; alphabet <= 'J'; alphabet++) {

            card = new Card(alphabet);
            cardlist.add(card);
            card = new Card(alphabet);
            cardlist.add(card);
        }
    }

    public void shuffleCards() {
        Collections.shuffle(cardlist);
    }
    
    public void sortHighscore(){
        Collections.sort(highscore, Collections.reverseOrder());
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

    public int getNrOfCards() {
        return cardlist.size();
    }

    public boolean getHidden(Object card) {
        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                return cardlist.get(i).getHidden();
            }
        }
        return false;
    }

    public void initPlayers() {
        player = new Player("Player 1");
        playerlist.add(player);
        player = new Player("Player 2");
        playerlist.add(player);
    }

    public String getPlayerName(int index) {
        return playerlist.get(index).getName();
    }

    public void switchPlayerTurn() {
        if (playerTurn == 0) {
            playerTurn = 1;
        } else {
            playerTurn = 0;
        }
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void addPoint(int index) {
        playerlist.get(index).addPoint();
    }

    public int getPlayerPoints(int index) {
        return playerlist.get(index).getPoint();
    }

    public boolean handleCardsPicked(String firstCard, String secondCard) {
        if (firstCard.equals(secondCard)) {
            return true;
        } else {
            return false;
        }
    }

    public void handleAICardsPicked(Object card) {
        ai.checkIfRemember(card);
        ai.addToRememberList(card);
    }

    public void addCardToAI(Object card) {
        ai.addToRememberList(card);
    }

    public void removeCardFromAI(Object card) {
        ai.removeFromRememberList(card);
    }

    public Object getAIRemember() {
        return ai.getAll();
    }
    
    public String getHighscore(int index){
        return highscore.get(index).toString();
    }
    
    public int getHighscoreSize(){
        return highscore.size();
    }

    public void saveHighscore(int player) {

        System.out.println(getPlayerPoints(player));
        PrintWriter pout = null;
        highscore.add(getPlayerPoints(player));

        try {
            pout = new PrintWriter(new BufferedWriter(new FileWriter("highscore.txt")));

            for (int i = 0; i < highscore.size(); i++) {
                pout.println(highscore.get(i).toString());
            }
        } catch (IOException exception) {
            System.out.println("Error saving highscore");
        } finally {
            if (pout != null) {
                pout.close();
            }
        }
    }

    public void readHighscore() throws IOException {
        BufferedReader bin = null;

        try {
            bin = new BufferedReader(new FileReader("highscore.txt"));

            String temp = bin.readLine();

            highscore.add(Integer.parseInt(temp));

            while (temp != null) {

                temp = bin.readLine();
                if (temp != null) {
                    highscore.add(Integer.parseInt(temp));
                    if(highscore.size()>=10){
                        return;
                    }
                }
            }

        } catch (FileNotFoundException fnf) {
            System.out.println("File not found!\n");
            return;
        } finally {
            if (bin != null) {
                bin.close();
            }
        }
        sortHighscore();
    }
}
