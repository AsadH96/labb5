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
public class Card {

    private char content;

    public Card(char character) {
        this.content=character;
    }
    
    @Override
    public String toString(){
        String info = "Content: " + content;
        return info;
    }
}
