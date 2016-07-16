package code.op.skill.crehop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillHandler {

	public static List<Player> getPlayers(int radius, LivingEntity mob)
	{
		List<Player> list = new ArrayList<Player>();
		List<Entity> near = mob.getNearbyEntities(radius, radius, radius);
		for(Entity check:near){
			if(check instanceof Player){
				{
					list.add((Player) check);
				}
			}
		}
		return list;
	}
	
}
