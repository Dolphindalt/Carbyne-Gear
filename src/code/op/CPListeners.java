package code.op;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CPListeners implements Listener {

	@SuppressWarnings("unused") //Will probably be used later.
	private Main plugin;
	private CPManager cpm;
	
	public CPListeners(Main plugin) {
		this.plugin = plugin;
		this.cpm = plugin.getCpm();
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		cpm.addPlayer(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerExit(PlayerQuitEvent e) {
		cpm.removePlayer(e.getPlayer());
	}
	
}
