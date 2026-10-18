/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package footballmanagersimulator;

import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class FootballManagerSimulator {

    /**
     * @param args the command line arguments
     */
   private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        League league = new League("Super League");

        Team galatasaray = new Team("Galatasaray", 100000000);
        Team fenerbahce = new Team("Fenerbahce", 100000000);
        Team besiktas = new Team("Besiktas", 100000000);

        galatasaray.addPlayer(new Player(
                1, "Player One", Position.STRIKER, 24, 90, 30000000
        ));

        galatasaray.addPlayer(new Player(
                2, "Player Two", Position.MIDFIELDER, 22, 85, 20000000
        ));

        fenerbahce.addPlayer(new Player(
                3, "Player Three", Position.DEFENDER, 25, 80, 15000000
        ));

        fenerbahce.addPlayer(new Player(
                4, "Player Four", Position.STRIKER, 23, 88, 25000000
        ));

        besiktas.addPlayer(new Player(
                5, "Player Five", Position.MIDFIELDER, 21, 82, 18000000
        ));

        besiktas.addPlayer(new Player(
                6, "Player Six", Position.DEFENDER, 26, 78, 12000000
        ));

        league.addTeam(galatasaray);
        league.addTeam(fenerbahce);
        league.addTeam(besiktas);

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    league.displayTeams();
                    break;

                case 2:
                    displayTeamPlayers(league);
                    break;

                case 3:
                    displayTeamStrengths(league);
                    break;

                case 4:
                    signPlayer(league);
                    break;

                case 5:
                    sellPlayer(league);
                    break;

                case 6:
                    displayTeamBudgets(league);
                    break;

                case 7:
                    league.playLeague();
                    break;

                case 8:
                    league.displayTable();
                    break;

                case 9:
                    displayAllTeamDetails(league);
                    break;

                case 0:
                    running = false;
                    System.out.println("Football Manager Simulator closed.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n========================================");
        System.out.println("       FOOTBALL MANAGER SIMULATOR");
        System.out.println("========================================");
        System.out.println("1. Display Teams");
        System.out.println("2. Display Team Players");
        System.out.println("3. Display Team Strengths");
        System.out.println("4. Sign Player");
        System.out.println("5. Sell Player");
        System.out.println("6. Display Team Budgets");
        System.out.println("7. Play League");
        System.out.println("8. Display League Table");
        System.out.println("9. Display All Team Details");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    private static void displayTeamPlayers(League league) {

        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine().trim();

        Team team = league.findTeamByName(teamName);

        if (team == null) {
            System.out.println("Team not found.");
            return;
        }

        System.out.println("\n--- " + team.getName() + " PLAYERS ---");
        team.displayPlayers();
    }

    private static void displayTeamStrengths(League league) {

        System.out.println("\n--- TEAM STRENGTHS ---");

        for (String name : new String[]{
                "Galatasaray", "Fenerbahce", "Besiktas"
        }) {
            Team team = league.findTeamByName(name);

            if (team != null) {
                System.out.printf(
                        "%-20s %.2f%n",
                        team.getName(),
                        team.getTeamStrength()
                );
            }
        }
    }

    private static void signPlayer(League league) {

        System.out.print("Enter destination team name: ");
        String teamName = scanner.nextLine().trim();

        Team team = league.findTeamByName(teamName);

        if (team == null) {
            System.out.println("Team not found.");
            return;
        }

        int id = readInt("Enter player ID: ");

        for (String name : new String[]{
                "Galatasaray", "Fenerbahce", "Besiktas"
        }) {
            Team existingTeam = league.findTeamByName(name);

            if (existingTeam != null
                    && existingTeam.findPlayerById(id) != null) {
                System.out.println("This player ID is already in use.");
                return;
            }
        }

        System.out.print("Enter player name: ");
        String playerName = scanner.nextLine().trim();

        if (playerName.isEmpty()) {
            System.out.println("Player name cannot be empty.");
            return;
        }

        System.out.println(
                "Positions: GOALKEEPER, DEFENDER, MIDFIELDER, STRIKER"
        );

        System.out.print("Enter position: ");
        Position position;

        try {
            position = Position.valueOf(
                    scanner.nextLine().trim().toUpperCase()
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid position.");
            return;
        }

        int age = readInt("Enter age: ");
        int overall = readInt("Enter overall (1-99): ");
        double marketValue = readDouble("Enter market value: ");

        try {
            Player player = new Player(
                    id,
                    playerName,
                    position,
                    age,
                    overall,
                    marketValue
            );

            if (team.signPlayer(player)) {
                System.out.println("Transfer successful!");
                System.out.println("Player: " + player.getName());
                System.out.println("Team: " + team.getName());
                System.out.println("Remaining budget: " + team.getBudget());
            } else {
                System.out.println(
                        "Transfer failed. Check the budget or player ID."
                );
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Could not create player: " + e.getMessage());
        }
    }

    private static void sellPlayer(League league) {

        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine().trim();

        Team team = league.findTeamByName(teamName);

        if (team == null) {
            System.out.println("Team not found.");
            return;
        }

        team.displayPlayers();

        int playerId = readInt("Enter player ID to sell: ");

        Player player = team.findPlayerById(playerId);

        if (player == null) {
            System.out.println("Player not found in this team.");
            return;
        }

        String playerName = player.getName();

        if (team.sellPlayer(playerId)) {
            System.out.println("Player sold successfully: " + playerName);
            System.out.println("Updated budget: " + team.getBudget());
        } else {
            System.out.println("Player sale failed.");
        }
    }

    private static void displayTeamBudgets(League league) {

        System.out.println("\n--- TEAM BUDGETS ---");

        for (String name : new String[]{
                "Galatasaray", "Fenerbahce", "Besiktas"
        }) {
            Team team = league.findTeamByName(name);

            if (team != null) {
                System.out.printf(
                        "%-20s %.2f%n",
                        team.getName(),
                        team.getBudget()
                );
            }
        }
    }

    private static void displayAllTeamDetails(League league) {

        for (String name : new String[]{
                "Galatasaray", "Fenerbahce", "Besiktas"
        }) {
            Team team = league.findTeamByName(name);

            if (team != null) {
                System.out.println("\n================================");
                System.out.println("Team: " + team.getName());
                System.out.println("Budget: " + team.getBudget());

                System.out.printf(
                        "Team Strength: %.2f%n",
                        team.getTeamStrength()
                );

                team.displayPlayers();
            }
        }
    }

    private static int readInt(String message) {

        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {
            System.out.print(message);

            try {
                double value = Double.parseDouble(
                        scanner.nextLine().trim()
                );

                if (Double.isFinite(value)) {
                    return value;
                }

                System.out.println("Please enter a finite number.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}


