import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
public class MemoryGame{
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> cards = new ArrayList<>();
    cards.add("A"); cards.add("A");
    cards.add("B"); cards.add("B");
    cards.add("C"); cards.add("C");
    cards.add("D"); cards.add("D");
    Collections.shuffle(cards);
    String board[] = new String[cards.size()];
    Arrays.fill(board, " ");
    boolean flip[] = new boolean[cards.size()];
    int matchPair = 0, attempt = 0, maxAttempts = 8;
    System.out.println("Welcome to the Memory Game!");
    System.out.println("You have "+maxAttempts+" attempts.");
    while(matchPair < 4 && attempt < maxAttempts){
      attempt++;
      System.out.println("Attempt "+attempt+" of "+maxAttempts+":");
      printBoard(board);
      System.out.println();
      int first = validIndex(sc, board, flip, "Enter index of first card to flip:");
      board[first] = cards.get(first);
      flip[first] = true;
      printBoard(board);
      System.out.println();
      int second;
      while(true){
      second = validIndex(sc, board, flip, "Enter index of second card to flip:");
      if(second == first){
        System.out.println("You can't pick the same card twice. Choose a different index.");
        continue;
      }
      break;
      }
      board[second] = cards.get(second);
      flip[second] = true; 
      printBoard(board);
      System.out.println();
      if (cards.get(first).equals(cards.get(second))) {
        matchPair++;
        System.out.println("You found a pair!");
      }else{
        System.out.println("Sorry those cards don't match.");
        board[first] = " ";
        flip[first] = false;
        board[second] = " ";
        flip[second] = false;
      }
    }
    if (matchPair == 4) {
      System.out.println("Congratulations, you won in "+attempt+" tries!");
    }else{
      System.out.println("You have used all your attempts. Game over.");
    }
    sc.close();
  }
  public static int validIndex(Scanner sc, String board[], boolean flip[], String prompt){
    int index;
    while(true){
      System.out.println(prompt);
      if (!sc.hasNextInt()) {
        System.out.println("Invalid input! Please enter an integer.");
        sc.next();
        continue;
      }
      index = sc.nextInt();
      if (index < 0 || index > board.length-1) {
        System.out.println("Invalid index! Please enter number between 0 and "+(board.length-1)+".");
        continue;
      }
      if (flip[index]) {
        System.out.println("That card is already revealed. Pick another index.");
        continue;
      }
      break;
    }
    return index;
  }
  public static void printBoard(String board[]){
    for (int i = 0; i < board.length; i++) {
      System.out.print("  "+i+" ");
    }
    System.out.println();
    System.out.print("| ");
    for (String string : board) {
      System.out.print(string+" | ");
    }
  }
}