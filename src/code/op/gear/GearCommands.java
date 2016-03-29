package code.op.gear;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GearCommands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cg")) {
			try {
				if (args[0] == null) return true;
			} catch (Exception ex) {
				return true;
			}
			if (args[0].equalsIgnoreCase("store")) {
				if (!GearHandler.isEnableStore()) return true;
				try {
					int page = Integer.parseInt(args[1]);
					GearHandler.showGear((Player) sender, page);
					return true;
				} catch (NumberFormatException | ArrayIndexOutOfBoundsException ez) {
					GearHandler.showGear((Player) sender, 1);
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("buy")) {
				if (!GearHandler.isEnableStore()) return true;
				try {
					if (GearHandler.getItem(args[1]) != null) {
						GearHandler.buyGear((Player) sender, args[1]);
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "No item matches the inserted buy name!");
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException ez) {
					sender.sendMessage(ChatColor.RED + "Please insert the buy name of the item.");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("money") && sender.hasPermission("carbyne.admin")) {
				((Player) sender).getInventory().addItem(GearHandler.getMoney());
			}
		}
		return true;
	}

}
