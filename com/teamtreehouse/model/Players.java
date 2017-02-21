package com.teamtreehouse.model;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Players {

  public static Player[] load() {
    return new Player[] {
      new Player("Joe", "Smith", 42, true),
      new Player("Jill", "Tanner", 36, true),
      new Player("Bill", "Bon", 43, true),
      new Player("Eva", "Gordon", 45, false),
      new Player("Matt", "Gill", 40, false),
      new Player("Kimmy", "Stein", 41, false),
      new Player("Sammy", "Adams", 45, false),
      new Player("Karl", "Saygan", 42, true),
      new Player("Suzane", "Greenberg", 44, true),
      new Player("Sal", "Dali", 41, false),
      new Player("Joe", "Kavalier", 39, false),
      new Player("Ben", "Finkelstein", 44, false),
      new Player("Diego", "Soto", 41, true),
      new Player("Chloe", "Alaska", 47, false),
      new Player("Arfalseld", "Willis", 43, false),
      new Player("Phillip", "Helm", 44, true),
      new Player("Les", "Clay", 42, true),
      new Player("Herschel", "Krustofski", 45, true),
      new Player("Andrew", "Chalklerz", 42, true),
      new Player("Pasan", "Membrane", 36, true),
      new Player("Kenny", "Lovins", 35, true),
      new Player("Alena", "Sketchings", 45, false),
      new Player("Carling", "Seacharpet", 40, false),
      new Player("Joseph", "Freely", 41, false),
      new Player("Gabe", "Listmaker", 45, false),
      new Player("Jeremy", "Smith", 42, true),
      new Player("Ben", "Droid", 44, true),
      new Player("James", "Dothnette", 41, false),
      new Player("Nick", "Grande", 39, false),
      new Player("Will", "Guyam", 44, false),
      new Player("Jason", "Seaver", 41, true),
      new Player("Johnny", "Thunder", 47, false),
      new Player("Ryan", "Creedson", 43, false)
    };

  }
  public static Player choosePlayer(Player[] players){
    Arrays.sort(players);
   int choice=0;
    for(int i=0; i<players.length; i++)
        System.out.println(i+1  +"- " + players[i]); 
    try{
      BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Choose a player:  ");
      choice= Integer.parseInt(reader.readLine());
    }catch(IOException ioe) 
    {
      System.out.println("Problem with input...");
      ioe.printStackTrace();
    }
    return players[choice-1];
  }

}