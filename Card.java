/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import static javafx.scene.layout.BorderPane.setAlignment;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Asad
 */
public class Card extends StackPane {

    private final char content;
    private Rectangle rect;
    private Text text;
    private boolean hidden;

    public Card(char character) {
        this.content = character;
        rect = new Rectangle(70, 100);
        rect.setFill(Color.RED);
        rect.setStroke(Color.AQUA);

        text = new Text(String.valueOf(character));
        text.setFont(Font.font(30));
        text.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);

        getChildren().addAll(rect, text);

        hidden = true;
    }

    public char getContent() {
        if (hidden) {
            return 0;
        } else {
            return content;
        }
    }

    /*public Rectangle getRectangle(){
        return this.rect;
    }*/
    public void show() {

        //FadeTransition ft = new FadeTransition(Duration.seconds(1), text);
        //ft.setToValue(1);
        //ft.play();
    }

    public void hide( 
    Card this) {
        
        //System.out.println(card.toString() + " kortet");
        
        //FadeTransition ft = new FadeTransition(Duration.seconds(1), this.text);
        //ft.setToValue(0);
        //ft.play();
        //System.out.println("hejsan");
        //hidden = true;
    }
    
    public void setHiddenFalse(){
        hidden=false;
    }
    
    public void setHiddenTrue(){
        hidden=true;
    }

    @Override
    public String toString() {
        return String.valueOf(content);
    }
}
