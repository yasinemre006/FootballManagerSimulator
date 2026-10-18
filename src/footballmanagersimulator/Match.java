/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package footballmanagersimulator;

import java.util.Random;

/**
 *
 * @author Lenovo
 */
public class Match {
private Team homeTeam;
private Team awayTeam;
private int homeScore;
private int awayScore;
public Match(Team homeTeam,Team awayTeam){
if(homeTeam==null || awayTeam ==null){
throw new IllegalArgumentException("Teams can not be null.");
}if(homeTeam==awayTeam){
throw new IllegalArgumentException("The same team cannot play a match against itself.");    

}if (homeScore < 0 || awayScore < 0) {
 throw new IllegalArgumentException("Scores cannot be negative.");   
}
this.homeTeam = homeTeam;
this.awayTeam = awayTeam;
this.homeScore = 0;
this.awayScore = 0;

}

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }
public double calculateTeamStrength(Team team){
 if(team==null){
 throw new IllegalArgumentException("Teams can not be null.");    
 }
 return team.getTeamStrength();
}public void playMatch(){
double homepower = calculateTeamStrength(homeTeam);
double awaypower = calculateTeamStrength(awayTeam);
Random random = new Random();
int baseHome = random.nextInt(4);
int baseAway = random.nextInt(4);
int homeAdvantage = random.nextInt(2);
this.homeScore = baseHome + homeAdvantage;
this.awayScore = baseAway;
  if (homepower > awaypower) {
            if (random.nextBoolean()) {
                this.homeScore++;
            }
        } else if (awaypower > homepower) {
            if (random.nextBoolean()) {
                this.awayScore++;
            }
        }
    }

    public void displayResult() {
        System.out.println("\n===== MATCH RESULT =====");

        System.out.println(
                homeTeam.getName() + " " + homeScore
                + " - " + awayScore + " " + awayTeam.getName()
        );

        if (homeScore > awayScore) {
            System.out.println("Winner: " + homeTeam.getName());
        } else if (awayScore > homeScore) {
            System.out.println("Winner: " + awayTeam.getName());
        } else {
            System.out.println("The match ended in a draw!");
        }
}
}
