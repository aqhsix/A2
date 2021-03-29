package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public Menu() {
        Scanner scanner = new Scanner(System.in);
        List<Team> teams = new ArrayList<>();
        List<Participant> individuals = new ArrayList<>();
        int option;

        scanner.useDelimiter("\n");

        do {
            System.out.println("Select an option:");
            System.out.println("1. Add participant");
            System.out.println("2. Manage participants");
            System.out.println("3. Start tournament");
            System.out.println("4. Exit\n");

            while(!scanner.hasNextInt()) {
                System.out.println("Input wasn't a number, please try again.");
                scanner.next();
            }

            option = scanner.nextInt();

            if(option == 1) {
                System.out.println("Enter participant type (team or individual):");
                String participantType = scanner.next();
                if(!participantType.equals("team") && !participantType.equals("individual")) {
                    System.out.println("Participant type incorrect, please try again.");
                } else {
                    if(participantType.equals("individual")) {
                        Participant participant = new Participant();
                        Member member = new Member();
                        participant.setParticipantType("individual");
                        participant.setParticipantID(individuals.size());
                        System.out.println("Enter first name:");
                        String firstName = scanner.next();
                        System.out.println("Enter last name: ");
                        String lastName = scanner.next();
                        member.setFirstName(firstName);
                        member.setLastName(lastName);
                        participant.addMember(member);
                        individuals.add(participant);
                    } else {
                        Team team = new Team();
                        team.setParticipantType("team");
                        team.setParticipantID(teams.size());
                        System.out.println("Enter team name:");
                        String teamName = scanner.next();
                        team.setTeamName(teamName);
                        do {
                            Member member = new Member();
                            System.out.println("Enter team member " + (team.members.size() + 1) + "'s first name");
                            String firstName = scanner.next();
                            member.setFirstName(firstName);
                            System.out.println("Enter team member " + (team.members.size() + 1) + "'s last name");
                            String lastName = scanner.next();
                            member.setLastName(lastName);
                            team.addMember(member);
                            System.out.println("Add another? (y/n)");
                            String another = scanner.next();
                            if(!another.equals("y")) {
                                break;
                            }
                        } while (true);
                        teams.add(team);
                    }
                }
            } else if(option == 2) {
                System.out.println("Select an option:");
                System.out.println("1. List teams");
                System.out.println("2. List individuals");
                System.out.println("3. Remove participant");
                System.out.println("4. Go back");

                while(!scanner.hasNextInt()) {
                    System.out.println("Input wasn't a number, please try again.");
                    scanner.next();
                }

                int option2 = scanner.nextInt();

                if(option2 == 1) {
                    for (Team team: teams) {
                        System.out.println("Team \"" + team.teamName + "\" (ID " + team.participantID + ")");
                        for (Member member: team.members) {
                            System.out.println("\tMember \"" + member.firstName + " " + member.lastName + "\"");
                        }
                    }
                } else if(option2 == 2) {
                    for (Participant participant: individuals) {
                        Member member = participant.members.get(0);
                        System.out.println("Individual \"" + member.firstName + " " + member.lastName + "\"");
                    }
                } else if(option2 == 3) {
                    System.out.println("Enter participant type (team or individual):");
                    String participantType = scanner.next();
                    if(participantType.equals("team")) {
                        System.out.println("Enter team ID:");

                        while(!scanner.hasNextInt()) {
                            System.out.println("Input wasn't a number, please try again.");
                            scanner.next();
                        }

                        int participantID = scanner.nextInt();
                        teams.remove(teams.stream().filter(team -> participantID == team.participantID).findFirst().orElse(null));
                    } else if(participantType.equals("individual")) {
                        System.out.println("Enter individual ID:");

                        while(!scanner.hasNextInt()) {
                            System.out.println("Input wasn't a number, please try again.");
                            scanner.next();
                        }

                        int participantID = scanner.nextInt();
                        individuals.remove(individuals.stream().filter(individual -> participantID == individual.participantID).findFirst().orElse(null));
                    } else {
                        System.out.println("Invalid option, please try again.");
                    }
                } else if(option2 != 4) {
                    System.out.println("Invalid option, please try again.");
                }

            } else if(option == 3) {
                System.out.println("Not implemented (start tournament)");
            } else if(option != 4) {
                System.out.println("Invalid option, please try again.");
            }

        } while (option != 4);
    }
}
