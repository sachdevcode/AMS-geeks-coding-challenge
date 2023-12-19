package com.company.blackjack.model;

import java.util.List;

public class Dealer {

    private Integer bet;
    private List<Card> dealerHand;

    private Integer dealerHandScore;

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(List<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public Integer getDealerHandScore() {
        return dealerHandScore;
    }

    public void setDealerHandScore(Integer dealerHandScore) {
        this.dealerHandScore = dealerHandScore;
    }

    public Dealer(Integer bet, List<Card> dealerHand, Integer dealerHandScore) {
        this.bet = bet;
        this.dealerHand = dealerHand;
        this.dealerHandScore = dealerHandScore;
    }
}
