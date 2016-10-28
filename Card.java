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
 * A card object with a character
 * 
 * @author Asad
 */
public final class Card extends StackPane {

    private final char content;
    private Rectangle rect;
    private Text text;
    private boolean hidden;

    /**
     * Constructor of card object
     * @param character 
     */
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

    /**
     * Returns the chararacter of a specific card if the hidden-flag if false, if
     * not then it returns 0
     * 
     * @return 
     */
    public char getContent() {
        if (hidden) {
            return 0;
        } else {
            return content;
        }
    }

    /**
     * Reveals the character of the card
     */
    public void show() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
        ft.setToValue(1);
//        ft.play();
        SequentialTransition seqT = new SequentialTransition(text, ft);
        seqT.play();
    }

    /**
     * Hides the character of the card
     */
    public void hide() {
        FadeTransition ft = new FadeTransition(Duration.seconds(2), text);
        ft.setToValue(0);
//        ft.play(); 
        SequentialTransition seqT = new SequentialTransition(text, ft);
        seqT.play();
    }

    /**
     * Hides the character of the card and is only called at the beginning of the program
     * to hide the card quickly
     */
    public void hideStart() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.0001), text);
        ft.setToValue(0);
        ft.play();
    }

    /**
     * Sets the hidden-flag to false
     */
    public void setHiddenFalse() {
        hidden = false;
        show();
    }

    /**
     * Sets the hidden-flag to true
     */
    public void setHiddenTrue() {
        hidden = true;
        hide();
    }

    /**
     * Returns the hidden-flag
     * @return 
     */
    public boolean getHidden() {
        return hidden;
    }

    /**
     * Overrided toString-method
     * @return 
     */
    @Override
    public String toString() {
        return String.valueOf(content);
    }
}
