package code.op.gear;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import code.op.CPManager;
import code.op.CarbynePlayer;
import code.op.Main;
import code.op.utils.Namer;

public class CarbyneListener implements Listener {

	@SuppressWarnings("unused") //Will probably be used later.
	private Main plugin;
	private CPManager cpm;
	
	public CarbyneListener(Main plugin) {
		this.plugin = plugin;
		this.cpm = plugin.getCpm();
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			double ap = 0;
			for (ItemStack is : p.getInventory().getArmorContents()) {
				if (!GearHandler.isCarbyneArmor(is)) {
					Material type = is.getType();
					if (type.equals(Material.DIAMOND_HELMET) || type.equals(Material.DIAMOND_CHESTPLATE) ||
						type.equals(Material.DIAMOND_LEGGINGS) || type.equals(Material.DIAMOND_BOOTS)) {
						ap = ap + 0.20;
					} else if (type.equals(Material.LEATHER_HELMET) || type.equals(Material.LEATHER_CHESTPLATE) ||
							type.equals(Material.LEATHER_LEGGINGS) || type.equals(Material.LEATHER_BOOTS)) {
						ap = ap + 0.07;
					} else {
						if (type != Material.AIR) {
							ap = ap + 0.15;
						}
					}
					continue;
				}
				ap = ap + Double.parseDouble(is.getItemMeta().getLore().get(2).split("\\s+")[2]);
 			}
			if (ap != 0) {
				double d = 0;
				d = (e.getDamage() - (e.getDamage()*ap) - (e.getOriginalDamage(DamageModifier.RESISTANCE)*-1));
			Bukkit.broadcastMessage("Damage for " + e.getEntity() + ": " + d);
			if (d >= p.getHealth()) {
				p.setHealth(0);
				return;
			}
			p.damage(d);
			e.setDamage(0);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamagebyEntity(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player attacked = (Player) e.getEntity();
			for(ItemStack is : attacked.getInventory().getArmorContents()) {
				//Update health
				if (cpm.containsPlayer(attacked)) {
					cpm.getCPByName(attacked.getName()).getObj().getScore("Health").setScore((int) attacked.getHealth());
				}
				//Apply effects
				if (is.getType().equals(Material.LEATHER_CHESTPLATE)) {
					CarbyneArmor ca = GearHandler.getCarbyneArmor(is);
					try {
						if (ca.getEffect() != null && ca.getChance() != -1) {
							ca.applyDefensiveEffectChance(attacked);
						}
					} catch (NullPointerException ex) {
						
					}
				}
				//Lower armor durability after defense
				CarbyneGear cg = GearHandler.getCarbyneGear(is);
				if(cg == null) continue;
				double durability = GearHandler.getDurability(is);
				if(!(durability == 0)) {
					is.setDurability((short) 0);
					durability--;
					List<String> old = is.getItemMeta().getLore();
					old.remove(1);
					old.add(1, ChatColor.RED + "Durability: " + durability);
					Namer.setLore(is, old);
				} else {
					attacked.getInventory().remove(is);
					attacked.playSound(attacked.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
				}
			}
		}
		if (e.getDamager() instanceof Player) {
			Player damager = (Player) e.getDamager();
			ItemStack is = damager.getItemInHand();
			CarbyneWeapon cw = GearHandler.getCarbyneWeapon(is);
			if (!(cw == null)) {
				//Apply effects
				try {
					if (cw.getEffect() != null && cw.getChance() != -1) {
						cw.applyOffensiveEffectChance(damager);
					}
				} catch (NullPointerException exx) {
					
				}
				//Lower weapons durability after attack
				double durability = GearHandler.getDurability(is);
				if(!(durability == 0)) {
					is.setDurability((short) 0);
					durability--;
					List<String> old = is.getItemMeta().getLore();
					old.remove(1);
					old.add(1, ChatColor.RED + "Durability: " + durability);
					Namer.setLore(is, old);
				} else {
					damager.getInventory().remove(is);
					damager.playSound(damager.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
				}
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		if(!e.getPlayer().isSneaking()) return;
		ItemStack is = e.getPlayer().getInventory().getItemInMainHand();
		if (is == null) return;
		CarbyneWeapon cw = GearHandler.getCarbyneWeapon(is);
		if (cw == null) return;
		if (cw.getSpecialName() != null && cw.getSpecialCost() != -1) {
			if(cpm.containsPlayer(e.getPlayer())) {
				cw.useSpecial(cpm.getCPByName(e.getPlayer().getName()));
			}
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		LivingEntity le = e.getEntity();
		if (le.getKiller() instanceof Player) {
			Player killer = le.getKiller();
			if (cpm.containsPlayer(killer)) {
				CarbynePlayer kp = cpm.getCPByName(killer.getName());
				if(kp.getSpecialCount() == 50) return;
				kp.setSpecialCount(kp.getSpecialCount() + 1);
			}
		}
	}
	
}
