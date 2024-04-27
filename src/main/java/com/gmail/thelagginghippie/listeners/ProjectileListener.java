package com.gmail.thelagginghippie.listeners;

import com.gmail.thelagginghippie.api.EggAPI;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileListener implements Listener {

	private final EggAPI eggAPI;

	public ProjectileListener(double explosionPower) {
		this.eggAPI = new EggAPI(explosionPower);
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		Projectile projectile = event.getEntity();
		if (projectile instanceof Egg) {
			if (projectile.getShooter() instanceof Player) {
				Player player = (Player) projectile.getShooter();
				// Call the method from the API class
				eggAPI.handleEggExplosion(player, projectile);
			}
		}
	}

	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent event) {
		Projectile projectile = event.getEntity();
		if (projectile instanceof Egg) {
			//Adjust the fire ticks as needed. 20 ticks = 1 sec
			projectile.setFireTicks(100);
		}
	}
}