package code.op.sb;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import SB.SMHandler;
import SB.SMPlayer;

public class ScoreboardUpdater implements Runnable {
	
	public static List<Player> disabled = new ArrayList<Player>();
	
	private int i = 0;
	
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			try {
				SMPlayer pl = SMHandler.getPlayer(p);
				if (disabled.contains(p)) {
					p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				}
				updateScoreboard(pl);
			} catch (NullPointerException ex) {
				continue;
			}
		}
	}

	public void updateScoreboard(SMPlayer p) {
		i++;
		p.updateLine(6, ChatColor.GREEN + "Health: " + Math.floor(p.getPlayer().getHealth()) + " / " + p.getPlayer().getMaxHealth());
		switch (i) {
			case 1: p.updateLine(8, ChatColor.GOLD.toString() + ChatColor.BOLD + "Medieval Lords");
			case 9: p.updateLine(8, ChatColor.AQUA.toString() + ChatColor.BOLD + "Medieval Lords");
		
		}
		if (i == 18) i = 0;
		p.sendScoreboard();
	}
	
}
