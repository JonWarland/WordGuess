/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.wordguess;

/**
 *
 * @author Jon
 */
import java.io.File;

public class WordGuess {

    public static void main(String[] args) throws Exception {
        boolean playAgain = true;
        File myFile = new File("google-10000-english-no-swears.txt");
        System.out.println("Running...");
        CSVReader wordReader = new CSVReader(myFile);
        GameLogic myGame = new GameLogic(wordReader);
        wordReader.unpackFile();
        while (playAgain == true)
        {
        myGame.resetGame();
        myGame.playGame();
        playAgain = myGame.replayCheck();
        }
    }
}
