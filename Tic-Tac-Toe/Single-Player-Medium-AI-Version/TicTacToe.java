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
    while(true){
      movesMade++;
      int slot;
      while(true){
        if (movesMade == 1) {
          System.out.println("You will play first. Enter a slot number to place "+currentPlayer+" in:");
        }else{
          System.out.println("Your turn, Enter a slot number to place "+currentPlayer+" in:");
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
      winner = checkWinner(board);
      if (winner == 'X') {
        System.out.println("\nCongratulations, you won! Thanks for playing.");
        break;
      }
      if (movesMade == 9) {
        System.out.println("\nIt's a draw.");
        break;
      }
      System.out.println("\nComputer's turn. Computer chooses slot:");
      slot = getSmarterMove(board, "O", "X") + 1;
      System.out.println(slot);
      board[slot-1] = "O";
      movesMade++;
      printBoard(board);
      winner = checkWinner(board);
      if (winner == 'O') {
        System.out.println("\nYou lose! Thanks for playing.");
        break;
      }
      System.out.println();
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

  public static int findWinningMove(String board[], String symbol){
    for(int i = 0; i < 9; i++){
      if (board[i].equals(String.valueOf(i+1))) {
        board[i] = symbol;
        if (checkWinner(board) == symbol.charAt(0)) {
          board[i] = String.valueOf(i+1);
          return i;
        }
      board[i] = String.valueOf(i+1);  
      }
    }
    return -1;
  }

  public static int getSmarterMove(String board[], String computer, String player){
    int move = findWinningMove(board, computer);
    if (move != -1) {
      return move;
    }
    move = findWinningMove(board, player);
    if (move != -1) {
      return move;
    }
    if (board[4].equals("5")) {
      return 4;
    }
    int corners[] = {0,2,6,8};
    for (int i : corners) {
      if (board[i].equals(String.valueOf(i+1))) {
        return i;
      }
    }
    int sides[] = {1,3,5,7};
    for (int i : sides) {
      if (board[i].equals(String.valueOf(i+1))) {
        return i;
      }
    }
    return -1;
  }
}

