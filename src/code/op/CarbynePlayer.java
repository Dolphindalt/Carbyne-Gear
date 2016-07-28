package code.op;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class CarbynePlayer {

	private Player p;
	private World world;
	private String name;
	
	private int djcd;
	
	public CarbynePlayer(Player p) {
		this.p = p;
		this.name = p.getName();
		this.djcd = 0;
	}
	
	public void tick() {
		if (djcd != 0) djcd--;
		checkFlight();
	}
	
	public void checkFlight() {
		if (djcd == 0 && world.getName().equalsIgnoreCase("world")) {
			p.setAllowFlight(true);
		} else p.setAllowFlight(false);
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

	public int getDjcd() {
		return djcd;
	}

	public void setDjcd(int djcd) {
		this.djcd = djcd;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
}
