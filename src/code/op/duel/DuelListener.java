package code.op.duel;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import code.op.Main;

public class DuelListener implements Listener {

	DuelZone z;
	
	public DuelListener(DuelZone z) {
		this.z = z;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.equals(z.getP1()) || p.equals(z.getP2())) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
					public void run() {
						z.resetDuel(p);
					}	
				},200L);
			}
		}
	}
	
	@EventHandler
	public void onLogout(PlayerQuitEvent e) {
		if (e.getPlayer().equals(z.getP1()) || e.getPlayer().equals(z.getP2())) {
			Player quitter = e.getPlayer();
			quitter.damage(999.0);
		}
	}
	
}
