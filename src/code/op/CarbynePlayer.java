package code.op;

import org.bukkit.entity.Player;

public class CarbynePlayer {

	private Player p;
	private String name;
	
	public CarbynePlayer(Player p) {
		this.p = p;
		this.name = p.getName();

	}
	
	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}
	
	public String getName() {
		return name;
	}
	
}
