package Hard;
import java.util.Arrays;
import java.util.Scanner;

public class NumberedCards {

    public static boolean isRoundWon(int[] playerCards, int[] opponentCards) {
        Arrays.sort(playerCards);
        Arrays.sort(opponentCards);
        
        int playerScore = (playerCards[4] * 10) + playerCards[3];
        int opponentScore = (opponentCards[4] * 10) + opponentCards[3];
        
        return playerScore > opponentScore;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] playerCards = new int[5];
        int[] opponentCards = new int[5];
        
        System.out.println("Enter your 5 cards (between 0 and 9): ");
        for (int i = 0; i < 5; i++) {
            playerCards[i] = scanner.nextInt();
        }
        
        System.out.println("Enter the opponent's 5 cards: ");
        for (int i = 0; i < 5; i++) {
            opponentCards[i] = scanner.nextInt();
        }

        if (isRoundWon(playerCards, opponentCards)) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose or it's a tie.");
        }

        scanner.close();
    }
}
