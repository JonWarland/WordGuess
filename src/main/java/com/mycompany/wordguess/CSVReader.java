/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.wordguess;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jon
 */
public class CSVReader {

    //class variables
    private File filePath;
    private ArrayList<String> words = new ArrayList<String>();
    

    //class constructor
    public CSVReader(File filePath) {                                           //specify the path of the .txt file
        this.filePath = filePath;
    }
    //getters & setters
    public String getWord(int position)
    {
        return words.get(position);
    }
    
    public void unpackFile() throws Exception {                                 //put all words from the .txt into an array for easier searching
        Scanner fileReader = new Scanner(this.filePath);
        System.out.println("Converting CSV to array.");
        while(fileReader.hasNext())
        {
        words.add(fileReader.next());
        }
        fileReader.close();
    }
    public String randomWord()
    {
        return words.get(Utility.randRange(0, words.size()-1));
    }
}
