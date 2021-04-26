package assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class Menu {
    public Menu() {
        Scanner scanner = new Scanner(System.in);
        List<Team> teams = new ArrayList<>();
        List<Participant> individuals = new ArrayList<>();
        int option;

        scanner.useDelimiter("\n");

        // Loop to allow the user to keep selecting options
        do {
            // Show the user all the options
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
                // Add participant
                System.out.println("Enter participant type (team or individual):");
                String participantType = scanner.next();
                if(!participantType.equals("team") && !participantType.equals("individual")) {
                    System.out.println("Participant type incorrect, please try again.");
                } else {
                    if(participantType.equals("individual")) {
                        // Create new participant and add a single member to it, then add it to the individuals list
                        Participant participant = new Participant();
                        Member member = new Member();
                        participant.setParticipantType("individual"); // Set participant type to individual
                        participant.setParticipantID(individuals.size()); // Set the participant ID
                        // Get first name from user
                        System.out.println("Enter first name:");
                        // Get last name from user
                        String firstName = scanner.next();
                        System.out.println("Enter last name: ");
                        String lastName = scanner.next();
                        // Get all/single event play from user
                        System.out.println("Play for all or a single event? (all/single)");
                        String singleEvent = scanner.next();
                        // Error if user enters incorrect option
                        if(!singleEvent.equals("all") && !singleEvent.equals("single")) {
                            System.out.println("Invalid option, try again.");
                        } else {
                            // Set all entered values
                            member.setFirstName(firstName);
                            member.setLastName(lastName);
                            participant.addMember(member);
                            participant.setSingleEvent(singleEvent.equals("single"));
                            individuals.add(participant); // Add participant
                        }
                    } else {
                        // Create new team then add it to the teams list
                        Team team = new Team();
                        team.setParticipantType("team"); // set participant type to team
                        team.setParticipantID(teams.size()); // set participant id
                        // Get team name from user
                        System.out.println("Enter team name:");
                        String teamName = scanner.next();
                        team.setTeamName(teamName);
                        // Loop to allow teams to add members
                        for (int i = 0; i < 5; i++) {
                            Member member = new Member(); // Create new member
                            // Get member first name from user
                            System.out.println("Enter team member " + (team.members.size() + 1) + "'s first name");
                            String firstName = scanner.next();
                            // Get member last name from user
                            member.setFirstName(firstName);
                            System.out.println("Enter team member " + (team.members.size() + 1) + "'s last name");
                            String lastName = scanner.next();
                            member.setLastName(lastName);
                            team.addMember(member); // Add member to team
                        }
                        // Get all/single event 
                        System.out.println("Play for all or a single event? (all/single)");
                        String singleEvent = scanner.next();
                        if(!singleEvent.equals("all") && !singleEvent.equals("single")) {
                            System.out.println("Invalid option, try again."); // Error if invalid option
                        } else {
                            team.setSingleEvent(singleEvent.equals("single"));
                            teams.add(team); // Add team
                        }
                    }
                }
            } else if(option == 2) {
                // Manage participants
                // Show another menu
                System.out.println("Select an option:");
                System.out.println("1. List teams");
                System.out.println("2. List individuals");
                System.out.println("3. Remove participant");
                System.out.println("4. Go back");

                while(!scanner.hasNextInt()) {
                    System.out.println("Input wasn't a number, please try again."); // Error if incorrect value entered
                    scanner.next();
                }

                int option2 = scanner.nextInt();

                if(option2 == 1) {
                    // List teams
                    for (Team team: teams) {
                        System.out.println("Team \"" + team.teamName + "\" (ID " + team.participantID + ")");
                        for (Member member: team.members) {
                            System.out.println("\tMember \"" + member.firstName + " " + member.lastName + "\"");
                        }
                    }
                } else if(option2 == 2) {
                    // List individuals
                    for (Participant participant: individuals) {
                        Member member = participant.members.get(0);
                        System.out.println("Individual \"" + member.firstName + " " + member.lastName + "\" ID " + participant.participantID);
                    }
                } else if(option2 == 3) {
                    // Remove participant
                    System.out.println("Enter participant type (team or individual):");
                    String participantType = scanner.next();
                    if(participantType.equals("team")) {
                        System.out.println("Enter team ID:");

                        while(!scanner.hasNextInt()) {
                            System.out.println("Input wasn't a number, please try again.");
                            scanner.next();
                        }

                        int participantID = scanner.nextInt();
                        for(Iterator<Team> iterator = teams.iterator(); iterator.hasNext();) {
                            Team team = iterator.next();
                            if(team.participantID == participantID) {
                                iterator.remove();
                            }
                        }
                    } else if(participantType.equals("individual")) {
                        System.out.println("Enter individual ID:");

                        while(!scanner.hasNextInt()) {
                            System.out.println("Input wasn't a number, please try again.");
                            scanner.next();
                        }

                        int participantID = scanner.nextInt();
                        for(Iterator<Participant> iterator = individuals.iterator(); iterator.hasNext();) {
                            Participant participant = iterator.next();
                            if(participant.participantID == participantID) {
                                iterator.remove();
                            }
                        }
                    } else {
                        System.out.println("Invalid option, please try again.");
                    }
                } else if(option2 != 4) {
                    // Error if invalid option
                    System.out.println("Invalid option, please try again.");
                }

            } else if(option == 3) {
                // Start tournament
                // Check if number of teams is correct
                if(teams.size() != 4 && teams.size() != 0) {
                    System.out.println("Not enough teams.");
                } else if(individuals.size() % 2 != 0) {
                    System.out.println("Number of individuals is odd.");
                } else if(individuals.size() == 0 && teams.size() == 0) {
                    // Check if there are any participants
                    System.out.println("No participants were added.");
                } else {
                    List<Score> individualScores = new ArrayList<>();
                    List<Score> teamScores = new ArrayList<>();
                    Random random = new Random();
                    for (Participant participant: individuals) {
                        Score score = new Score();
                        score.setParticipantID(participant.participantID);
                        individualScores.add(score);
                    }
                    for (Team team: teams) {
                        Score score = new Score();
                        score.setParticipantID(team.participantID);
                        teamScores.add(score);
                    }
                    if(individuals.size() > 0) {
                        List<Participant> alreadyPlayedI = new ArrayList<>();
                        for (int i = 1; i < 6; i++) {
                            List<Participant> eventParticipantsI = new ArrayList<>(individuals);
                            System.out.println("Event " + i);
                            for (Participant p: alreadyPlayedI) {
                                if(p.singleEvent) {
                                    eventParticipantsI.remove(p);
                                }
                            }
                            if(eventParticipantsI.size() > 1) {
                                int p1 = random.nextInt(eventParticipantsI.size());
                                Participant pa1 = eventParticipantsI.get(p1);
                                alreadyPlayedI.add(pa1);
                                eventParticipantsI.remove(pa1);
                                int p2 = random.nextInt(eventParticipantsI.size());
                                Participant pa2 = eventParticipantsI.get(p2);
                                alreadyPlayedI.add(pa2);
                                eventParticipantsI.remove(pa2);
                                System.out.println("Individual " + pa1.members.get(0).firstName + " " + pa1.members.get(0).lastName + " vs " + pa2.members.get(0).firstName + " " + pa2.members.get(0).lastName);
                                System.out.println("Enter result:");
                                System.out.println("1. Participant 1 win");
                                System.out.println("2. Participant 2 win");
                                System.out.println("3. Draw");

                                while (!scanner.hasNextInt()) {
                                    System.out.println("Input wasn't a number, please try again.");
                                    scanner.next();
                                }

                                int result = scanner.nextInt();
                                if (result == 1) {
                                    for (ListIterator<Score> iterator = individualScores.listIterator(); iterator.hasNext(); ) {
                                        Score score = iterator.next();
                                        if (score.participantID == pa1.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                    }
                                } else if (result == 2) {
                                    for (ListIterator<Score> iterator = individualScores.listIterator(); iterator.hasNext(); ) {
                                        Score score = iterator.next();
                                        if (score.participantID == pa2.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                    }
                                } else if (result == 3) {
                                    for (ListIterator<Score> iterator = individualScores.listIterator(); iterator.hasNext(); ) {
                                        Score score = iterator.next();
                                        if (score.participantID == pa1.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                        if (score.participantID == pa2.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(teams.size() > 0) {
                        List<Team> alreadyPlayedT = new ArrayList<>();
                        for(int i = 1; i < 6; i++) {
                            List<Team> eventParticipantsT = new ArrayList<>(teams);
                            System.out.println("Event " + i);
                            for (Team t: alreadyPlayedT) {
                                if(t.singleEvent) {
                                    eventParticipantsT.remove(t);
                                }
                            }
                            if(eventParticipantsT.size() > 1) {
                                int p1 = random.nextInt(eventParticipantsT.size());
                                Team pa1 = eventParticipantsT.get(p1);
                                alreadyPlayedT.add(pa1);
                                eventParticipantsT.remove(pa1);
                                int p2 = random.nextInt(eventParticipantsT.size());
                                Team pa2 = eventParticipantsT.get(p2);
                                alreadyPlayedT.add(pa2);
                                eventParticipantsT.remove(pa2);
                                System.out.println("Team " + pa1.teamName + " vs " + pa2.teamName);
                                System.out.println("Enter result:");
                                System.out.println("1. Participant 1 win");
                                System.out.println("2. Participant 2 win");
                                System.out.println("3. Draw");

                                while (!scanner.hasNextInt()) {
                                    System.out.println("Input wasn't a number, please try again.");
                                    scanner.next();
                                }

                                int result = scanner.nextInt();
                                if (result == 1) {
                                    for (ListIterator<Score> iterator = teamScores.listIterator(); iterator.hasNext(); ) {
                                        Score score = iterator.next();
                                        if (score.participantID == pa1.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                    }
                                } else if (result == 2) {
                                    for (ListIterator<Score> iterator = teamScores.listIterator(); iterator.hasNext(); ) {
                                        Score score = iterator.next();
                                        if (score.participantID == pa2.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                    }
                                } else if (result == 3) {
                                    for (ListIterator<Score> iterator = teamScores.listIterator(); iterator.hasNext(); ) {
                                        Score score = iterator.next();
                                        if (score.participantID == pa1.participantID) {
                                            score.add(3);
                                            iterator.set(score);
                                        }
                                        if (score.participantID == pa2.participantID) {
                                            score.add(3);
                                            iterator.set(score);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Collections.sort(individualScores, new Comparator<Score>() {
                        @Override
                        public int compare(Score a, Score b) {
                            return Integer.compare(b.score, a.score);
                        } 
                    });
                    Collections.sort(teamScores, new Comparator<Score>() {
                        @Override
                        public int compare(Score a, Score b) {
                            return Integer.compare(b.score, a.score);
                        } 
                    });
                    int x = 1;
                    System.out.println("Individual scores:");
                    for(Score score: individualScores) {
                        for (Participant individual : individuals) {
                            if (individual.participantID == score.participantID) {
                                System.out.println("#" + x + " - " + individual.members.get(0).firstName + " " + individual.members.get(0).lastName + " - " + score.score);
                            }
                        }
                        x++;
                    }
                    int y = 1;
                    System.out.println("Team scores:");
                    for(Score score: individualScores) {
                        for (Team team : teams) {
                            if (team.participantID == score.participantID) {
                                System.out.println("#" + y + " - " + team.teamName + " - " + score.score);
                            }
                        }
                        y++;
                    }
                }
            } else if(option != 4) {
                // Error when invalid option given
                System.out.println("Invalid option, please try again.");
            }

        } while (option != 4); // exit if option 4 picked
    }
}
