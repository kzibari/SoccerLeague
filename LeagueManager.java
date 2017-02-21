import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

public class LeagueManager {
  
  //////////////////////////////////////////////////////////////////////////////////////////////
  public static void main(String[] args) {
    Map<Team, Set<Player>> teams=null;
    Player[] players = Players.load();
    
    try{
      File f = new File("teams.ser");
      if (f.isFile() && f.canRead()) {
          FileInputStream fis = new FileInputStream("teams.ser");
          ObjectInputStream ois = new ObjectInputStream(fis);
          teams = (Map<Team, Set<Player>>) ois.readObject();
          ois.close();
          fis.close();
      }
      else
        teams=new TreeMap<>();
    }catch(IOException ioe){ioe.printStackTrace();}
    catch (ClassNotFoundException cnfe){cnfe.printStackTrace();}
    
    System.out.printf("\nThere are currently %d registered players.%n", players.length);
    // Your code here!
    
    while(true){
      int option = promptForOption();
      switch (option){
        case 1: 
              createNewTeam(teams);
            break;
        case 2: addPlayerToTeam(teams, players);
            break;
        case 3: removePlayerFromTeam(teams);
            break;
        case 4: viewTeams(teams);
            break;
        case 5: displayLeagueBalanceReport(teams);
            break;
        case 6: {
                  Team t=chooseTeam(teams.keySet().toArray(new Team[teams.keySet().size()]));
                  t.viewRoster(teams.get(t));
        }
        
            break;
        case 7: {
          try{
              FileOutputStream fos = new FileOutputStream("teams.ser");
              ObjectOutputStream oos = new ObjectOutputStream(fos);
              oos.writeObject(teams);
              oos.close();
              }catch(IOException ioe){ioe.printStackTrace();}
              //catch (ClassNotFoundException cnfe){cnfe.printStackTrace();}
              System.exit(0);
        }
          
        default: System.out.println("Invalid option...");
    }
    }
    
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////////
  private static int promptForOption(){
    System.out.print("\n\nWhat do you what to do?  \n\n"+
                      "1- Create a new team\n"+
                      "2- Add a player to a team\n"+
                      "3- Remove a player from a team\n"+
                      "4- View teams\n"+
                      "5- View league balance report\n"+
                      "6- View team roster\n"+
                      "7- Exit\n\n"+
                      "Choose a number:");
    int option=0;
    try{
       BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
      option = Integer.parseInt(reader.readLine());
    }catch(IOException ioe) 
    {
      System.out.println("Problem with input...");
      ioe.printStackTrace();
    }
    return option;
    
  }
  /////////////////////////////////////////////////////////////////////////////////////////
  private static void createNewTeam(Map<Team, Set<Player>> teams){
    Player[] players = Players.load();
    int maxNoOfTeams=(players.length)/11;
    if(teams.size()<maxNoOfTeams){
    try{
      BufferedReader reader2=new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Enter a name for the team:  ");
      String name= reader2.readLine();
      System.out.print("Enter a coach's name for the team:  ");
      String coach= reader2.readLine();
      teams.put(new Team(name, coach), new HashSet());
    }catch(IOException ioe) 
    {
      System.out.println("Problem with input...");
      ioe.printStackTrace();
    }
    }
    else{
      System.out.println("There are not enough players to create a new team...");
  }
  }
  ///////////////////////////////////////////////////////////////////
  private static void viewTeams(Map<Team, Set<Player>> teams)
  {
    System.out.println("List of teams: ");
    teams.forEach((key, value)-> System.out.println(key + ", Average Height: " + key.getAverageHeight(value) ));
  }
  
 //////////////////////////////////////////////////////////////////
 
  private static Team chooseTeam(Team[] teams){
    
   int choice=0;
     
    Arrays.sort(teams);
    for(int i=0; i<teams.length; i++)
        System.out.println(i+1  +"- " + teams[i]); 
    try{
      BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Choose a team:  ");
      choice= Integer.parseInt(reader.readLine());
    }catch(IOException ioe) 
    {
      System.out.println("Problem with input...");
      ioe.printStackTrace();
    }
    return teams[choice-1];
  }
  //////////////////////////////////////////////////////////////////////////////////
  private static void addPlayerToTeam(Map<Team, Set<Player>> teams, Player[] players){
    
   
    Player p=Players.choosePlayer(players);
    if(isAvailable(teams, p)){
    Team team=chooseTeam(teams.keySet().toArray(new Team[teams.keySet().size()]));
    teams.get(team).add(p);
    System.out.println("Player: "+p.getFirstName() + " " + p.getLastName()+ " added to team: "+ team.getName());
    }
    else
       System.out.println("Player: "+p.getFirstName() + " " + p.getLastName()+ " is not available");
  }
  /////////////////////////////////////////////////////////////////////////////
  private static void removePlayerFromTeam(Map<Team, Set<Player>> teams){
    
    Team team=chooseTeam(teams.keySet().toArray(new Team[teams.keySet().size()]));
    Player player=cheesePlayerFromTeam(teams, team);
    teams.get(team).remove(player);
   
  }
  /////////////////////////////////////////////////////////////////////
  private static Player cheesePlayerFromTeam(Map<Team, Set<Player>> teams, Team team){
    System.out.println("Choose the player to remove from team "+ team.getName());
    Player[] teamPlayers=teams.get(team).toArray(new Player[teams.get(team).size()]);
    int choice=0;
    for(int i=0; i<teamPlayers.length; i++)
        System.out.println(i+1  +"- " + teamPlayers[i]); 
    try{
      BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Choose a player:  ");
      choice= Integer.parseInt(reader.readLine());
    }catch(IOException ioe) 
    {
      System.out.println("Problem with input...");
      ioe.printStackTrace();
    }
    return teamPlayers[choice-1];
  }
  //////////////////////////////////////////////////////////////////////////////////
   private static void displayLeagueBalanceReport(Map<Team, Set<Player>> teams){
    System.out.println("League Balance Report: ");
    teams.forEach((key, value)-> System.out.println(key + ", Number of experienced players: " + key.getCountOfExperiencedPlayers(value) + ", Number of inexperienced players: " + key.getCountOfInexperiencedPlayers(value) ));
  }

///////////////////////////
public static boolean isAvailable(Map<Team, Set<Player>> teams, Player p){
  for (Map.Entry<Team, Set<Player>> entry : teams.entrySet())
    if (entry.getValue().contains(p)) 
      return false;
   
   return true;
}
  public static int getAverageExperienceLevel(Map<Team, Set<Player>> teams, Team team){
    //for (Map.Entry<Team, Set<Player>> entry : teams.entrySet())
    //if (entry.getValue().contains(p)) 
    int count=0;
    Player[] teamPlayers=teams.get(team).toArray(new Player[teams.get(team).size()]);
    for(Player p: teamPlayers)
      if (p.isPreviousExperience()) count++;
    return count;
  }
  

//////////////////////////////  
 }
