import com.company.blackjack.ui.BlackjackUI;

import java.util.Scanner;

public class Main {

public static void main(String[] args) {
        boolean tryAgain = true;
        Scanner scanner = new Scanner(System.in);

        while(tryAgain){
            BlackjackUI blackjackUI = new BlackjackUI();
            blackjackUI.startGame();
            System.out.print("Press T to Try Again And Q to Quit : ");
            String again = scanner.nextLine();
            tryAgain = again.equalsIgnoreCase("T");
        }

    }
}