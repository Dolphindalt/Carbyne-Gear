package code.op.sb;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import code.op.utils.HiddenStringUtils;
import SB.SMHandler;
import SB.SMPlayer;

public class ScoreboardJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		SMPlayer p = SMHandler.getPlayer(e.getPlayer());
		p.setTitle(HiddenStringUtils.encodeString("_"));
		p.add(ChatColor.YELLOW + "Hand: " + 0, 0);
		p.add(ChatColor.GOLD + "B: " + 0, 1);
		p.add(ChatColor.GOLD + "L: " + 0, 2);
		p.add(ChatColor.GOLD + "C: " + 0, 3);
		p.add(ChatColor.GOLD + "H: " + 0, 4);
		p.add(ChatColor.GREEN + "Health: " + p.getPlayer().getHealth() + " / " + p.getPlayer().getMaxHealth(), 5);
		p.add(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------", 6);
		p.add(ChatColor.AQUA.toString() + ChatColor.BOLD + "Medieval Lords", 7);
		p.build();
		p.sendScoreboard();
	}
	
}
