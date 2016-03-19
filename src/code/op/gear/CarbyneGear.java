package code.op.gear;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public abstract class CarbyneGear {

	protected String name = new String();
	protected String displayName = new String();
	protected List<String> lore = new ArrayList<String>();
	protected String type = new String();
	protected final String code = new String("carbynegear");
	protected int durability = -1;
	protected boolean hidden = false;
	protected int cost;
	
	public abstract boolean load(ConfigurationSection cs, String type, Color color);
	
	public abstract ItemStack getItem();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<String> getLore() {
		return lore;
	}
	public void setLore(List<String> lore) {
		this.lore = lore;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public int getDurability() {
		return durability;
	}
	public void setDurability(int durability) {
		this.durability = durability;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
