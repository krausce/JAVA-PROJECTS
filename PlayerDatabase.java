import java.util.ArrayList;
import java.util.Collections;

class Player {
	String name = "";
	public double attack_stat = 0;
	public double block_stat = 0;
	
	Player(String init_name, double init_attack_stat, double init_block_stat){
		this.name = init_name;
		this.attack_stat = init_attack_stat;
		this.block_stat = init_block_stat;
	}
		
	void print() {
		System.out.println(this.name + " attack stat: " + this.attack_stat + " block stat: " + this.block_stat);
	}
}

class ScrimmageTeam {
	String team_name = "";
	ArrayList<String> team_roster = new ArrayList<String>();
	
	ScrimmageTeam(String init_name){
		this.team_name = init_name;
	}
	public void addPlayers(String name) {
		this.team_roster.add(name);
	}
	void print() {
		System.out.println(team_name);
		for(String player_name: team_roster) {
			System.out.println(player_name);
		}
		System.out.println();
	}
}

class PlayerDatabase {		
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Player> players_by_attack_stats = new ArrayList<Player>();
	ArrayList<Player> players_by_block_stats = new ArrayList<Player>();
	ArrayList<ScrimmageTeam> scrimmage_teams = new ArrayList<ScrimmageTeam>();
	
	void addPlayer(String init_name, double init_attack_stat, double init_block_stat) {		
		Player new_player = new Player(init_name, init_attack_stat, init_block_stat);
		players.add(new_player);
	}
	
	void emptyPlayerDatabase () {
		players.clear();
		players_by_attack_stats.clear();
		players_by_block_stats.clear();
		scrimmage_teams.clear();
	}
	
	void printPlayer() {
		for(Player player: players) {
			player.print();
		}
	}
	
	void printScrimmageTeams () {
		for(ScrimmageTeam team: scrimmage_teams) {
			team.print();
		}
	}
	
	void printPlayersAttack() {
		for(Player player: players_by_attack_stats) {
			player.print();
		}
	}
	
	void printPlayersBlock() {
		for(Player player: players_by_block_stats) {
			player.print();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void sortPlayersAttack() {
		ArrayList<Double> attack_stats = new ArrayList();
		for(Player player: players) {
			attack_stats.add(player.attack_stat);
		}
		Collections.sort(attack_stats, Collections.reverseOrder());
		int index_attack = 0;
		while(index_attack < this.players.size() -1 ) {
			for(Player player: this.players) {
				try {
					if(player.attack_stat == attack_stats.get(index_attack)) {
						players_by_attack_stats.add(player);
						index_attack++;
					}
				}
				catch(Exception IndexOutOfBoundsException) {
					continue;
				}
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void sortPlayersBlock() {
		ArrayList<Double> block_stats = new ArrayList();
		for(Player player: players) {
			block_stats.add(player.block_stat);
		}
		Collections.sort(block_stats, Collections.reverseOrder());
		int index_block = 0;
		while(index_block < block_stats.size() - 1) {
			for(Player player: players) {
				try {
					if(player.block_stat == block_stats.get(index_block)) {
						players_by_block_stats.add(player);
						index_block++;
					}
				}
				catch(Exception IndexOutOfBoundsException) {
					continue;
				}
			}
		}
	}
	public void makeScrimmageTeams (int number_scrimmage_teams){
		ArrayList<String> attackers = new ArrayList<String>();
		ArrayList<String> blockers = new ArrayList<String>();
		
		for(Player player: players_by_attack_stats) {
			attackers.add(player.name);
		}
		for(Player player: players_by_block_stats) {
			blockers.add(player.name);
		}
		for(int index = 0; index < number_scrimmage_teams; index++) {
			String team_name = "Team" + Integer.toString(index + 1);
			ScrimmageTeam team = new ScrimmageTeam(team_name);
			scrimmage_teams.add(team);
		}
		int number_attackers = 3;
		int number_blockers = 3;
		while(number_attackers > 0) {
			for(ScrimmageTeam team_attackers: scrimmage_teams) {
				String name_to_remove = attackers.get(0);
				blockers.remove(name_to_remove);
				team_attackers.team_roster.add(attackers.remove(0));				
			}			
			number_attackers--;
		}
		while(number_blockers > 0) {
			for(ScrimmageTeam team_blockers: scrimmage_teams) {
				team_blockers.team_roster.add(blockers.remove(0));				
			}			
			number_blockers--;
		}
	}
}