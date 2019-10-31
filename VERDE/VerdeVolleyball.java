import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class VerdeVolleyball {
	
	public static void main(String[] args) {
		//Instantiate a PlayerDatabase object
		PlayerDatabase team_stats = new PlayerDatabase();
		PlayerDatabase top_3_attackers = new PlayerDatabase();
		PlayerDatabase top_3_blockers = new PlayerDatabase();
		displayMenu(); 
		System.out.println("\n                   !!!!!Please selection \"Option 1\" first to load a roster file.!!!!!!");
		int user_selection = 0;
		String file_name = "";
		while(user_selection != 5) {
			user_selection = getUserMenuSelection();
			System.out.println();
			if(user_selection == 1) {
				if(team_stats.players.size() > 0) {
					try {
						team_stats.emptyPlayerDatabase();
						top_3_attackers.emptyPlayerDatabase();
						top_3_blockers.emptyPlayerDatabase();
					}
					catch(Exception ConcurrentModificationException) {
						continue;
					}
				}
				try {
					Files.list(Paths.get(".")).forEach(System.out::println);
				} catch (IOException e1) {
					System.out.println("No files found in the directory.");
					e1.printStackTrace();
					continue;
				}
				while(team_stats.players.size() == 0) {
					file_name = getUserFileName();
					//Read in the team roster file.
					try {
						team_stats = openRoster(file_name, team_stats);
					} catch (FileNotFoundException e) {
						System.out.println("\nFile not found. Please enter a different file name with extension.");
						continue;
					}
				}
				//Sort players by their Attack stat.
				team_stats.sortPlayersAttack();
				int count_attack = 0;					
				while(count_attack < 3) {
					top_3_attackers.addPlayer(team_stats.players_by_attack_stats.get(count_attack).name, team_stats.players_by_attack_stats.get(count_attack).attack_stat, team_stats.players_by_attack_stats.get(count_attack).block_stat);
					count_attack++;	
				}
				//Sort players by their Block stat.
				team_stats.sortPlayersBlock();
				int count_block = 0;					
				while(count_block < 3) {
					top_3_blockers.addPlayer(team_stats.players_by_block_stats.get(count_block).name, team_stats.players_by_block_stats.get(count_block).attack_stat, team_stats.players_by_block_stats.get(count_block).block_stat);
					count_block++;						
				}
				//Make balanced scrimmage teams.
				int number_scrimmage_teams = team_stats.players.size()/6;
				team_stats.makeScrimmageTeams(number_scrimmage_teams);
				
			} else if(user_selection == 2) {
				//If user selected this option first, catch the error and have them select "Option 1" first to load a roster file.
				if(team_stats.players.size() > 0) {
					top_3_attackers.printPlayer();
				} else {
					System.out.println("Please select Option \"1\" to open a roster first.");
				}
				
			} else if(user_selection == 3) {
				//If user selected this option first, catch the error and have them select "Option 1" first to load a roster file.
				if(team_stats.players.size() > 0) {
					top_3_blockers.printPlayer();
				} else {
					System.out.println("Please select Option \"1\" to open a roster first.");
				}
				
			} else if(user_selection == 4) {
				//If user selected this option first, catch the error and have them select "Option 1" first to load a roster file.
				if(team_stats.players.size() > 0) {
					team_stats.printScrimmageTeams();
				} else {
					System.out.println("Please select Option \"1\" to open a roster first.");
				}
				
			} else if(user_selection == 5) {
				break;
			} else {
				System.out.println("Invalid menu selection, please enter a number (1 - 5).");
			}
			
		}				
	}
	
	private static PlayerDatabase openRoster(String file_name, PlayerDatabase team_stats) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(file_name));
		ArrayList<Double> player_stats = new ArrayList<Double>();
		ArrayList<String> players_names = new ArrayList<String>();
		while(scanner.hasNextLine()) {
			//Get names from the text scanner.
			Pattern name = Pattern.compile("([a-zA-Z]+) ([a-zA-Z]+)");
			try {
				//Clear local variables each time through.
				players_names.add(scanner.findInLine(name));				
				player_stats.add(scanner.nextDouble());
				
			}
			catch(Exception e) {
				continue;
			}			
		}
		players_names.removeIf(n -> n == null);
		int index = 0;
		while(players_names.size() > 0) {
			team_stats.addPlayer((players_names.remove(index)), (player_stats.remove(index)), (player_stats.remove(index))) ;
		}
		return team_stats;		
	}
	
	private static void displayMenu() {
		int height = 26;
		for(int count = 1; count <= height; count++) {
			if(count == 1 || count == height) {
				printFirstLineOrLastLine(height);
			} else if(count == 2) {
				System.out.println("               ***************************************************************************");
				System.out.println("               *                  Verde Valley High School Volleyball                   **");
				System.out.println("               ***************************************************************************");
			} else if(count == Math.round((height/2))){
				System.out.println("               *                          1. Open a roster                               *");
				System.out.println("               *                                                                         *");
				System.out.println("               *                          2. List top 3 attackers                        *");
				System.out.println("               *                                                                         *");
				System.out.println("               *                          3. List top 3 blockers                         *");
				System.out.println("               *                                                                         *");
				System.out.println("               *                          4. Make and display scrimmage teams            *");
				System.out.println("               *                                                                         *");
				System.out.println("               *                          5. Quit                                        *");
				System.out.println("               *                                                                         *");
			}
		}
	}
	
	public static void printFirstLineOrLastLine(int height) {
		for(int count = 1; count <= height; count++) {
			if(count == 1) {
				System.out.print("               ");
				System.out.print("+ ");
			} else if (count == height) {
				System.out.println("+");
			} else {
				System.out.print("*  ");
			}
		}
	}
	
	private static int getUserMenuSelection() {
		System.out.print("\nEnter the menu item you would like to execute: ");
		int menu_item = 0;
		try {
			Scanner scanner = new Scanner(System.in);
			menu_item = scanner.nextInt();			
		}
		catch(Exception InputMismatchException) {
			System.out.println("You entered an invalid selection. Please select a menu option (1 - 5).");
		}
		return menu_item;
	}
	
	private static String getUserFileName() {
		String file = "";
		try {
			System.out.print("\nEnter the roster file name, including the extenion, you would like to open: ");
			Scanner scanner = new Scanner(System.in);
			file = scanner.nextLine();
		}
		catch(Exception InputMismatchException) {
			System.out.println("You entered an invalid file name. Please re-enter the file name with the extension.");
		}
		return file;
	}
}