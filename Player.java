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
public class Player {
    private int points;
    private String name;
    public Player(String name, int points)
    {
        this.name=name;
        this.points=points;
    }
    
    public void addPoint(){
        this.points=this.points+1;
    }
    public int getPoints(){
        return points;
    }
    
    public void resetPoints(){
        points=0;
    }
    
    public String getName(){
        return name;
    }
    
}
