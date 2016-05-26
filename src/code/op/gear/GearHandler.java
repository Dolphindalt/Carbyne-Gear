package code.op.gear;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import code.op.Main;
import code.op.utils.ColorUtils;
import code.op.utils.HiddenStringUtils;
import code.op.utils.Namer;

/*
 *  Credit for mostly this entire class to Frodenkvist
 */

public class GearHandler {

	private static List<CarbyneGear> gear = new ArrayList<CarbyneGear>();
	private static List<CarbyneGear> store = new ArrayList<CarbyneGear>();
	
	public static boolean addItem(CarbyneGear is) {
		return gear.add(is);
	}
	
	public static void load(FileConfiguration fc) {
		gear.clear();
		String c = new String();
		for(int i=0;i<10;++i) {
			switch(i)
			{
			case 0:
				c = "Black";
				break;
			case 1:
				c = "Red";
				break;
			case 2:
				c = "Green";
				break;
			case 3:
				c = "Blue";
				break;
			case 4:
				c = "White";
				break;
			case 5:
				c = "Purple";
				break;
			case 6:
				c = "Teal";
				break;
			case 7:
				c = "Pink";
				break;
			case 8:
				c = "Orange";
				break;
			case 9:
				c = "Yellow";
				break;
			}
			// Armor
			CarbyneGear cg = new CarbyneArmor();
			if (cg.load(fc.getConfigurationSection(c), "Helmet", ColorUtils.getColorByName(c))) {
				gear.add(cg);
			}
			cg = new CarbyneArmor();
			if (cg.load(fc.getConfigurationSection(c), "Chestplate", ColorUtils.getColorByName(c))) {
				gear.add(cg);
			}
			cg = new CarbyneArmor();
			if (cg.load(fc.getConfigurationSection(c), "Leggings", ColorUtils.getColorByName(c))) {
				gear.add(cg);
			}
			cg = new CarbyneArmor();
			if (cg.load(fc.getConfigurationSection(c), "Boots", ColorUtils.getColorByName(c))) {
				gear.add(cg);
			}
			// Each case
		}
		//Weapons
		for(int i=0;i < 5; ++i) {
			switch(i) {
				case 0:
					c = "Sword";
					break;
				case 1:
					c = "Axe";
					break;
				case 2:
					c = "Bow";
					break;
				case 3:
					c = "Hoe";
					break;
				case 4:
					c = "Spade";
					break;
			}
			for(int j=1;fc.contains("Weapons." + c + "." + j);++j) {
				CarbyneGear cg = new CarbyneWeapon();
				if(cg.load(fc.getConfigurationSection("Weapons"), c, Color.fromRGB(j))) {
					gear.add(cg);
				}
			}
		}
		
		/**
		 * This section loads the minecraft armor defaults, it exludes chain because it is not craftable
		 */
		ConfigurationSection cs = fc.getConfigurationSection("MinecraftArmor");
		for(String material : cs.getKeys(false)) {
			String type = new String();
			for(int i = 0; i < 4; i++) {
				switch(i) {
				case 0:
					type = "Helmet";
					break;
				case 1:
					type = "Chestplate";
					break;
				case 2: 
					type = "Leggings";
					break;
				case 3:
					type = "Boots";
					break;
				}
				CarbyneGear ma = new MinecraftArmor();
				if(ma.load(cs.getConfigurationSection(material), type, null)) {
					MinecraftArmor.defaultArmors.put(ma.getItem().getType(), ma.getItem());
				
				} else Main.instance.logger.log(Level.SEVERE, "[CarbyneGear]: MinecraftArmor configuration has falied to load " + cs + "." + type + "!");
			}
		}
		Main.instance.logger.info(MinecraftArmor.defaultArmors.toString());
		Collections.sort(gear, new CarbyneGearComparator());
		
		//Sort out hiddens into new list
		Iterator<CarbyneGear> itr = gear.iterator();
		while(itr.hasNext()) {
			CarbyneGear cur = itr.next();
			if(!cur.hidden) store.add(cur);
		}
		
		Main.instance.logger.info(gear.size() + " carbyne gear loaded");
	}
	
