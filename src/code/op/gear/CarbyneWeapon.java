package code.op.gear;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import code.op.CarbynePlayer;
import code.op.skill.Special;
import code.op.skill.SpecialInstances;
import code.op.utils.HiddenStringUtils;
import code.op.utils.Namer;

/*
 *  Credit to Frodenkvist for some of this code.
 */

public class CarbyneWeapon extends CarbyneGear {

	private List<String> enchants = new ArrayList<String>();
	private String material = new String();
	private int specialCost = -1;
	private Special special;
	private int specialCharges = 0;
	private PotionEffect effect;
	private double chance;
	
	@Override
	public boolean load(ConfigurationSection cs, String material, Color color) {
		if ((this.material = material) == null) return false;
		if (!material.equalsIgnoreCase("Bow")) {
			if ((this.type = cs.getString(material + "." + color.asRGB() + ".Type")) == null) return false;
		}
		if((durability = cs.getInt(material + "." + color.asRGB() + ".Durability")) == -1) return false;
		if((lore = cs.getStringList(material + "." + color.asRGB() + ".Lore")) == null || lore.size() <= 0) return false;
		if((displayName = cs.getString(material + "." + color.asRGB() + ".DisplayName")) == null) return false;
		if((cost = cs.getInt(material + "." + color.asRGB() + ".Cost")) == -1) return false;
		
		if (cs.getString(material + "." + color.asRGB() + ".Special") != null) {
			this.special = getSpecial(cs.getString(material + "." + color.asRGB() + ".Special"));
			this.specialCost = cs.getInt(material + "." + color.asRGB() + ".SpecialCost");
		}
		
		this.name = cs.getString(material + "." + color.asRGB() + ".Name");
		this.displayName = cs.getString(material + "." + color.asRGB() + ".DisplayName");
		this.lore = cs.getStringList(material + "." + color.asRGB() + ".Lore");
		this.lore.add(0, ChatColor.AQUA + "Charge: 0 / " + specialCost);
		this.lore.add(0, ChatColor.RED + "Durability: " + cs.getInt(material + "." + color.asRGB() + ".Durability"));
		this.lore.add(0, HiddenStringUtils.encodeString(code));
		this.enchants = cs.getStringList(material + "." + color.asRGB() + ".Enchants");
		this.hidden = cs.getBoolean(material + "." + color.asRGB() + ".Hidden");
		this.cost = cs.getInt(material + "." + color.asRGB() + ".Cost");
		
		if (cs.getString(material + "." + color.asRGB() + ".OffensiveEffect") != null) {
			String offensiveEffect = cs.getString(material + "." + color.asRGB() + ".OffensiveEffect");

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
		ItemStack is = null;
		if(material.equalsIgnoreCase("sword"))
		{
			if(type.equalsIgnoreCase("wood"))
			{
				is = new ItemStack(Material.WOOD_SWORD);
			}
			else if(type.equalsIgnoreCase("stone"))
			{
				is = new ItemStack(Material.STONE_SWORD);
			}
			else if(type.equalsIgnoreCase("iron"))
			{
				is = new ItemStack(Material.IRON_SWORD);
			}
			else if(type.equalsIgnoreCase("gold"))
			{
				is = new ItemStack(Material.GOLD_SWORD);
			}
			else if(type.equalsIgnoreCase("diamond"))
			{
				is = new ItemStack(Material.DIAMOND_SWORD);
			}
		}
		else if(material.equalsIgnoreCase("axe"))
		{
			if(type.equalsIgnoreCase("wood"))
			{
				is = new ItemStack(Material.WOOD_AXE);
			}
			else if(type.equalsIgnoreCase("stone"))
			{
				is = new ItemStack(Material.STONE_AXE);
			}
			else if(type.equalsIgnoreCase("iron"))
			{
				is = new ItemStack(Material.IRON_AXE);
			}
			else if(type.equalsIgnoreCase("gold"))
			{
				is = new ItemStack(Material.GOLD_AXE);
			}
			else if(type.equalsIgnoreCase("diamond"))
			{
				is = new ItemStack(Material.DIAMOND_AXE);
			}
		}
		else if(material.equalsIgnoreCase("hoe"))
		{
			if(type.equalsIgnoreCase("wood"))
			{
				is = new ItemStack(Material.WOOD_HOE);
			}
			else if(type.equalsIgnoreCase("stone"))
			{
				is = new ItemStack(Material.STONE_HOE);
			}
			else if(type.equalsIgnoreCase("iron"))
			{
				is = new ItemStack(Material.IRON_HOE);
			}
			else if(type.equalsIgnoreCase("gold"))
			{
				is = new ItemStack(Material.GOLD_HOE);
			}
			else if(type.equalsIgnoreCase("diamond"))
			{
				is = new ItemStack(Material.DIAMOND_HOE);
			}
		}
		else if(material.equalsIgnoreCase("spade"))
		{
			if(type.equalsIgnoreCase("wood"))
			{
				is = new ItemStack(Material.WOOD_SPADE);
			}
			else if(type.equalsIgnoreCase("stone"))
			{
				is = new ItemStack(Material.STONE_SPADE);
			}
			else if(type.equalsIgnoreCase("iron"))
			{
				is = new ItemStack(Material.IRON_SPADE);
			}
			else if(type.equalsIgnoreCase("gold"))
			{
				is = new ItemStack(Material.GOLD_SPADE);
			}
			else if(type.equalsIgnoreCase("diamond"))
			{
				is = new ItemStack(Material.DIAMOND_SPADE);
			}
		}
		else if(material.equalsIgnoreCase("bow"))
		{
			is = new ItemStack(Material.BOW);
		}
		
		Namer.setName(is, name);
		Namer.setLore(is, lore);
		
		for(String s : enchants)
		{
			String[] split = s.split(",");
			if(split.length != 2)
				continue;
			if(material.equalsIgnoreCase("spade")){
				if(split[0].equalsIgnoreCase("durability"))
				{
					is.addUnsafeEnchantment(Enchantment.DURABILITY, Integer.valueOf(split[1]));
				}
			}
			else if(!material.equalsIgnoreCase("bow"))
			{
				if(split[0].equalsIgnoreCase("sharpness"))
				{
					is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, Integer.valueOf(split[1]));
				}
				else if(split[0].equalsIgnoreCase("ARTHROPODS"))
				{
					is.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, Integer.valueOf(split[1]));
				}
				else if(split[0].equalsIgnoreCase("undead"))
				{
					is.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, Integer.valueOf(split[1]));
				}
				else if(split[0].equalsIgnoreCase("fire"))
				{
					is.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, Integer.valueOf(split[1]));
				}
				else if(split[0].equalsIgnoreCase("loot"))
				{
					is.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, Integer.valueOf(split[1]));
				}
				else if(split[0].equalsIgnoreCase("knockback"))
				{
					is.addUnsafeEnchantment(Enchantment.KNOCKBACK, Integer.valueOf(split[1]));
				}
			}
			else
			{
				if(split[0].equalsIgnoreCase("damage"))
				{
					is.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, Integer.valueOf(split[1]));
				}
				else if(split[0].equalsIgnoreCase("fire"))
				{
					is.addUnsafeEnchantment(Enchantment.ARROW_FIRE, Integer.valueOf(split[1]));
				}
				else if(split[0].equalsIgnoreCase("infinite"))
				{
					is.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, Integer.valueOf(split[1]));
				}
				else if(split[0].equalsIgnoreCase("knockback"))
				{
					is.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, Integer.valueOf(split[1]));
				}
			}
		}
		return is;
	}

	public void applyOffensiveEffectChance(LivingEntity target) {
		if (Math.random() <= chance) {
			target.addPotionEffect(effect);
		}
	}
	
	public boolean useSpecial(CarbyneWeapon cw, CarbynePlayer p) {
		if(special == null) return false;
		if(getSpecialCost() == -1) return false;
		if(cw.getSpecialCharges() < this.getSpecialCost()) return false;
		if(!special.run(p.getP())) return false;
		cw.setSpecialCharges((cw.getSpecialCharges() - this.getSpecialCost()));
		for (Entity e : p.getP().getNearbyEntities(50, 50, 50)) {
			if (e instanceof Player) {
				((Player)e).sendMessage(ChatColor.AQUA + " .::::." + ChatColor.YELLOW + p.getName() + " has casted " + ChatColor.BOLD.toString() + ChatColor.RED + special.getName() + "! " + ChatColor.AQUA + ".::::.");
			}
		}
		return true;
 	}
	
	private Special getSpecial(String skillName) {
		if(skillName.equalsIgnoreCase("ForceBlast")) {
			return SpecialInstances.getForceBlast();
		} else if (skillName.equalsIgnoreCase("LightningStorm")) {
			return SpecialInstances.getLightningStorm();
		} else if (skillName.equalsIgnoreCase("ShadowSweep")) {
			return SpecialInstances.getShadowSweep();
		} else if (skillName.equalsIgnoreCase("SummonHealing")) {
			return SpecialInstances.getSummonHealing();
		}
		return null;
	}
	
	public String getSpecialName() {
		if (special != null) {
			return special.getName();
		}
		return null;
	}
	
	public int getSpecialCost() {
		return specialCost;
	}
	
	public void setSpecialCharges(int specialCharges) {
		this.specialCharges = specialCharges;
	}

	public int getSpecialCharges() {
		return specialCharges;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
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
