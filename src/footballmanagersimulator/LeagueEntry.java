/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package footballmanagersimulator;

/**
 *
 * @author Lenovo
 */
public class LeagueEntry {
private Team team;
private int played;
private int wins;
private int draws;
private int losses;
private int goalsFor;
private int goalsAgaints;
private int points;
public LeagueEntry(Team team){
if(team==null){
throw new IllegalArgumentException("Team can not be null.");    
}
this.team=team;
this.wins=0;
this.draws=0;
this.losses=0;
this.goalsFor=0;
this.goalsAgaints=0;
this.points=0;
}
public void recordMatch(int scored, int conceded) {
if (scored < 0 || conceded < 0) {
throw new IllegalArgumentException("Atılan veya yenilen gol sayısı negatif olamaz!");
}
this.played++;
this.goalsFor += scored;
this.goalsAgaints += conceded;
if(scored>conceded){
this.wins++;
this.points +=3;
}
if(conceded>scored){
this.losses++;

}if(conceded==scored){
 this.draws++;
 this.points+=1;
}
}

    public Team getTeam() {
        return team;
    }

    public int getPlayed() {
        return played;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgaints() {
        return goalsAgaints;
    }

    public int getPoints() {
        return points;
    }
    public void resetStats() {
    this.played = 0;
    this.wins = 0;
    this.draws = 0;
    this.losses = 0;
    this.goalsFor = 0;
    this.goalsAgaints = 0;
    this.points = 0;

}
    public int getGoalsAgainst() {
    return goalsAgaints;
}

public int getGoalDifference() {
    return goalsFor - goalsAgaints;
}
    
}
