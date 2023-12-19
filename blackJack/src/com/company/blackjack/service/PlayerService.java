package com.company.blackjack.service;

import com.company.blackjack.config.ConsoleColors;
import com.company.blackjack.model.Card;
import com.company.blackjack.model.Dealer;
import com.company.blackjack.model.Player;

import java.util.*;

public class PlayerService {

    private static List<Player> playerInRound  = new ArrayList<>();
    CardService cardService = new CardService();
    private static final int MAX_PLAYERS = 7;
    private static final int MIN_PLAYERS = 2;
    private Dealer dealer;
    private static final String DEALER = "Dealer";
    private static final Scanner scanner = new Scanner(System.in);

    public void isNoOfPlayersValid(Integer numberOfPlayers) throws Exception {
        if (numberOfPlayers < MIN_PLAYERS || numberOfPlayers > MAX_PLAYERS) {
            throw new IllegalArgumentException("Number of players must be between 2 and 7.");
        }

        createPlayers(numberOfPlayers);
    }

    public void createPlayers(Integer no){
        dashes();
        for (int i = 0; i < no; i++) {
            System.out.print("Enter the bet amount of Player ".concat(String.valueOf(i+1))+" : ");
            Integer amount = scanner.nextInt();
            playerInRound.add(new Player("Player ".concat(String.valueOf(i+1)), amount));
        }
        dashes();
        playerInRound.add(new Player("Dealer",0));
    }

    public void dealInitialCards(){
        Collections.shuffle(CardService.cards);
        for (Player player:playerInRound) {
            List<Card> playerCard = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                playerCard.add(CardService.cards.get(0));
                cardService.removeCard();
            }
            player.setCards(playerCard);
            displayPlayerHands(player);
        }

    }

    public void displayPlayerHands(Player player) {
        int handSum = calculateHandSum(player);
        Card.displayPlayerCards(player.getCards());

        adjustForAces(player, handSum);
        player.setHandScore(handSum);

        System.out.println(ConsoleColors.GREEN + player.getPlayerNumber() + " in hand score : " + handSum + ConsoleColors.RESET);

        handleDealerScenario(player, handSum);
        checkAndHandleBusted(player);
        handlePlayerInput(player);
    }

    private int calculateHandSum(Player player) {
        int handSum = 0;
        int noOfAces = 0;
        dashes();
        System.out.println(ConsoleColors.GREEN_BOLD + player.getPlayerNumber() + ConsoleColors.RESET);
        for (Card card : player.getCards()) {
            handSum += card.getValue();
            if (card.getRange().equalsIgnoreCase("A")) {
                noOfAces++;
            }
        }
        return handSum;
    }

    private void adjustForAces(Player player, int handSum) {
        int noOfAces = countAces(player);
        while (handSum > 21 && noOfAces > 0) {
            handSum -= 10;
            noOfAces--;
        }
    }

    private int countAces(Player player) {
        int noOfAces = 0;
        for (Card card : player.getCards()) {
            if (card.getRange().equalsIgnoreCase("A")) {
                noOfAces++;
            }
        }
        return noOfAces;
    }


    private void handleDealerScenario(Player player, int handSum) {
        if (player.getPlayerNumber().equalsIgnoreCase(DEALER)) {
            dealer = new Dealer(0, player.getCards(), handSum);
        }
    }

    private void checkAndHandleBusted(Player player) {
        checkBusted(player);
        if (player.isBusted()) {
            System.out.println(ConsoleColors.RED_BOLD + player.getPlayerNumber() + " is Busted " + ConsoleColors.RESET);
        }
    }

    private void handlePlayerInput(Player player) {
        if (!player.getPlayerOutOfRound()) {
            String isHitOrStand = promptForHitOrStand();
            handleHitAndStay(player, isHitOrStand);
        }
    }

    private String promptForHitOrStand() {
        String isHitOrStand = "N";
        while (!isHitOrStand.equalsIgnoreCase("H") && !isHitOrStand.equalsIgnoreCase("S")) {
            System.out.println("Hit or Stand (H | S) : ");
            isHitOrStand = scanner.nextLine();
        }
        return isHitOrStand;
    }


    public void handleHitAndStay(Player player,String isHitOrStand){
        if (isHitOrStand.equalsIgnoreCase("H")) {
            giveCardToPlayer(player);
        }
        while(player.getHandScore() <= 16) {
             if (isHitOrStand.equalsIgnoreCase("S")) {
                displayPlayerHands(player);
            }
        }
    }

    public void giveCardToPlayer(Player player){
        player.getCards().add(CardService.cards.get(0));
        cardService.removeCard();
        displayPlayerHands(player);
    }


    public void checkBusted(Player player){
        if(player.getHandScore() > 21){
            player.setPlayerOutOfRound(true);
        }
    }


    public void displayResult(){
        dashes();
        System.out.println("Dealer's final hand: " + dealer.getDealerHand() + " (Score: " + dealer.getDealerHandScore() + ")");
        playerInRound.stream().forEach(player -> {
            if(!player.getPlayerNumber().equalsIgnoreCase("Dealer")) {
                dashes();
                System.out.println(ConsoleColors.BLACK+player.getPlayerNumber()+ConsoleColors.RESET);
                System.out.println("Your final hand: " + player.getCards() + " (Score: " + player.getHandScore() + ")");
                printDisplayResults(player);
            }
            dashes();
        });
    }

    private void printDisplayResults(Player player) {
        if (player.getHandScore() > 21) {
            System.out.println(ConsoleColors.RED+"Unfortunately, you exceeded 21 and busted. Your initial bet of "+ player.getBet()+" units has been lost."+ConsoleColors.RESET);
        } else if (dealer.getDealerHandScore() > 21) {
            System.out.println(ConsoleColors.RED+"Dealer busted! "+ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BOLD+"Congratulations! You win twice your initial bet of "+ player.getBet()+" units. Your total winnings now stand at "+ player.getBet()*2+" units."+ConsoleColors.RESET);
        } else if (player.getHandScore() > dealer.getDealerHandScore()) {
        System.out.println(ConsoleColors.GREEN_BOLD +"Congratulations! You've won. Your initial bet of "+ player.getBet()+" units has doubled to "+ player.getBet()*2+" units."+ConsoleColors.RESET);
        } else if (player.getHandScore() == dealer.getDealerHandScore()) {
            System.out.println(ConsoleColors.GREEN+ "It's a push. Your initial bet of "+ player.getBet()+" units has been returned, so your total remains "+ player.getBet()+" units." +ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED_BOLD+"Dealer wins. Your bet goes to the dealer."+ConsoleColors.RESET);
        }
    }

    public static void dashes(){
        System.out.println("--------------------------------------------------------------------");
    }
}