	/*
	 *  Gear getters and setters below...
	 */
	
	public static ItemStack getItem(String displayName) {
		for (CarbyneGear cg : gear) {
			if(cg.getDisplayName().equalsIgnoreCase(displayName)) {
				return cg.getItem();
			}
		}
		return null;
	}
	
	public static CarbyneGear getCarbyneGear(ItemStack is) {
		if(is.getItemMeta() == null) return null;
		List<String> lore = Namer.getLore(is);
		if(lore == null || lore.isEmpty()) return null;
		for(CarbyneGear eg : gear) {
			if(eg.getCode().equalsIgnoreCase(HiddenStringUtils.extractHiddenString(lore.get(0)))) {
				return eg;
			}
		}
		return null;
	}
	
	public static double getDurability(ItemStack is)
	{
		String key = Namer.getLore(is).get(1);
		if(key.contains("Durability:")) {
			return Double.valueOf(key.split(" ")[1]);
		}
		return -1;
	}

	public static CarbyneArmor getCarbyneArmor(ItemStack is) {
		if (is.getItemMeta() == null) return null;
		List<String> lore = is.getItemMeta().getLore();
		if(lore == null || lore.isEmpty()) return null;
		for(CarbyneGear cg : gear) {
			if(!(cg instanceof CarbyneArmor)) continue;
				if (cg.getName().equalsIgnoreCase(is.getItemMeta().getDisplayName().replace('§', '&'))) {
					if (cg.getCode().equalsIgnoreCase(HiddenStringUtils.extractHiddenString(lore.get(0)))) {
						return (CarbyneArmor)cg;
					}
				}
			}
		return null;
	}
	
	public static CarbyneWeapon getCarbyneWeapon(ItemStack is) {
		if (is.getItemMeta() == null) return null;
		List<String> lore = is.getItemMeta().getLore();
		if(lore == null || lore.isEmpty()) return null;
		for (CarbyneGear cg : gear) {
			if (!(cg instanceof CarbyneWeapon)) continue;
				if (cg.getName().equalsIgnoreCase(is.getItemMeta().getDisplayName().replace('§', '&'))) {
					if (cg.getCode().equalsIgnoreCase(HiddenStringUtils.extractHiddenString(lore.get(0)))) {
						return (CarbyneWeapon)cg;
					}
				}
			}
		return null;
	}
	
