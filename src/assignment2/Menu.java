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
                        System.out.println("Individual \"" + member.firstName + " " + member.lastName + "\" ID " + participant.participantID);
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
                    System.out.println("Invalid option, please try again.");
                }

            } else if(option == 3) {
                System.out.println("Not implemented (start tournament)");
                // check there is enough players to play 5 events - at least 2 teams/2 participants
                // must be even
                if(teams.size() % 2 != 0) {
                    System.out.println("Number of teams is odd.");
                    break;
                } else if(individuals.size() % 2 != 0) {
                    System.out.println("Number of individuals is odd.");
                } else if(individuals.size() == 0 && teams.size() == 0) {
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
                    for (int i = 1; i < 6; i++) {
                        System.out.println("Event " + i);
                        if(individuals.size() > 0) {
                            List<Participant> eventParticipants = new ArrayList<>(individuals);
                            for(int s = 0; s < eventParticipants.size() / 2; s++) {
                                int p1 = random.nextInt(eventParticipants.size());
                                Participant pa1 = eventParticipants.get(p1);
                                eventParticipants.remove(pa1);
                                int p2 = random.nextInt(eventParticipants.size());
                                Participant pa2 = eventParticipants.get(p2);
                                eventParticipants.remove(pa2);
                                System.out.println("Individual " + pa1.members.get(0).firstName + " " + pa1.members.get(0).lastName + " vs " + pa2.members.get(0).firstName + " " + pa2.members.get(0).lastName);
                                System.out.println("Enter result:");
                                System.out.println("1. Participant 1 win");
                                System.out.println("2. Participant 2 win");
                                System.out.println("3. Draw");
                                
                                while(!scanner.hasNextInt()) {
                                    System.out.println("Input wasn't a number, please try again.");
                                    scanner.next();
                                }
                                
                                int result = scanner.nextInt();
                                if(result == 1) {
                                    for(ListIterator<Score> iterator = individualScores.listIterator(); iterator.hasNext();) {
                                        Score score = iterator.next();
                                        if(score.participantID == pa1.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                    }
                                } else if(result == 2) {
                                    for(ListIterator<Score> iterator = individualScores.listIterator(); iterator.hasNext();) {
                                        Score score = iterator.next();
                                        if(score.participantID == pa2.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                    }
                                } else if(result == 3) {
                                    for(ListIterator<Score> iterator = individualScores.listIterator(); iterator.hasNext();) {
                                        Score score = iterator.next();
                                        if(score.participantID == pa1.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                        if(score.participantID == pa2.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for(int i = 1; i < 6; i++) {
                        if(teams.size() > 0) {
                            List<Team> eventParticipants = new ArrayList<>(teams);
                            for(int s = 0; s < eventParticipants.size() / 2; s++) {
                                int p1 = random.nextInt(eventParticipants.size());
                                Team pa1 = eventParticipants.get(p1);
                                eventParticipants.remove(pa1);
                                int p2 = random.nextInt(eventParticipants.size());
                                Team pa2 = eventParticipants.get(p2);
                                eventParticipants.remove(pa2);
                                System.out.println("Team " + pa1.teamName + " vs " + pa2.teamName);
                                System.out.println("Enter result:");
                                System.out.println("1. Participant 1 win");
                                System.out.println("2. Participant 2 win");
                                System.out.println("3. Draw");
                                
                                while(!scanner.hasNextInt()) {
                                    System.out.println("Input wasn't a number, please try again.");
                                    scanner.next();
                                }
                                
                                int result = scanner.nextInt();
                                if(result == 1) {
                                    for(ListIterator<Score> iterator = teamScores.listIterator(); iterator.hasNext();) {
                                        Score score = iterator.next();
                                        if(score.participantID == pa1.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                    }
                                } else if(result == 2) {
                                    for(ListIterator<Score> iterator = teamScores.listIterator(); iterator.hasNext();) {
                                        Score score = iterator.next();
                                        if(score.participantID == pa2.participantID) {
                                            score.add(5);
                                            iterator.set(score);
                                        }
                                    }
                                } else if(result == 3) {
                                    for(ListIterator<Score> iterator = teamScores.listIterator(); iterator.hasNext();) {
                                        Score score = iterator.next();
                                        if(score.participantID == pa1.participantID) {
                                            score.add(3);
                                            iterator.set(score);
                                        }
                                        if(score.participantID == pa2.participantID) {
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
                            return a.score > b.score ? -1 : (a.score < b.score) ? 1 : 0;
                        } 
                    });
                    Collections.sort(teamScores, new Comparator<Score>() {
                        @Override
                        public int compare(Score a, Score b) {
                            return a.score > b.score ? -1 : (a.score < b.score) ? 1 : 0;
                        } 
                    });
                    int x = 1;
                    System.out.println("Individual scores:");
                    for(Score score: individualScores) {
                        for(Iterator<Participant> iterator = individuals.iterator(); iterator.hasNext();) {
                            Participant individual = iterator.next();
                            if(individual.participantID == score.participantID) {
                                System.out.println("#" + x + " - " + individual.members.get(0).firstName + " " + individual.members.get(0).lastName + " - " + score.score);
                            }
                        }
                        x++;
                    }
                    int y = 1;
                    System.out.println("Team scores:");
                    for(Score score: individualScores) {
                        for(Iterator<Team> iterator = teams.iterator(); iterator.hasNext();) {
                            Team team = iterator.next();
                            if(team.participantID == score.participantID) {
                                System.out.println("#" + y + " - " + team.teamName + " - " + score.score);
                            }
                        }
                        y++;
                    }
                }
            } else if(option != 4) {
                System.out.println("Invalid option, please try again.");
            }

        } while (option != 4);
    }
}
