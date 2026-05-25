import java.util.Scanner;
public class GuessGame {
  public static void main(String[] args){                    
    Scanner sc = new Scanner(System.in);
    System.out.println("Welcome to the Number Guessing Game!");
    while(true){
      int n = (int)(Math.random()*100)+1;
      System.out.println("Guess the number between 1 and 100...\n");
      System.out.println("You have 10 attempts to guess it!");
      boolean found = false;
      for (int i = 0; i < 10; i++) {
        System.out.println("Attempt "+(i+1)+" of 10:");
        while(!sc.hasNextInt()){
          System.out.println("Invalid input! Please enter a number:");
          sc.next();
        }
        int guess = sc.nextInt();
        if ( guess==n ) {
          found = true;
          break;
        }else if (guess > n) {
          System.out.println("Too high! Guess lower.");
        }else{
          System.out.println("Too low! Guess higher.");
        }
      }
      if (!found) {
        System.out.println("You have use all your attempts. The number was: "+n+".");
      }else{
        System.out.println("You Won!");
      }
      char choice = validChoice(sc, "Do you want to play again? (y/n):");
      if ( choice =='n' ) {
        System.out.println("\nThanks for playing.");
        break;
      }else{
        System.out.println("\nStarting a new game...\n");
      }
    }
    sc.close();
  }
  public static char validChoice(Scanner sc, String prompt){
    char ch;
    while(true){
      System.out.println(prompt);
      ch = sc.next().charAt(0);
      if (ch != 'y' && ch != 'n') {
        System.out.println("Invalid input! Please enter y or n.");
        continue;
      }
      break;
    }
    return ch;
  }                
}
