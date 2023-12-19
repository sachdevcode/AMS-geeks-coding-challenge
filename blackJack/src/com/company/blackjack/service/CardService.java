package com.company.blackjack.service;

import com.company.blackjack.model.Card;

import java.util.*;

public class CardService {
    public static List<Card> cards = new ArrayList<>();
    public void generateCards(){
        List<String> suits = Arrays.asList("♠","♥","♦","♣");
        List<String> ranges = Arrays.asList("A","2","3","4","5","6","7","8","9","10","J","Q","K");

        for (String suit:suits) {
            for (String range:ranges) {
                cards.add(new Card(suit,range));
            }
        }

    }
    public void removeCard(){
        cards.remove(0);
    }

}
