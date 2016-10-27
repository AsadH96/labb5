/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Asad
 */
public class Player {
    private String name;
    private int score;
    
    public Player(String name){
        this.name=name;
        score=0;
    }
    
    public String getName(){
        return name;
    }
    
    
    public int getPoint(){
        return score;
    }
    
    public void addPoint(){
        score+=1;
    }
    
    public void resetPoints(){
        score=0;
    }
}
