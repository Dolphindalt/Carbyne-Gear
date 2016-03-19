package code.op.skill;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import code.op.utils.CustomEntityFirework;

public class SummonHealing  extends Special {

	private final FireworkEffect.Builder builder = FireworkEffect.builder();
	private final FireworkEffect fe = builder.flicker(true).with(Type.BURST).withColor(Color.MAROON).withColor(Color.RED).trail(false).build();
	
	@Override
	public boolean run(Player caster) {
		for (Entity e : caster.getNearbyEntities(10, 10, 10)) {
			if (!(e instanceof Player)) continue;
			Player p = (Player) e;
			CustomEntityFirework.spawn(e.getLocation(), fe);
			p.setHealth(p.getHealth() + (p.getMaxHealth() - p.getHealth()));
		}
		return true;
	}

	@Override
	public String getName() {
		return "SummonHealing";
	}

}
