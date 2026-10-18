/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package footballmanagersimulator;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Team {
private String name;
private double budget;
private ArrayList<Player> player;
public Team(String name, double budget){
if(name==null || name.trim().isEmpty()){
throw new IllegalArgumentException("Team Name can not be nnull or empty.");        
}
this.name=name;
if(budget<0){
throw new IllegalArgumentException("Budget can not be negative.");    
}
this.budget=budget;
this.player = new ArrayList<>();
}

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }public boolean addPlayer(Player player){
    if(player==null){
    return false;    
    }
   for (Player p : this.player) {
   if (p.getId() == player.getId()) {
   return false;    
   }
   
   }
   boolean isAdded = this.player.add(player);
   return isAdded;
    }
    public int getPlayerCount(){
    return player.size();
    }public Player findPlayerById(int id){
     for (Player p : this.player) {
         if(p.getId()==id){
         return p;    
         }
     }
     return null;
    }
    public boolean removePlayer(int id){
    Player playerToRemove = findPlayerById(id);
    if (playerToRemove == null) {
    return false;    
    }
    return this.player.remove(playerToRemove);
    }public boolean signPlayer(Player playerToSign){
     if(playerToSign==null){
     return false;    
     }
    if (findPlayerById(playerToSign.getId()) != null) {
    return false;    
    }
    if (this.budget < playerToSign.getMarketValue()) {
        return false;
    }
    this.budget -= playerToSign.getMarketValue();
    boolean isAdded = this.player.add(playerToSign); 
    return isAdded;
    }
    public boolean sellPlayer(int id){
    Player playerToSell = findPlayerById(id);
    if (playerToSell == null) {
        return false;
    }
    double marketValue = playerToSell.getMarketValue();
    boolean isRemoved = removePlayer(id);
    if (!isRemoved) {
    return false;
    }
    this.budget += marketValue;
    return true;
    }
    public void displayPlayers(){
   System.out.println("Team Name: " + this.name);   
   if (this.player.isEmpty()) {
   System.out.println("No players in the team.");
   return;
   }
   System.out.println("Players in the team:");
   for(Player p : this.player ){
   System.out.println("- " + p.toString());    
   }
    }
    public double getTeamStrength(){
        double power=0;
        if(player.isEmpty()){
         return 0;
        }
    for(Player p : this.player ){
    power += p.getOverall();
    }
   power= power / player.size();
   return power;
    }

}
