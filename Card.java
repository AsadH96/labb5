/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

/**
 *
 * @author LENOVO
 */
public class Card{

    private final char content;
    private boolean changeable;

    public Card(char character) {
        this.content = character;
        changeable= true;
    }
    public void setUnchangeable(boolean stat){
        this.changeable = stat;
    }
    public boolean getChangeable(){
        return changeable;
    }

    public char getContent() {
        return content;
    }

    @Override
    public String toString() {
        String info = String.valueOf(content);
        return info;
    }

}