	public static boolean isCarbyneWeapon(ItemStack is) {
		if (is.getItemMeta() == null) return false;
		List<String> lore = is.getItemMeta().getLore();
		if(lore == null || lore.isEmpty()) return false;
		for(CarbyneGear cg : gear) {
			if(!(cg instanceof CarbyneWeapon)) continue;
				if (cg.getName().equalsIgnoreCase(is.getItemMeta().getDisplayName().replace('§', '&'))) {
					if(cg.getCode().equalsIgnoreCase(HiddenStringUtils.extractHiddenString(lore.get(0)))) {
						return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isCarbyneArmor(ItemStack is) {
		if (is.getItemMeta() == null) return false;
		List<String> lore = is.getItemMeta().getLore();
		if(lore == null || lore.isEmpty()) return false;
		for(CarbyneGear cg : gear) {
			if(!(cg instanceof CarbyneArmor)) continue;
				if (cg.getName().equalsIgnoreCase(is.getItemMeta().getDisplayName().replace('§', '&'))) {
					if(cg.getCode().equalsIgnoreCase(HiddenStringUtils.extractHiddenString(lore.get(0)))) {
							return true;
				}
			}
		}
		return false;
	}
	
	// STORE RELATED STUFF BELOW
	
	private static String name = new String();
	private static List<String> lore;
	private static String moneyCode = new String();
	private static boolean enableStore;
	private static Material moneyItem;
	
	public static boolean loadStoreOptions(FileConfiguration cs) {
		enableStore = cs.getBoolean("EnableStore");
		if (!enableStore) return false;

		if (cs.getString("MoneyItem.Material") == null && Material.getMaterial(cs.getString("MoneyItem.Material")) == null) return false;
		if (cs.getString("MoneyItem.Name") == null) return false;
		if (cs.getStringList("MoneyItem.Lore") == null) return false;
		if (cs.getString("MoneyItem.MoneyCode") == null) return false;

		moneyCode = cs.getString("MoneyItem.MoneyCode");
		moneyItem = Material.getMaterial(cs.getString("MoneyItem.Material"));
		name = cs.getString("MoneyItem.Name");
		lore = cs.getStringList("MoneyItem.Lore");
		lore.add(0, HiddenStringUtils.encodeString(moneyCode));
		
		Main.instance.logger.info("Store loaded");
		return true;
	}
	
	public static ItemStack getMoney() {
		ItemStack is = new ItemStack(moneyItem);
		
		Namer.setName(is, name);
		Namer.setLore(is, lore);

		return is;
	}
	
	public static void buyGear(Player buyer, String buyName) {
		ItemStack is = getItem(buyName);
		CarbyneGear cg = getCarbyneGear(is);
		if (buyer.getInventory().firstEmpty() == -1) {
			buyer.sendMessage(ChatColor.RED + "Your invnetory is full!");
			return;
		}
		if (buyer.hasPermission("carbyne.admin") || buyer.isOp()) {
			if (buyer.getInventory().firstEmpty() != -1) {
				buyer.getInventory().addItem(is);
				return;
			}
		}
		//Normal Player Buy
		try {
			ItemStack money = buyer.getInventory().getItemInHand();
			if (moneyCode.equals(HiddenStringUtils.extractHiddenString(buyer.getInventory().getItemInHand().getItemMeta().getLore().get(0)))) {
				if (money.getAmount() >= cg.getCost()) {
					if (money.getAmount() == cg.getCost()) {
						buyer.getInventory().remove(money);
					} else {
						money.setAmount(money.getAmount() - cg.getCost());
					}
					buyer.getInventory().addItem(is);
					buyer.sendMessage(ChatColor.GREEN + "Purchase complete!");
					return;
				} else {
					buyer.sendMessage(ChatColor.RED + "Not enough currency!");
					return;
				}
			} else {
				buyer.sendMessage(ChatColor.RED + "This item is not valid currency!");
				return;
			}
		} catch (NullPointerException ez) {
			buyer.sendMessage(ChatColor.RED + "Make sure you have the right currency in your hand!");
			return;
		}
	}
	
	public static void showGear(Player p, int page) {
		if(store.size() <= 0) {
			p.sendMessage(ChatColor.RED + "The store is empty!");
			return;
		}
		int size = store.size();
		int pages = size/8;
		if((size%8) != 0) ++pages;
		if(page > pages) page = pages;
		p.sendMessage(ChatColor.GREEN + "    " + ChatColor.RED + " Carbyne Gear " + ChatColor.GREEN + "    (pg. " + page + "/" + pages + ")");
		p.sendMessage(ChatColor.GREEN + "| " + ChatColor.AQUA + " Name " + ChatColor.GREEN + " | " + ChatColor.DARK_AQUA + " Buy Name " + ChatColor.GREEN + " | " + ChatColor.GRAY + " Cost " + ChatColor.GREEN + " |");
		for(int i = ((8*page)-8);i < (page*8);++i) {
			if(p.hasPermission("carbyne.admin")) {
				CarbyneGear cg = gear.get(i);
				p.sendMessage(ChatColor.GREEN + "- " + ChatColor.AQUA + Namer.removeTag(cg.getName()) + " " + ChatColor.DARK_AQUA + cg.getDisplayName() + " " + ChatColor.GRAY + cg.getCost());
				if((i+1) >= gear.size())
					break;
			}
			else {
				CarbyneGear cg = store.get(i);
				p.sendMessage(ChatColor.GREEN + "- " + ChatColor.AQUA + Namer.removeTag(cg.getName()) + " " + ChatColor.DARK_AQUA + cg.getDisplayName() + " " + ChatColor.GRAY + cg.getCost());
				if((i+1) >= store.size())
					break;
			}
		}
	}

	public static boolean isEnableStore() {
		return enableStore;
	}
	
	public List<CarbyneGear> getGear() {
		return gear;
	}
	
}
