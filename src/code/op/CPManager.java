package code.op;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.entity.Player;

public class CPManager implements Runnable {

	private List<CarbynePlayer> players;
	
	public CPManager() {
		players = new ArrayList<CarbynePlayer>();
	}

	public List<CarbynePlayer> getPlayers() {
		return players;
	}
	
	public void addPlayer(Player p) {
		players.add(new CarbynePlayer(p));
	}
	
	public void removePlayer(Player p) {
		try {
		players.remove(getCPByName(p.getName()));
		} catch (NullPointerException ex) {
			return;
		}
	}
	
	public boolean containsPlayer(Player p) {
		for (CarbynePlayer s : players) {
			if (s.getName().equals(p.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public CarbynePlayer getPlayer(Player player) {
		for (CarbynePlayer s : players) {
			if (player.equals(s.getP())) {
				return s;
			}
		}
		return null;
	}
	
	public CarbynePlayer getCPByName(String name) {
		for (CarbynePlayer s : players) {
			if(s.getName().equalsIgnoreCase(name)) {
				return s;
			}
		}
		return null;
	}

	public void run() {
		Iterator<CarbynePlayer> itr = players.iterator();
		while (itr.hasNext()) {
			itr.next().tick();
		}
	}
	
}
