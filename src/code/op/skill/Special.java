package code.op.skill;

import org.bukkit.entity.Player;

/*
 *  Credit to Frodenkvist and Crehop for this class and subclasses
 */

public abstract class Special {
	
	public Special() {
		
	}
	
	public abstract boolean run(Player caster);
	
	public abstract String getName();
	
}
