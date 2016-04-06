package code.op.duel;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import code.op.Main;

public class DuelInstance {

	public static DuelZone z;
	
	public static void loadDuel(FileConfiguration fc) {
		ConfigurationSection cs = fc.getConfigurationSection("Duel");
		if (!cs.getBoolean(".Enable")) {
			return;
		}
		World world = Bukkit.getWorld(cs.getString(".World"));
		Location spawn1 = new Location(world, cs.getInt(".Spawn1.X"), cs.getInt(".Spawn1.Y"), cs.getInt(".Spawn1.Z"));
		Location spawn2 = new Location(world, cs.getInt(".Spawn2.X"), cs.getInt(".Spawn2.Y"), cs.getInt(".Spawn2.Z"));
		Location wait1 = new Location(world, cs.getInt(".Wait1.X"), cs.getInt(".Wait1.Y"), cs.getInt(".Wait1.Z"));
		Location wait2 = new Location(world, cs.getInt(".Wait2.X"), cs.getInt(".Wait2.Y"), cs.getInt(".Wait2.Z"));
		Location end = new Location(world, cs.getInt(".End.X"), cs.getInt(".End.Y"), cs.getInt(".End.Z"));
		if (spawn1 == null || spawn2 == null || wait1 == null || wait2 == null || end == null) {
			Main.instance.logger.warning("Duel failed to load, invalid location!");
			return;
		}
		z = new DuelZone(spawn1, spawn2, wait1, wait2, end);
	}
	
}
