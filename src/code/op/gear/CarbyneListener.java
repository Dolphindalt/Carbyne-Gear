package code.op.gear;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;

import code.op.CPManager;
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
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onDamage(EntityDamageEvent e, EntityDamageByEntityEvent ee) {
		if (ee.isCancelled()) return;
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			double ap = 0;
			for (ItemStack is : p.getInventory().getArmorContents()) {
				if (is.getType().equals(Material.AIR)) continue;
				try {
					ap = ap + Double.parseDouble(is.getItemMeta().getLore().get(2).split("\\s+")[2]);
				} catch (NullPointerException ex) {
					// If this gets called, the armor is invalid.
				}
			}
			if (ap != 0) {
				double d = 0;
				d = (e.getDamage() - (e.getDamage()*ap) - (e.getOriginalDamage(DamageModifier.RESISTANCE)*-1));
			if (d >= p.getHealth()) {
				p.setHealth(0);
				return;
			}
			if (p.isBlocking()) {
			p.damage(d);
			} else {
				d = 0.32/d;
				p.damage(d);
			}
			e.setDamage(0);
			}
		}
	}
	
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
					cpm.getCPByName(attacked.getName()).getBoard().getObjective(DisplaySlot.SIDEBAR).getScore(parseArmorType(is.getType())).setScore((int) durability);
				} else {
					cpm.getCPByName(attacked.getName()).getBoard().getObjective(DisplaySlot.SIDEBAR).getScore(parseArmorType(is.getType())).setScore((int) durability);
					attacked.getInventory().remove(is);
					attacked.playSound(attacked.getLocation(), Sound.ITEM_BREAK, 1, 1);
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
					damager.playSound(damager.getLocation(), Sound.ITEM_BREAK, 1, 1);
				}
			}
		}
	}
	
	/**
	 * If a player right clicks air and is sneaking, they are checked if they can use a special. If they can they use the special.
	 * @param e
	 */
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		if(!e.getPlayer().isSneaking()) return;
		ItemStack is = e.getPlayer().getInventory().getItemInHand();
		if (is == null) return;
		CarbyneWeapon cw = GearHandler.getCarbyneWeapon(is);
		if (cw == null) return;
		if (cw.getSpecialName() != null && cw.getSpecialCost() != -1) {
			if(cpm.containsPlayer(e.getPlayer())) {
				cw.useSpecial(cw, cpm.getCPByName(e.getPlayer().getName()));
			}
		}
	}
	
	/**
	 * On an entities death, if the killer is a player, his special charges will increase and update.
	 * @param e
	 */
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		LivingEntity le = e.getEntity();
		if (le.getKiller() instanceof Player) {
			ItemStack is = le.getKiller().getItemInHand();
			if (GearHandler.isCarbyneWeapon(is)) {
				CarbyneWeapon cw = GearHandler.getCarbyneWeapon(is);
				String[] chargeLine = is.getItemMeta().getLore().get(2).split("\\s+");
				int am = Integer.parseInt(chargeLine[1]);
				if (am == cw.getSpecialCost()) return;
				am++;
				Namer.setLore(is, "Charge: " + am + " / " + cw.getSpecialCost(), 2);
				cw.setSpecialCharges(am);
			}
			/*if (cpm.containsPlayer(killer)) {
				CarbynePlayer kp = cpm.getCPByName(killer.getName());
				if(kp.getSpecialCount() == 50) return;
				kp.setSpecialCount(kp.getSpecialCount() + 1);
			}*/
		}
	}
	
	/**
	 * This method checks if the player is crafting a minecraft armor peice (excluding chain). The peice will be replaced
	 * by a MinecraftArmor getItem() itemstack. If the ItemStack is null a warning will display.
	 * @param e the craft item event in question
	 */
	@EventHandler
	public void onCraftItem(CraftItemEvent e) {
		try {
			if (MinecraftArmor.defaultArmors.get(e.getCurrentItem().getType()).getType().equals(e.getCurrentItem().getType())) {
				e.setCurrentItem(MinecraftArmor.defaultArmors.get(e.getCurrentItem().getType()));
			}
			} catch (NullPointerException ex) {
				//This will catch if it is not armor
			}
	}
	
	/**
	 * Set the players scoreboard for armor durability on inventory close.
	 * @param e
	 */
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		double[] a = {0,0,0,0};
		ItemStack[] ac = e.getPlayer().getInventory().getArmorContents();
		for(int i = 0; i < 4; i++) {
			if (ac[i].equals(Material.AIR)) {
				continue;
			}
			if (MinecraftArmor.defaultArmors.containsKey(ac[i]) || GearHandler.isCarbyneArmor(ac[i])) {
				a[i] = Double.parseDouble(ac[i].getItemMeta().getLore().get(1).split("\\s+")[1]);
			}
			continue;
		}
		cpm.getCPByName(e.getPlayer().getName()).updateArmorDurability(a[0], a[1], a[2], a[3]);
	}
	
	public String parseArmorType(Material material) {
		if(material.toString().contains("HELMET")) {
			return "Helmet";
		} else if(material.toString().contains("CHESTPLATE")) {
			return "Chestplate";
		} else if(material.toString().contains("LEGGINGS")) {
			return "Leggings";
		} else if (material.toString().contains("BOOTS")) {
			return "Boots";
		} else return null;
	}
	
}
