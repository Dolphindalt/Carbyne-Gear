package code.op.skill.crehop;

import org.bukkit.GameMode;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class CrehopListener implements Listener {

	public void onFlightToggle(PlayerToggleFlightEvent e) {
		if (e.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase("world")) {
			
			if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
				SuperJump.Jump(e.getPlayer(), 0.9);
				e.setCancelled(true);
				e.getPlayer().setAllowFlight(false);
				e.getPlayer().setFlying(false);
			}
		}
		
	}
	
}
