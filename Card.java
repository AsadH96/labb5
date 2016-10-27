/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
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
public final class Card extends StackPane {

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
        text.setFont(Font.font(50));
        text.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);

        getChildren().addAll(rect, text);
        
        hideStart();

        hidden = true;
    }

    public char getContent() {
        if (hidden) {
            return 0;
        } else {
            return content;
        }
    }

    public void show() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
        ft.setToValue(1);
//        ft.play();
        SequentialTransition seqT=new SequentialTransition(text, ft);
        seqT.play();
    }

    public void hide() {
        FadeTransition ft = new FadeTransition(Duration.seconds(2), text);
        ft.setToValue(0);
//        ft.play(); 
        SequentialTransition seqT=new SequentialTransition(text, ft);
        seqT.play();
    }
    
    public void hideStart() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.0001), text);
        ft.setToValue(0);
        ft.play();       
    }
    
    public void setHiddenFalse(){
        hidden=false;
        show();
    }
    
    public void setHiddenTrue(){
        hidden=true;
        hide();
    }
    
    public boolean getHidden(){
        return hidden;
    }

    @Override
    public String toString() {
        return String.valueOf(content);
    }
}