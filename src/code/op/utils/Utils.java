package code.op.utils;

import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownyUniverse;

public class Utils {

	public static Resident getResident(Player player) {
		try {
			return TownyUniverse.getDataSource().getResident(player.getName());
		} catch (NotRegisteredException e) {
			return null;
		}
	}
	
}
