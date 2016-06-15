package code.op.misc;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;

import code.op.utils.Utils;

public class DamageListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Player) {
			Resident damager = Utils.getResident((Player)e.getDamager());
			Resident attacked = Utils.getResident((Player)e.getEntity());
				try {
					Town defender = attacked.getTown();
					Town attacker = damager.getTown(); 
					if (attacker.equals(defender)) {
						e.setCancelled(true);
						return;
					}
					if (defender.getNation().equals(attacker.getNation())) {
						e.setCancelled(true);
						return;
					}
				} catch (NotRegisteredException e1) {
					return;
				}
			} else if (e.getDamager() instanceof Projectile) {
				ProjectileSource p = ((Projectile) e.getDamager()).getShooter();
				if (p instanceof Player) {
					Resident damager = Utils.getResident((Player) p);
					Resident attacked = Utils.getResident((Player)e.getEntity());
						try {
							Town defender = attacked.getTown();
							Town attacker = damager.getTown(); 
							if (attacker.equals(defender)) {
								e.setCancelled(true);
								return;
							}
							if (defender.getNation().equals(attacker.getNation())) {
								e.setCancelled(true);
								return;
							}
						} catch (NotRegisteredException e1) {
							return;
						}
				}
			}
		}
	}
	
}
