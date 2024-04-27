package com.gmail.thelagginghippie;

import com.gmail.thelagginghippie.api.EggAPI;
import com.gmail.thelagginghippie.commands.Commands;
import com.gmail.thelagginghippie.listeners.EggThrowListener;
import com.gmail.thelagginghippie.listeners.ProjectileListener;
import com.gmail.thelagginghippie.utils.Log;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class DeviledEggs extends JavaPlugin {

	@Getter
	private static DeviledEggs instance;

	@Override
	public void onEnable() {
		instance = this;
		Log.log("&aDeviledEggs Loaded Successfully!");
		loadConfig();
		Log.log("&aConfiguration files have been loaded!");
		getCommand("de").setExecutor(new Commands(this));
		Log.log("&aSuccessfully registered the /de command and its sub-commands!");
		double explosionPower = getConfig().getDouble("explosion_power", 4.0);
		Bukkit.getPluginManager().registerEvents(new ProjectileListener(explosionPower), this);
		Log.log("&aSuccessfully registered ProjectileListener.");

		Bukkit.getPluginManager().registerEvents(new EggThrowListener(this), this);
		Log.log("&aSuccessfully registered EggThrowListener.");
	}

	@Override
	public void onDisable() {
		saveConfig();
	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		double explosionPower = getConfig().getDouble("explosion_power", 4.0);
		Log.log("&aExplosion Power:§c " + explosionPower);
		new EggAPI(explosionPower);
	}
}