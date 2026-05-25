import java.util.Scanner;
public class TicTacToe {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String board[] = new String[9];
    for (int i = 0; i < board.length; i++) {
      board[i] = String.valueOf(i + 1);
    }
    System.out.println("Welcome to 3x3 Tic Tac Toe.");
    printBoard(board);
    String currentPlayer = "X";
    int movesMade = 0;
    char winner = ' ';
    while (true) {
      movesMade++;
      int slot;
      while (true) {
        System.out.println("\nYour turn; enter a slot number to place " + currentPlayer + " in:");
        if (!sc.hasNextInt()) {
          System.out.println("Invalid input! Please enter an integer.");
          sc.next();
          continue;
        }
        slot = sc.nextInt();
        if (slot < 1 || slot > 9) {
          System.out.println("Invalid slot! Please enter between 1 and 9.");
          continue;
        }
        if (board[slot - 1].equals("X") || board[slot - 1].equals("O")) {
          System.out.println("This slot is already filled! Choose another one.");
          continue;
        }
        break;
      }
      board[slot - 1] = currentPlayer;
      printBoard(board);
      winner = checkWinner(board);
      if (winner == 'X') {
        System.out.println("\nCongratulations, you won!");
        break;
      }
      if (movesMade == 9) {
        System.out.println("\nIt's a draw!");
        break;
      }
      System.out.println("\nComputer's turn:");
      slot = getBestMove(board) + 1;
      System.out.println(slot);
      board[slot - 1] = "O";
      movesMade++;
      printBoard(board);
      winner = checkWinner(board);
      if (winner == 'O') {
        System.out.println("\nYou lose! Thanks for playing.");
        break;
      }
      if (movesMade == 9) {
        System.out.println("\nIt's a draw!");
        break;
      }
    }
    sc.close();
  }

  public static void printBoard(String board[]) {
    System.out.println("-------------");
    System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
    System.out.println("-------------");
    System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
    System.out.println("-------------");
    System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
    System.out.println("-------------");
  }

  public static char checkWinner(String board[]) {
    for (int i = 0; i < 8; i++) {
      String line = "";
      switch (i) {
        case 0: line = board[0]+board[1]+board[2]; break;
        case 1: line = board[3]+board[4]+board[5]; break;
        case 2: line = board[6]+board[7]+board[8]; break;
        case 3: line = board[0]+board[3]+board[6]; break;
        case 4: line = board[1]+board[4]+board[7]; break;
        case 5: line = board[2]+board[5]+board[8]; break;
        case 6: line = board[0]+board[4]+board[8]; break;
        case 7: line = board[2]+board[4]+board[6]; break;
      }
      if (line.equals("XXX")) return 'X';
      if (line.equals("OOO")) return 'O';
    }
    return ' ';
  }

  public static int minimax(String board[], int depth, boolean isMaximizing) {
    char result = checkWinner(board);
    if (result == 'O') return 10 - depth;
    if (result == 'X') return depth - 10;

    boolean isFull = true;
    for (int i = 0; i < 9; i++) {
      if (board[i].equals(String.valueOf(i + 1))) {
        isFull = false;
        break;
      }
    }
    if (isFull) return 0;

    if (isMaximizing) {
      int bestScore = Integer.MIN_VALUE;
      for (int i = 0; i < 9; i++) {
        if (board[i].equals(String.valueOf(i + 1))) {
          board[i] = "O";
          int score = minimax(board, depth + 1, false);
          board[i] = String.valueOf(i + 1);
          bestScore = Math.max(score, bestScore);
        }
      }
      return bestScore;
    } else {
      int bestScore = Integer.MAX_VALUE;
      for (int i = 0; i < 9; i++) {
        if (board[i].equals(String.valueOf(i + 1))) {
          board[i] = "X";
          int score = minimax(board, depth + 1, true);
          board[i] = String.valueOf(i + 1);
          bestScore = Math.min(score, bestScore);
        }
      }
      return bestScore;
    }
  }

  public static int getBestMove(String board[]) {
    int bestScore = Integer.MIN_VALUE;
    int move = -1;
    for (int i = 0; i < 9; i++) {
      if (board[i].equals(String.valueOf(i + 1))) {
        board[i] = "O";
        int score = minimax(board, 0, false);
        board[i] = String.valueOf(i + 1); 
        if (score > bestScore) {
          bestScore = score;
          move = i;
        }
      }
    }
    return move;
  }
}