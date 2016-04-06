package code.op.duel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import code.op.Main;

public class DuelZone {
	
	private Player p1;
	private Player p2;
	
	private Location wait1, wait2, spawn1, spawn2, end;
	
	public DuelZone(Location spawn1, Location spawn2, Location wait1, Location wait2, Location end) {
		this.p1 = null;
		this.p2 = null;
		this.spawn1 = spawn1;
		this.spawn2 = spawn2;
		this.wait1 = wait1;
		this.wait2 = wait2;
		this.end = end;
	}
	
	public void joinDuel(Player p) {
		if (p1 == null) {
			p1 = p;
			p1.teleport(wait1);
		} else if (p2 == null) {
			p2 = p;
			p2.teleport(wait2);
		} else {
			p.sendMessage(ChatColor.RED + "The duel zone is in use!");
			return;
		}
		if (p1 != null && p2 != null) countdown();
	}
	
	public void countdown() {
		p1.sendMessage(ChatColor.YELLOW + "Duel will begin in ten seconds...");
		p2.sendMessage(ChatColor.YELLOW + "Duel will begin in ten seconds...");
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				startDuel();
			}
		},200L);
	}
	
	public void startDuel() {
		if (p1 != null && p2 != null) {
			p1.teleport(spawn1);
			p2.teleport(spawn2);
			Bukkit.broadcastMessage(ChatColor.RED + "[Duel]: " + ChatColor.YELLOW + p1.getName() + " vs. " + p2.getName() + "!");
		}
	}
	
	public void resetDuel(Player dead) {
		if (p1 != null) {
			if (!p1.equals(dead)) p1.teleport(end);
		}
		if (p2 != null) {
			if (!p2.equals(dead)) p2.teleport(end);
		}
		p1 = null;
		p2 = null;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}
	
}
