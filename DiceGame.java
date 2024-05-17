import java.util.Scanner;
import java.util.Random;
public class DiceGame {
    static Random random = new Random();
    public static int rollDice() {
        return random.nextInt(6) + 1;
    }
    public static void playRound(int roundNum, int[] playerScores) {
        int[] diceResults = {rollDice(), rollDice(), rollDice()};
        int sameCount = 0;
        for (int i = 0; i < diceResults.length; i++) {
            for (int j = i + 1; j < diceResults.length; j++) {
                if (diceResults[i] == diceResults[j]) {
                    sameCount++;
                }
            }
        }
        if (sameCount == 0) {
            for (int i = 0; i < playerScores.length; i++) {
                playerScores[i] += diceResults[i];
            }
        } else if (sameCount == 1) {
            int bonusNumber = 
              (diceResults[0] == diceResults[1] && diceResults[0] > diceResults[2]) ? diceResults[0] 
            : (diceResults[0] == diceResults[1] && diceResults[0] < diceResults[2]) ? 0 
            : (diceResults[0] == diceResults[2] && diceResults[0] > diceResults[1]) ? diceResults[0] 
            : (diceResults[0] == diceResults[2] && diceResults[0] < diceResults[1]) ? 0 
            : (diceResults[1] == diceResults[2] && diceResults[1] > diceResults[0]) ? diceResults[1] 
            : (diceResults[1] == diceResults[2] && diceResults[1] < diceResults[0]) ? 0 
            : diceResults[2];
            for (int i = 0; i < playerScores.length; i++) {
                if (diceResults[i] == bonusNumber) {
                    playerScores[i] += 2 * bonusNumber;
                } else {
                    playerScores[i] += diceResults[i];
                }
            }
        } else {
            for (int i = 0; i < playerScores.length; i++) {
                playerScores[i] += diceResults[0];
            }
        }
        System.out.println("\nRound " + roundNum);
        System.out.println("Dice 1: " + diceResults[0] + "\tDice 2: " + diceResults[1] + "\tDice 3: " + diceResults[2]);
        System.out.println("Total Points:");
        System.out.println("Dice 1: " + playerScores[0] + "\tDice 2: " + playerScores[1] + "\tDice 3: " + playerScores[2]);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int targetRounds;
        while (true) {
            System.out.print("Enter the target number of rounds (1-99): ");
            try {
                targetRounds = scanner.nextInt();
                if (targetRounds >= 1 && targetRounds <= 99) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 99.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 99.");
                scanner.next();
            }
        }
        int[] playerScores = {0, 0, 0};
        for (int roundNum = 1; roundNum <= targetRounds; roundNum++) {
            playRound(roundNum, playerScores);
        }
        scanner.close();
    }
}

