package code.op.skill.crehop;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import code.op.CPManager;
import code.op.CarbynePlayer;

public class CrehopListener implements Listener {

	private CPManager cpm;
	
	public CrehopListener(CPManager cpm) {
		this.cpm = cpm;
	}
	
	@EventHandler
	public void onFlightToggle(PlayerToggleFlightEvent e) {
		if (e.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase("world")) {
			CarbynePlayer cp;
			if ((cp = cpm.getPlayer(e.getPlayer())) != null && cp.getDjcd() == 0)  {
				if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
					e.setCancelled(true);
					SuperJump.Jump(e.getPlayer(), 0.9);
					cp.setDjcd(10);
					e.getPlayer().setFlying(false);
				}
			}
		}
		
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		CarbynePlayer cp = cpm.getPlayer(e.getPlayer());
		if (cp != null) {
			cp.setWorld(e.getPlayer().getWorld());
		}
	}
	
	@EventHandler
	public void worldChange(PlayerChangedWorldEvent e) {
		CarbynePlayer cp = cpm.getPlayer(e.getPlayer());
		if (cp != null) {
			cp.setWorld(e.getPlayer().getWorld());
		}
	}
	
}
