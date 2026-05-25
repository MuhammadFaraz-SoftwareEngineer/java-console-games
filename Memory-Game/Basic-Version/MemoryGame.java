// Basic version built using primitive arrays and manual shuffle.
import java.util.Random;
import java.util.Scanner;
public class MemoryGame{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    char cards[] = {'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D'};
    Random rand = new Random();
    for (int i = cards.length-1; i >= 0; i--) {
         int randomIndex = rand.nextInt(i+1);
         char temp = cards[i];
         cards[i] = cards[randomIndex];
         cards[randomIndex] = temp;
    }
    char board[] = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    int matchPair = 0, attempt = 0, maxAttempts = 8;
    System.out.println("Welcome to the Memory Game!");
    System.out.println("You have "+maxAttempts+" attempts.");
    while(matchPair < 4 && attempt < maxAttempts){
      attempt++;
      System.out.println("Attempt "+attempt+" of "+maxAttempts);
      printBoard(board);
      System.out.println();
      int first = validIndex(sc, board, cards.length, "Enter index of first card to flip:");
      board[first] = cards[first];
      printBoard(board);
      System.out.println();
      int second;
      while(true){
        second = validIndex(sc, board, cards.length, "Enter index of second card to flip:");
        if (second == first) {
            System.out.println("You can't pick the same card twice. Choose a different index.");
            continue;
        }
        break;
      }
      board[second] = cards[second];
      printBoard(board);
      System.out.println();
      if (cards[first] == cards[second]) {
          matchPair++;
          System.out.println("You found a pair!");
      }else{
          System.out.println("Sorry, those cards don't match.");
          board[first] = ' ';
          board[second] = ' ';
      }
    }
    if (matchPair == 4) {
      System.out.println("Congratulations, you won in "+attempt+" tries!");
    }else{
      System.out.println("You have used all your attempts. Game over.");
    }
  }
  public static int validIndex(Scanner sc, char board[], int length, String prompt){
    int index;
    while(true){
      System.out.println(prompt);
      if (!sc.hasNextInt()) {
        System.out.println("Invalid input! Please enter an integer.");
        sc.next();
        continue;
      }
      index = sc.nextInt();
      if (index < 0 || index > length-1) {
        System.out.println("Invalid index! Please enter a number between 0 and "+(length-1)+".");
        continue;
      }
      if (board[index] != ' ') {
        System.out.println("That card is already revealed. Pick another index.");
        continue;
      }
      break;
    }
    return index;
  }
  public static void printBoard(char board[]){
    for (int i = 0; i < board.length; i++) {
      System.out.print("  "+i+" ");
    }
    System.out.println();
    System.out.print("| ");
    for (char c : board) {
      System.out.print(c+" | ");
    }
  }
}

