package com.gmail.thelagginghippie.commands;

import com.gmail.thelagginghippie.DeviledEggs;
import com.gmail.thelagginghippie.utils.EggUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	private DeviledEggs plugin;

	// Constructor to receive the instance of DeviledEggs plugin
	public Commands(DeviledEggs plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("de")) {
			if (args.length == 0) {
				sender.sendMessage("§aUsage: /de give <player> <amount> OR /de reload");
				return true;
			}

			if (args[0].equalsIgnoreCase("give")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage("§cOnly players can use this command.");
					return true;
				}

				Player player = (Player) sender;
				if (args.length < 3) {
					sender.sendMessage("§aUsage: /de give <player> <amount>");
					return true;
				}

				Player target = plugin.getServer().getPlayer(args[1]);
				if (target == null) {
					sender.sendMessage("§cPlayer not found.");
					return true;
				}

				int amount;
				try {
					amount = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					sender.sendMessage("§cInvalid amount.");
					return true;
				}

				EggUtils.giveDeviledEgg(amount, target);
				sender.sendMessage("§aGave " + target.getName() + " " + amount + " DeviledEggs.");
				return true;
			}

			if (args[0].equalsIgnoreCase("reload")) {
				plugin.reloadConfig();
				plugin.loadConfig();
				sender.sendMessage("§aConfiguration reloaded.");
				return true;
			}
		}

		return false;
	}
}
