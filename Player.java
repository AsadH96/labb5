/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * A player object with a name and a score
 * 
 * @author Asad
 */
public class Player {

    private String name;
    private int score;

    /**
     * Constructor of the player object
     * 
     * @param name 
     */
    public Player(String name) {
        this.name = name;
        score = 0;
    }

    /**
     * Returns the name of the player
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the amount of points the player has
     * 
     * @return 
     */
    public int getPoint() {
        return score;
    }

    /**
     * Adds one point to the player
     */
    public void addPoint() {
        score += 1;
    }

    /**
     * Resets the point of a player to 0
     */
    public void resetPoints() {
        score = 0;
    }
}
