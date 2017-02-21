package com.teamtreehouse.model;
import java.util.Set;
import java.io.Serializable;

public class Team implements Comparable<Team>, Serializable{
  private String mName;
  private String mCoach;
  private static final long serialVersionUID = 1L;
  public Team(String name, String coach){
   mName= name;
    mCoach=coach;
  }
  public String getName(){
    return mName;
  }
  public String getCoach(){
    return mCoach;
  }
  
  public double getAverageHeight(Set<Player> s){
    double ave=0;
    for(Player p : s)
      ave+=p.getHeightInInches();
    return (ave/s.size());
  }
  public int getCountOfExperiencedPlayers(Set<Player> s){
    int c=0;
    for(Player p : s)
      if (p.isPreviousExperience()) c++;
    return c;
  }
  public int getCountOfInexperiencedPlayers(Set<Player> s){
    return s.size()-getCountOfExperiencedPlayers(s);
  }
  public void viewRoster(Set<Player> s){
    System.out.println("Team: " + mName+ " has " + getCountOfExperiencedPlayers(s) + " experienced players.");
    for(Player p:s)
      System.out.println(p.getFirstName() + " " + p.getLastName() + " " + p.getHeightInInches() + " " + p.isPreviousExperience());
  }
  
  @Override
  public String toString(){
   return "Team  " + mName + " coached by "+ mCoach; 
  }
  @Override
  public int compareTo(Team other) {
    
    return mName.compareTo(other.mName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Team)) return false;

    Team team = (Team) o;

    if (!mName.equals(team.mName)) 
      return false;
    if (!mCoach.equals(team.mCoach)) 
      return false;
    return true;
  }
  @Override
  public int hashCode() {
    
    return (mName+mCoach).hashCode();
  }
}