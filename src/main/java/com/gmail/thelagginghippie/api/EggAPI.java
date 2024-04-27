package com.gmail.thelagginghippie.api;

import com.gmail.thelagginghippie.utils.EggUtils;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

public class EggAPI {

	private final double explosionPower;

	public EggAPI(double explosionPower) {
		this.explosionPower = explosionPower;
	}

	public void handleEggExplosion(Player player, Projectile projectile) {
		ItemStack itemInHand = player.getItemInHand();
		if (EggUtils.isDeviledEgg(itemInHand)) {
			projectile.getWorld().createExplosion(projectile.getLocation(), (float) explosionPower);
		}
	}
}
