package code.op.duel;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuelCommands implements CommandExecutor {

	private DuelZone z;
	
	public DuelCommands(DuelZone z) {
		this.z = z;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("duel")) {
			if (DuelInstance.z == null) return true;
			try {
				if (args[0].equalsIgnoreCase("join")) {
					z.joinDuel((Player) sender);
					return true;
				}
			} catch (Exception ex) {
				sender.sendMessage(ChatColor.RED + "Invalid arguments!");
				return true;
			}
		}
		return true;
	}
	
}
