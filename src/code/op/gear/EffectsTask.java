package code.op.gear;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.entity.Player;

import code.op.utils.ParticleEffect;

public class EffectsTask implements Runnable {

	public static List<Player> effectPlayers;
	
	public EffectsTask() {
		effectPlayers = new ArrayList<Player>();
	}
	
	public void run() {
		Iterator<Player> i = effectPlayers.iterator();
		while (i.hasNext()) {
			showParticles(i.next());
		}
	}

	public void showParticles(Player player) {
		ParticleEffect.PORTAL.display(0, 0, 0, 1, 10, player.getLocation(), 300);
	}
	
	public static void addPlayer(Player player) {
		if (!effectPlayers.contains(player)) effectPlayers.add(player);
	}
	
	public static void removePlayer(Player player) {
		if (effectPlayers.contains(player)) effectPlayers.remove(player);
	}
	
}
