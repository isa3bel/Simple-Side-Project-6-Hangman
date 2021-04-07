import java.io.Console;
import java.util.Scanner;

public class Man {
  static final int MAX_INCORRECT = 6;
  int numIncorrect;
  char[] body;

  public Man() {
    // Initialize the Man object
    body = new char[] {' ', ' ', ' ', '\n', ' ', ' ', ' ', '\n', ' ', ' ', ' ', '\n'};
    numIncorrect = 0;
  }

  /**
   * Determines if the man is alive/the user can still make guesses
   * @return a boolean specifying if the man is alive or not
   */
  public boolean isAlive() {
    return numIncorrect < MAX_INCORRECT;
  }

  /**
   * Converts the array of characters to one large string
   * @return a string representing the hangman
   */
  public String toString() {
    return new String(this.body);
  }

  public void hang() {
    this.numIncorrect++; // when a user makes a wrong guess, we must add to the man and increase number of incorrect guesses
    switch(numIncorrect){
      case 1:
        body[1] = 'O';
        break;
      case 2:
        body[5] = '|';
        break;
      case 3:
        body[4] = '\\';
        break;
      case 4:
        body[6] = '/';
        break;
      case 5:
        body[8]='/';
        break;
      case 6:
        body[10] = '\\';
        break;
    }
  }

  public static void main(String[] args) {
    System.out.println("Welcome to the ASCII Version of Hangman!");
    Console c = System.console();
    char[] letters = c.readPassword("Please enter a secret word: ");
    for(int i=0; i<letters.length; i++) {
      letters[i] = Character.toUpperCase(letters[i]);
    }

    char[] puzzle = new char[letters.length];
    for(int i = 0; i < puzzle.length; i++) {
      puzzle[i] = '_';
    }

    Man m = new Man();
    Scanner s = new Scanner(System.in);

    while(m.isAlive()) {
      boolean underscore = false;
      // show the user how many letters they've guessed
      for (int i = 0; i < puzzle.length; i++) {
        System.out.print(puzzle[i] + " ");
      }
      System.out.println();

      char choice = s.nextLine().toUpperCase().charAt(0);
      System.out.println("Please guess a letter: ");

      // get the user's guess and determine if it's the in the secret word
      boolean guessedCorrectly = false;
      for (int i = 0; i < letters.length; i++) {
        if (letters[i] == choice) {
          guessedCorrectly = true;
          for (int j = 0; j < letters.length; j++) {
            if (letters[j] == choice) {
              puzzle[j] = choice;
            }
          }
        }
      }

      if (!guessedCorrectly) {
        m.hang();
      }
      // Print current man to the user
      System.out.print(m);

      // determine if all the letters have been guessed
      for (int k = 0; k < puzzle.length; k++) {
        if (puzzle[k] == '_') {
          underscore = true;
        }
      }

      if (!underscore) {
        break;
      }
    }

    // End of game
    if (m.isAlive()) {
      System.out.println("You win!");
    } else {
      System.out.println("You lose");
    }
  }
}
