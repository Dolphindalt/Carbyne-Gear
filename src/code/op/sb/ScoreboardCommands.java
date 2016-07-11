package code.op.sb;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScoreboardCommands implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sb")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("toggle")) {
					Player player = (Player)sender;
					if (ScoreboardUpdater.disabled.contains(player)) {
						ScoreboardUpdater.disabled.remove(player);
					} else {
						ScoreboardUpdater.disabled.add(player);
					}
				}
			}
		}
		return true;
	}

	
	
}
