package code.op.gear;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import code.op.utils.HiddenStringUtils;
import code.op.utils.Namer;

public class MinecraftWeapons extends CarbyneGear {

	public static HashMap<Material, ItemStack> weapons = new HashMap<Material, ItemStack>();
	
	private String material = null;
	
	@Override
	public boolean load(ConfigurationSection cs, String type, Color color) {
		material = cs.getName();
		if((this.type = type) == null) return false;
		if((durability = cs.getInt(type + ".Durability")) == -1) return false;
		this.lore = new ArrayList<String>();
		this.lore.add(0, ChatColor.RED + "Durability: " + cs.getInt(type + ".Durability"));
		this.lore.add(0, HiddenStringUtils.encodeString(code));

		return true;
	}

	@Override
	public ItemStack getItem() {
		ItemStack is = new ItemStack(Material.getMaterial(((material + "_" + type).toUpperCase())));
		Namer.setLore(is, lore);
		
		return is;
	}

	
	
}
