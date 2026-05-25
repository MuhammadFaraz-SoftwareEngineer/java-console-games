import java.util.Scanner;
public class TicTacToe {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String board[] = new String[9];
    for (int i = 0; i < board.length; i++) {
      board[i] = String.valueOf(i+1);
    } 
    System.out.println("Welcome to 3x3 Tic Tac Toe.");
    printBoard(board);
    System.out.println();
    String currentPlayer = "X";
    int movesMade = 0;
    char winner = ' ';
    while(movesMade < 9){
      movesMade++;
      int slot;
      while(true){
        if (movesMade == 1) {
          System.out.println(currentPlayer+" will play first. Enter a slot number to place "+currentPlayer+" in:");
        }else{
          System.out.println(currentPlayer+"'s turn; enter a slot number to place "+currentPlayer+" in:");
        }
        if(!sc.hasNextInt()){
          System.out.println("Invalid input! Please enter an integer.");
          sc.next();
          continue;
        }
        slot = sc.nextInt();
        if (slot < 1 || slot > 9) {
          System.out.println("Invalid slot! Please enter a slot number between 1 and 9.");
          continue;
        }
        if (board[slot - 1].equals("X") || board[slot - 1].equals("O")) {
          System.out.println("This slot is already filled! Choose another one.");
          continue;
        }
        break;
      }
      board[slot-1] = currentPlayer;
      printBoard(board);
      System.out.println();
      winner = checkWinner(board);
      if (winner == 'X') {
        System.out.println("Congratulations! 'X' has won! Thanks for playing.");
        break;
      }else if (winner == 'O') {
        System.out.println("Congratulations! 'O' has won! Thanks for playing.");
        break;
      }
      if (currentPlayer.equals("X")) {
          currentPlayer = "O";
      }else{
          currentPlayer = "X";
      }
    }
    if (movesMade == 9 && winner == ' ') {
      System.out.println("It's a draw.");
    }
    sc.close();
  }

  public static void printBoard(String board[]){
    System.out.println("-------------");
    System.out.println("| "+board[0]+" | "+board[1]+" | "+board[2]+" |");
    System.out.println("-------------");
    System.out.println("| "+board[3]+" | "+board[4]+" | "+board[5]+" |");
    System.out.println("-------------");
    System.out.println("| "+board[6]+" | "+board[7]+" | "+board[8]+" |");
    System.out.println("-------------");
  }

  public static char checkWinner(String board[]){
    for (int i = 0; i < 8; i++) {
      String line = null;
      switch (i) {
        case 0:
          line = board[0]+board[1]+board[2];
          break;
        case 1:
          line = board[3]+board[4]+board[5]; 
          break;
        case 2:
          line = board[6]+board[7]+board[8]; 
          break;
        case 3:
          line = board[0]+board[3]+board[6]; 
          break;
        case 4:
          line = board[1]+board[4]+board[7]; 
          break;
        case 5:
          line = board[2]+board[5]+board[8]; 
          break;
        case 6:
          line = board[0]+board[4]+board[8]; 
          break;          
        case 7:
          line = board[2]+board[4]+board[6]; 
          break;  
      }
      if (line.equals("XXX")) {
        return 'X';
      }else if (line.equals("OOO")) {
        return 'O';
      }
    }
    return ' ';
  }
}