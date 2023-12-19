package com.company.blackjack.model;

import java.util.List;

public class Card {

    private String suit;

    private String range;

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getValue(){
        if("AJQK".contains(range)){
            if(range.equalsIgnoreCase("A")){
                return 11;
            }
            return 10;
        }
        return Integer.valueOf(range);
    }

    @Override
    public String toString() {
        return suit+" "+range;
    }

    public Card(String suit, String range) {
        this.suit = suit;
        this.range = range;
    }



    public void displayCard() {
        String[] cardArt = {
                "┌───────┐",
                "│ " + range + "     │",
                "│   " + suit + "  │",
                "│     " + range + " │",
                "└───────┘"
        };

        for (String line : cardArt) {
            System.out.println(line + "\t");
        }
    }

    public static void displayPlayerCards(List<Card> cards) {
        for (Card card : cards) {
            card.displayCard();
            System.out.println();
        }
    }
}
