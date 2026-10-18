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
public class League {
private String name;  
private ArrayList<Team> teams ;
private ArrayList<LeagueEntry> entries;
public League(String name) {
if(name==null || name.trim().isEmpty()){
 throw new IllegalArgumentException("Leauge can not be null.");   
}    
        this.name = name;
        this.teams=new ArrayList<>();
        this.entries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

public boolean addTeam(Team team) {
    if (team == null) {
        return false;
    }

    if (teams.contains(team)) {
        return false;
    }

    for (Team t : teams) {
        if (t.getName().equalsIgnoreCase(team.getName())) {
            return false;
        }
    }

    teams.add(team);
    entries.add(new LeagueEntry(team));

    return true;

}
public int getTeamCount(){
 return teams.size();
}public void displayTeams(){
    if (this.teams.isEmpty()) {
        System.out.println("No teams in the league.");
        return;
    }
    System.out.println("Teams in the league:");
    for (Team t : this.teams) {
    System.out.println("- " + t.getName());
}    
    
}public Team findTeamByName(String name){
 if (name == null || name.trim().isEmpty()) {
     return null;
 } 
 for (Team t : this.teams) {
 if (t.getName().equalsIgnoreCase(name.trim())) {
 return t;    
 }    
 }
 return null;
}public void playLeague() {
    if (teams.size() < 2) {
        System.out.println("Not enough teams in the league.");
        return;
    }

    
    for (LeagueEntry entry : entries) {
        entry.resetStats();
    }

    for (int i = 0; i < teams.size(); i++) {
        for (int j = 0; j < teams.size(); j++) {

            if (i != j) {
                Team homeTeam = teams.get(i);
                Team awayTeam = teams.get(j);

                Match match = new Match(homeTeam, awayTeam);

                match.playMatch();
                match.displayResult();

                LeagueEntry homeEntry = findEntryByTeam(homeTeam);
                LeagueEntry awayEntry = findEntryByTeam(awayTeam);

                homeEntry.recordMatch(
                    match.getHomeScore(),
                    match.getAwayScore()
                );

                awayEntry.recordMatch(
                    match.getAwayScore(),
                    match.getHomeScore()
                );
            }
        }
    }

    System.out.println("\nAll league matches have been played!");
}
public void displayTable() {
    if (entries.isEmpty()) {
        System.out.println("No teams in the league.");
        return;
    }

    ArrayList<LeagueEntry> sortedEntries = new ArrayList<>(entries);

    sortedEntries.sort((a, b) -> {
        
        int result = Integer.compare(b.getPoints(), a.getPoints());

        if (result != 0) {
            return result;
        }

       
        result = Integer.compare(
            b.getGoalDifference(),
            a.getGoalDifference()
        );

        if (result != 0) {
            return result;
        }

        
        return Integer.compare(b.getGoalsFor(), a.getGoalsFor());
    });

    System.out.println("\n================ LEAGUE TABLE ================");

    System.out.printf(
        "%-20s %3s %3s %3s %3s %4s %4s %4s %4s%n",
        "Team", "P", "W", "D", "L", "GF", "GA", "GD", "Pts"
    );

    System.out.println(
        "---------------------------------------------------------------"
    );

    for (LeagueEntry entry : sortedEntries) {
        System.out.printf(
            "%-20s %3d %3d %3d %3d %4d %4d %4d %4d%n",
            entry.getTeam().getName(),
            entry.getPlayed(),
            entry.getWins(),
            entry.getDraws(),
            entry.getLosses(),
            entry.getGoalsFor(),
            entry.getGoalsAgainst(),
            entry.getGoalDifference(),
            entry.getPoints()
        );
    }

    System.out.println(
        "==============================================================="
    );
}
private LeagueEntry findEntryByTeam(Team team) {
    for (LeagueEntry entry : entries) {
        if (entry.getTeam() == team) {
            return entry;
        }
    }

    return null;
}


}
