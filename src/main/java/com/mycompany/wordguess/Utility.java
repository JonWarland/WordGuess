/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.wordguess;

/**
 *
 * @author Jon
 */
import java.util.Scanner;

public class Utility {

    private static Scanner keyboardIn = new Scanner(System.in);

    public static int randRange(int start, int end)                             //get a random number in the inclusive range from start to end.
    {
        int temp;
        if (start > end) {
            temp = start;
            start = end;
            end = temp;
        }
        return (int) Math.floor(Math.random() * (end - start + 1) + start);
    }
    public static String sanitizeInput(String input)                            //sanitises user input making it lower case and removing any non-letter characters
    {
        String temp = input;
        String tempClean = "";
        String currentCharacter;
        String legalCharacters = "abcdefghijklmnopqrstuvwxyz";
        temp = temp.toLowerCase();
        temp = temp.trim();
        
        for(int i=0;i<temp.length();i++)
        {
            if(legalCharacters.contains(""+temp.charAt(i)))
            {
                tempClean = tempClean+temp.charAt(i);
            }
        }
        return tempClean;
        
    }
    
    public static char firstLetter(String input)                                //sanitizes user input then takes the first character
    {
        String temp = sanitizeInput(input);
        while(temp.length()==0)
        {
            System.out.print("I bet you think you're really clever. Now type a letter >> "); // char doesn't like being null
            temp = sanitizeInput(keyboardIn.nextLine());
        }
        return temp.charAt(0);
    }
    
    public static String replaceCharacter(String oldString, int position,char newLetter) //removes a letter from a string at specified position and replaces it with a new letter.
    {
        if (oldString.length()>1)
        {
            return oldString.substring(0,position)+newLetter+oldString.substring(position+1,oldString.length());
        }
        else
        {
            return ""+newLetter;
        }
    }
}
