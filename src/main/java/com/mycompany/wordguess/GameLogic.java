/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.wordguess;
import java.util.Scanner;

/**
 *
 * @author Jon
 */
public class GameLogic {
    //---------Attributes---------
    private CSVReader wordList;                                                 //this pulls the wordlist from the CSVReader class
    private String trueWord;                                                    // the actual word in plaintext
    private String blankWord;                                                   // the word displayed to the player as blanks and letters -t--a---
    private char currentGuess;                                                  // the most recent letter the player has guessed
    private String guessedLetters;                                              // list of letters the player has already guessed to avoid repeats
    private int strikes;                                                        // number of letters the player has guessed wrong
    private boolean gameOver;                                                   // is the game over, if so stop asking for guesses
    private Scanner userIn = new Scanner(System.in);                            // get input from the user
    
    //--------constructor----------
    GameLogic(CSVReader wordList)
    {
        this.wordList = wordList;
    }
    public void resetGame()                                                     // setting all attributes back to default values for the player to start a fresh game
    {
        this.trueWord = wordList.randomWord();
        this.blankWord = this.blankConstructor(trueWord);
        this.currentGuess = 0;
        this.strikes = 0;
        this.guessedLetters = "";
        this.gameOver = false;
    }
    
    //---------Getters & Setters---------

    public boolean isGameOver() {
        return gameOver;
    }
  
    
    //---------Methods---------
    public String blankConstructor(String trueWord)                             // create a blank ----- of the correct length for the selected word
    {
        String temp1 = "";
        for(int i=0;i<trueWord.length();i++)
        {
            temp1 = temp1+"-";
        }
        return temp1;
    }
    public void makeGuess()
    {
        boolean correctGuess = false;
        System.out.print("Guess a letter >> ");
        this.currentGuess = Utility.firstLetter(userIn.nextLine());
        while(this.guessedLetters.contains(""+currentGuess))                    //keep asking the player for input until they guess a unique letter
        {
            System.out.print("You already guessed that! Guess a different letter >> ");
            this.currentGuess = Utility.firstLetter(userIn.nextLine());
        }
        for(int i=0;i<trueWord.length();i++)                                    // loop through the actual word to see where our guessed letter fits in
        {
            if (trueWord.charAt(i) == currentGuess)
            {
                this.blankWord = Utility.replaceCharacter(this.blankWord, i, currentGuess); //replace the blanks with the correct letter in the correct places ---a---
                correctGuess = true;
            }
        }
        if(correctGuess == false)                                               //if the player guessed a letter not in the word, increment their strike integer
        {
            this.strikes = this.strikes+1;
        }
        this.guessedLetters = this.guessedLetters+" "+this.currentGuess;        //add the guessed letter to the list of previous guesses
    }
    
    public void gameState()                                                     //check win conditions
    {
        System.out.println("Your current guess is: "+this.blankWord);
        System.out.println("You have already guessed: "+this.guessedLetters);
        System.out.println("You have had "+this.strikes+"/10 strikes");
        if(strikes >=10)                                                        //check failure condition
        {
            System.out.println("You lose! The word was "+this.trueWord);
            this.gameOver = true;
        }
        else if(this.trueWord.equals(this.blankWord))                           //check win condition
        {
            System.out.println("You win! The word was "+this.trueWord);
            this.gameOver = true;
        }
    }
    public void playGame()                                                      //this function is called to start the game.
    {
        System.out.println("Welcome to Hangman.");
        System.out.println("If you guess wrong 10 times you lose");
        System.out.println("Your word is "+this.blankWord);
        while(this.gameOver == false)                                           //loop guessing and checking win condition until the player wins or loses
        {
            this.makeGuess();
            this.gameState();
        }   
    }
    public boolean replayCheck()                                                //check to see if the player wants to win or lose after the game ends, is called in main method
    {
        boolean answered = false;
        while (answered == false)
        {
        System.out.print("Would you like to play again? Y/N >> ");
        char userReply = Utility.firstLetter(this.userIn.nextLine());
        if(userReply == 'y')
            {
                answered = true;
                return true;
            }
        else if(userReply == 'n')
            {
                    answered = true;
                    return false;
            }
        }
        return false;
    }
}
