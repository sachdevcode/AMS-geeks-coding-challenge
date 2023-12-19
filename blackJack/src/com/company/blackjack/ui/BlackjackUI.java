package com.company.blackjack.ui;

import com.company.blackjack.config.ConsoleColors;
import com.company.blackjack.service.CardService;
import com.company.blackjack.service.PlayerService;

import java.util.Scanner;

public class BlackjackUI {

    CardService cardService;
    PlayerService playerService;
    Scanner scanner;

    public BlackjackUI(){
        this.cardService = new CardService();
        this.playerService = new PlayerService();
        this.scanner = new Scanner(System.in);
    }

    public void startGame(){
        cardService.generateCards();
        displayWelcomeMessage();
    }

    public Integer getNumberOfPlayers(Scanner scanner){
        System.out.print("How many players in the round: ");
        Integer players = scanner.nextInt();
        return players;
    }
    public void displayWelcomeMessage() {
        System.out.println("Welcome to " + ConsoleColors.GREEN_BOLD + "Blackjack!" + ConsoleColors.RESET);

        Boolean isNumberOfPlayerValid = false;
        while(!isNumberOfPlayerValid){
            try {
                int numberOfPlayers = getNumberOfPlayers(scanner);
                playerService.isNoOfPlayersValid(numberOfPlayers);
                isNumberOfPlayerValid = true;
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED_BOLD + e.getMessage() + ConsoleColors.RESET);
            }
        }


        waitForStartInput();
        playerService.dealInitialCards();
        playerService.displayResult();
    }

    private void waitForStartInput(){
        String startGame = "E";
        while (!startGame.equalsIgnoreCase("S")){
            System.out.print("Press " + ConsoleColors.GREEN_BOLD + "S" + ConsoleColors.RESET + " to Start: ");
            startGame = scanner.next();
        }
    }

}
