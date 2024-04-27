package com.gmail.thelagginghippie.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class Log{
	private static final ConsoleCommandSender console = Bukkit.getConsoleSender();
	public static void log(String message) {
			console.sendMessage("[DeviledEggs] " + ChatColor.translateAlternateColorCodes('&', message));
	}
}
