package code.op.gear;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import code.op.utils.HiddenStringUtils;
import code.op.utils.Namer;

/*
 *  Credit to Frodenkvist for some of this code.
 */

public class CarbyneArmor extends CarbyneGear {

	private Color color;
	private List<String> enchants = new ArrayList<String>();
	private PotionEffect effect;
	private double chance = -1;
	private double armorRating = -1;
	
	@Override
	public boolean load(ConfigurationSection cs, String type, Color color) {
		if((this.type = type) == null) return false;
		if((this.color = color) == null) return false;
		if((lore = cs.getStringList(type + ".Lore")) == null || lore.size() <= 0) return false;
		if((durability = cs.getInt(type + ".Durability")) == -1) return false;
		if((displayName = cs.getString(type + ".DisplayName")) == null) return false;
		if((cost = cs.getInt(type + ".Cost")) == -1) return false;
		if((armorRating = cs.getDouble(type + ".ArmorRating")) == -1) return false;
		
		this.name = cs.getString(type + ".Name");
		this.displayName = cs.getString(type + ".DisplayName");
		this.lore = cs.getStringList(type + ".Lore");
		this.lore.add(0, "Armor Rating: " + armorRating);
		this.lore.add(0, ChatColor.RED + "Durability: " + cs.getInt(type + ".Durability"));
		this.lore.add(0, HiddenStringUtils.encodeString(code));
		this.enchants = cs.getStringList(type + ".Enchants");
		this.hidden = cs.getBoolean(type + ".Hidden");
		this.cost = cs.getInt(type + ".Cost");
		
		if (cs.getString(type + ".DefensiveEffect") != null && type.equalsIgnoreCase("chestplate")) {
			String offensiveEffect = cs.getString(type + ".DefensiveEffect");
			
			String[] split = offensiveEffect.split(",");
			if (split.length == 3) {
				effect = new PotionEffect(PotionEffectType.getByName(split[0]), Integer.parseInt(split[2]), 0);
				chance = Double.parseDouble(split[1]);
			}
		}
		
		return true;
	}

	@Override
	public ItemStack getItem() {
		String material = ("leather_" + type).toUpperCase();
		ItemStack is = new ItemStack(Material.getMaterial(material));
		
		LeatherArmorMeta lam = (LeatherArmorMeta) is.getItemMeta();
		lam.setColor(color);
		is.setItemMeta(lam);
		
		Namer.setName(is, name);
		Namer.setLore(is, lore);
		
		for(String s : enchants) {
			String[] split = s.split(",");
			if(split.length != 2)
				continue;
			if(split[0].equalsIgnoreCase("protection"))
			{
				is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Integer.valueOf(split[1]));
			}
			else if(split[0].equalsIgnoreCase("fireprotection"))
			{
				is.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, Integer.valueOf(split[1]));
			}
			else if(split[0].equalsIgnoreCase("featherfalling"))
			{
				if(!is.getType().equals(Material.LEATHER_BOOTS))
					continue;
				is.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, Integer.valueOf(split[1]));
			}
			else if(split[0].equalsIgnoreCase("blastprotection"))
			{
				is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, Integer.valueOf(split[1]));
			}
			else if(split[0].equalsIgnoreCase("projectileprotection"))
			{
				is.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, Integer.valueOf(split[1]));
			}
			else if(split[0].equalsIgnoreCase("respiration"))
			{
				if(!is.getType().equals(Material.LEATHER_HELMET))
					continue;
				is.addUnsafeEnchantment(Enchantment.OXYGEN, Integer.valueOf(split[1]));
			}
			else if(split[0].equalsIgnoreCase("aquaaffinity"))
			{
				if(!is.getType().equals(Material.LEATHER_HELMET))
					continue;
				is.addUnsafeEnchantment(Enchantment.WATER_WORKER, Integer.valueOf(split[1]));
			}
			if(split[0].equalsIgnoreCase("thorns"))
			{
				is.addUnsafeEnchantment(Enchantment.THORNS, Integer.valueOf(split[1]));
			}
			if(split[0].equalsIgnoreCase("unbreaking"))
			{
				is.addUnsafeEnchantment(Enchantment.DURABILITY, Integer.valueOf(split[1]));
			}
		}
		
		return is;
	}
	
	public void applyDefensiveEffectChance(LivingEntity target) {
		if (Math.random() <= chance) {
			target.addPotionEffect(effect);
		}
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public List<String> getEnchants() {
		return enchants;
	}
	public void setEnchants(List<String> enchants) {
		this.enchants = enchants;
	}

	public PotionEffect getEffect() {
		return effect;
	}

	public void setEffect(PotionEffect effect) {
		this.effect = effect;
	}

	public double getChance() {
		return chance;
	}

	public void setChance(double chance) {
		this.chance = chance;
	}
	
}
