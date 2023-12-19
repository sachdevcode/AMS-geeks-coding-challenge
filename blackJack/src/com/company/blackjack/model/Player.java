package com.company.blackjack.model;

import java.util.List;

public class Player {

    private String playerNumber;
    private Integer bet;
    private Boolean isPlayerOutOfRound;
    private List<Card> cards;

    private Integer handScore;

    public Player(String playerNumber, Integer bet) {
        this.playerNumber = playerNumber;
        this.bet = bet;
        this.isPlayerOutOfRound = false;
    }

    public Integer getHandScore() {
        return handScore;
    }

    public void setHandScore(Integer handScore) {
        this.handScore = handScore;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public Boolean getPlayerOutOfRound() {
        return isPlayerOutOfRound;
    }

    public void setPlayerOutOfRound(Boolean playerOutOfRound) {
        isPlayerOutOfRound = playerOutOfRound;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isBusted(){
        return this.isPlayerOutOfRound;
    }
}
