/* 
READ ME:

THIS IS A TICTACTOE GAME
TAKE TURNS WITH THE A.I.
CHOOSE WHETHER YOU WANT TO BE X OR O
PLOT YOUR POINTS RELATED TO THE GRID TO OCCUPY

P.S. THIS IS MY FIRST JAVA PROJECT MADE IN VISUAL STUDIO - BLUEJ KEPT FREEZING RANDOMLY
*/

 import java.util.Scanner;
 import java.util.Random;
 
 class Game {
     private char userCharacter;
     private char botCharacter;
     private boolean gameIsRunning = true;
     private char[][] board = {
         {'0', '0', '0'},
         {'0', '0', '0'},
         {'0', '0', '0'},
     };
     
     public Game() { // set up for the game
         System.out.println("Welcome to TicTacToe!");
         
         // if the user is not X or O then try again
         
         while (userCharacter != 'X' && userCharacter != 'O') {
             System.out.println("Type your character to play as \n");
             System.out.println("X or O?");
             
             Scanner selectionInput = new Scanner(System.in);
             userCharacter = selectionInput.next().charAt(0);
             
             System.out.println("User character is: " + userCharacter);
         }
         
         // setup the bots character
         if (userCharacter == 'X') {
             botCharacter = 'O';
         } else {
             botCharacter = 'X';
         }
         
         // let the game begin
         Run();
     }
     
     void DrawBoard() {
         // draws the board based off current values
         // formatting included
         
         // loop through X/Y of board variable and print it out
         
         for (int x = 0; x < 3; x++) {
             for (int y = 0; y < 3; y++) {
                 if (y == 2) { // every time the column/row element reaches 3 - print a new line
                     System.out.print(board[x][y] + "\n");
                 } else {
                     System.out.print(board[x][y]);
                 }
             }
         }
     }
     
     boolean CheckForWinner(char user) {
         // method works by checking every side of the board to decide if the-
         //-bot or player wins..... or if it's a tie.
         
         // CHECK HORIZONTAL
         
         // go through the rows and check if the element (y) is equal to the user

         for (int x = 0; x < 3; x++) {
            if (board[x][0] == user && board[x][1] == user && board[x][2] == user) {
                return true;
            }
         }

         // CHECK VERTICAL
         
         // go through the columns and check if the element (y) is equal to the user

         for (int y = 0; y < 3; y++) {
            if (board[0][y] == user && board[1][y] == user && board[2][y] == user) {
                return true;
            }
         }
         
         // CHECK PARALLEL

         // simply check diagonally - what's more to say?

         // checking from top left to lower right
         if (board[0][0] == user && board[1][1] == user && board[2][2] == user) {
            return true;
         }

         // checking from lower left to upper right
         if (board[2][0] == user && board[1][1] == user && board[0][2] == user) {
            return true;
         }

         return false;
     }
     
     void Run() {
         System.out.println("Running");
         
         while (gameIsRunning) {
             // draw the board
             DrawBoard();

             Random random = new Random();
            
            Scanner rowInput = new Scanner(System.in);
            Scanner columnInput = new Scanner(System.in);

            System.out.println("Input row (0-2): ");
            int rowIndex = rowInput.nextInt();

            System.out.println("Input column (0-2): ");
            int columnIndex = columnInput.nextInt();
            
            // checking if the spot is unoccupied
            if (board[rowIndex][columnIndex] != '0') {
                System.out.println("skip");
                continue; 
            }
            
            // updating the board spot (check if there's a winner)

            board[rowIndex][columnIndex] = userCharacter;
            if (CheckForWinner(userCharacter)) {
                System.out.println("You win!!!!!!");
                
                gameIsRunning = false;
                DrawBoard();
            }

            // bots turn (method?) (check if theres a winner)

            // bot will randomly grab a row and column value. if it already exists, then it'll go again till it finds one that isnt occupied

            int botRowIndex;
            int botColumnIndex;

            do {
                botRowIndex = random.nextInt(3);
                botColumnIndex = random.nextInt(3);
            } while (board[botRowIndex][botColumnIndex] != '0');

            board[botRowIndex][botColumnIndex] = botCharacter;
            if (CheckForWinner(botCharacter)) {
                System.out.println("You lose!!!!!!");

                gameIsRunning = false;
                DrawBoard();
            }

             
         }
     }
 }
 
 public class Main {    
     public static void main(String[] args) {
         Game game = new Game();
     }
 }