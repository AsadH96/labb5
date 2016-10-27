/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MemoryModel {

    private ArrayList<Card> cardlist;
    private ArrayList<Player> players;
    private Card card;
    private Player player;
    private static int playerturn;
    private AI aI;

    public MemoryModel() {

        aI = new AI("Terminator", this, true);
        cardlist = new ArrayList<Card>();
        players = new ArrayList<Player>();
        playerturn = 0;
        createPlayers();
        addCards();
        shuffleCard();
    }

    public int getPlayerTurn() {
        return playerturn;
    }

    public void switchPlayerTurn() {
        if (playerturn == 0) {
            playerturn = 1;
            aIPick();
        } else {
            playerturn = 0;
        }
    }

    public void createPlayers() {
        player = new Player("Jake", 0);
        players.add(player);

        player = new Player("Hope", 0);
        players.add(player);
    }

    public void resetGame() {
        addCards();
        shuffleCard();
        resetPlayerPoints();

    }

    public void shuffleCard() {
        Collections.shuffle(cardlist);

    }

    public Card getCard(int index) {
        return cardlist.get(index);
    }

    public void addCards() {
        cardlist.clear();
        for (char alphabet = 'A'; alphabet <= 'J'; alphabet++) {
            card = new Card(alphabet);
            cardlist.add(card);
            card = new Card(alphabet);
            cardlist.add(card);
        }
    }

    public int getNrOfCards() {
        return cardlist.size();
    }

    public void showCard(Object card) {
        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                cardlist.get(i).showCard();
                cardlist.get(i).setUnchangeable(false);
                //aI.rememberCard(card);
            }
        }
    }

    public void hideCard(Object card) {
        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                cardlist.get(i).hideCard();
                cardlist.get(i).setUnchangeable(true);
            }
        }
    }

    public void setUnchangeable(Object card, boolean stat) {
        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                cardlist.get(i).setUnchangeable(stat);
            }
        }
    }

    public boolean getChangeable(Object card) {
        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                return cardlist.get(i).getChangeable();
            }
        }
        return true;
    }

    public void addPoint(int index) {
        players.get(index).addPoint();
    }

    public boolean compareCards(Object first, Object second) {
        if (first.toString().contains(second.toString())) {
            setUnchangeable(first, false);
            setUnchangeable(second, false);
            return true;
        }
        return false;
    }

    public String getPlayerName(int index) {
        return players.get(index).getName();
    }

    public int getPlayerPoints(int index) {
        return players.get(index).getPoints();
    }

    public void resetPlayerPoints() {
        players.get(0).resetPoints();
        players.get(1).resetPoints();
    }

    public String getPlayer(int index) {
        return "Name: " + getPlayerName(index) + "\nPoints: " + getPlayerPoints(index);
    }

    public String showRules() {
        return "How to play the game Memory:\n\n"
                + "Pick one card from the board and the card will flip and reveal "
                + "the content of the card. After you've picked a card, pick another card. "
                + "Your goal is to find the matching pair of the card you first picked up.\n\n"
                + "If you didn't find the matching pair, it's the other player's turn to try and "
                + "find a pair.\n\nYou need to keep your attention on the board all the time and "
                + "try to remember the position of the cards that's been revealed. The player with "
                + "the highest score wins the game.\n\nGood luck and have fun!";
    }

    public void RememberCardAi(Object card) {
        for (int i = 0; i < cardlist.size(); i++) {
            if (cardlist.get(i).equals(card)) {
                aI.rememberCard(card);
            }
        }
    }

    public void aIPick() {
        if (aI.sameCards()) {
            aI.showSameCards();
        } else {
            //aI.pickCard();
            Random generator = new Random();
            int i = generator.nextInt(20);
            showCard(cardlist.get(i));
            System.out.println("He picks "+cardlist.get(i));
            if (!aI.compareCardList(cardlist.get(i))) {
                Random gene = new Random();
               int g = gene.nextInt(20);
                showCard(cardlist.get(g));
                System.out.println("Now he picks: "+cardlist.get(g));
                if(!compareCards(cardlist.get(i),cardlist.get(g))){
                    hideCard(cardlist.get(i));
                    hideCard(cardlist.get(g));
                }
                    
            }

        }
    }

    public void rememberCard(Object card) {
        aI.rememberCard(card);
    }
}
