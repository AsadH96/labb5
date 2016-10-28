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
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Model for a memory game.
 *
 * @author Asad
 */
public class ModelMemory extends Observable {

    private ArrayList<Card> cardlist = new ArrayList<Card>();
    private Card card;
    private Player player;
    private ArrayList<Player> playerlist = new ArrayList<Player>();
    private int playerTurn;
    private AI ai;
    private boolean playWithAI;
    private ArrayList<Integer> highscore = new ArrayList<Integer>();

    /**
     * Constructur for model
     */
    public ModelMemory() {
        ai = new AI(this); //Testing phase
        reset();
    }

    /**
     * Resets the whole game
     */
    public void reset() {
        addCards();
        shuffleCards();
        initPlayers();
        playerTurn = 0;
        playerlist.get(0).resetPoints();
        playerlist.get(1).resetPoints();

        updateView();
    }

    /**
     * Notifies the observers of eventual changes in the model
     */
    public void updateView() {
        this.setChanged();
        this.notifyObservers();
    }

    public void playWithAI() {
        playWithAI = true;
        ai = new AI(this);
    }

    /**
     * Creates cards and adds the cards to the cardlist
     */
    public void addCards() {

        cardlist.clear();

        for (char alphabet = 'A'; alphabet <= 'J'; alphabet++) {

            card = new Card(alphabet);
            cardlist.add(card);
            card = new Card(alphabet);
            cardlist.add(card);
        }
    }

    /**
     * Shuffles the cards
     */
    public void shuffleCards() {
        Collections.shuffle(cardlist);
    }

    /**
     * Sorts the highscore list in descending order
     */
    public void sortHighscore() {
        Collections.sort(highscore, Collections.reverseOrder());
    }

    /**
     * Prints the list of cards
     */
    public void printList() {
        System.out.println(cardlist.toString());
    }

    /**
     * Returns a card from a specific index in the cardlist
     *
     * @param index
     * @return
     */
    public Card getCardFromIndex(int index) {
        return cardlist.get(index);
    }

    /**
     * Returns the character of a card in a specific index
     *
     * @param index
     * @return
     */
    public char getCharacterOfCard(int index) {
        return cardlist.get(index).getContent();
    }

    /**
     * Hides a card
     *
     * @param card
     */
    public void setHiddenTrue(Object card) {
        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                cardlist.get(i).setHiddenTrue();
                //updateView();
            }
        }
    }

    /**
     * Shows a card
     *
     * @param card
     */
    public void setHiddenFalse(Object card) {

        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                cardlist.get(i).setHiddenFalse();
                //updateView();
            }
        }
    }

    /**
     * Returns the size of the cardlist
     *
     * @return
     */
    public int getNrOfCards() {
        return cardlist.size();
    }

    /**
     * Checks if a card is hidden, ie has its hidden-flag set to true
     * 
     * @param card
     * @return 
     */
    public boolean getHidden(Object card) {
        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                return cardlist.get(i).getHidden();
            }
        }
        return false;
    }

    /**
     * Creates players and adds them to the list
     */
    public void initPlayers() {
        player = new Player("Player 1");
        playerlist.add(player);
        player = new Player("Player 2");
        playerlist.add(player);
    }

    /**
     * Returns the name of a player
     *
     * @param index
     * @return
     */
    public String getPlayerName(int index) {
        return playerlist.get(index).getName();
    }

    /**
     * Switches the turn of the player
     */
    public void switchPlayerTurn() {
        if (playerTurn == 0) {
            playerTurn = 1;
        } else {
            playerTurn = 0;
        }
        updateView();
    }

    /**
     * Returns which player has the turn
     *
     * @return
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     * Adds a point to a player
     *
     * @param index
     */
    public void addPoint(int index) {
        playerlist.get(index).addPoint();
        updateView();
    }

    /**
     * Returns the points a player have
     *
     * @param index
     * @return
     */
    public int getPlayerPoints(int index) {
        return playerlist.get(index).getPoint();
    }

    /**
     * Checks if the first and second card matches
     *
     * @param firstCard
     * @param secondCard
     * @return
     */
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

    /**
     * Returns the highscore list
     *
     * @param index
     * @return
     */
    public String getHighscore(int index) {
        return highscore.get(index).toString();
    }

    /**
     * Returns the size of the highscore list
     *
     * @return
     */
    public int getHighscoreSize() {
        return highscore.size();
    }

    /**
     * Saves the highscores in a textfile
     *
     * @param player
     */
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

    /**
     * Reads the highscores from a textfile
     *
     * @throws IOException
     */
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
                    if (highscore.size() >= 10) {
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
