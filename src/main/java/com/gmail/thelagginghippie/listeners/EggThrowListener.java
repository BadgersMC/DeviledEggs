package com.gmail.thelagginghippie.listeners;

import com.gmail.thelagginghippie.DeviledEggs;
import com.gmail.thelagginghippie.api.DeviledEggThrowEvent;
import com.gmail.thelagginghippie.utils.EggUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EggThrowListener implements Listener {

	private final DeviledEggs plugin;

	public EggThrowListener(DeviledEggs plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			ItemStack item = event.getItem();
			if (item != null && EggUtils.isDeviledEgg(item)) {
				// Right-clicked with a Deviled Egg, handle the interaction
				Player player = event.getPlayer();

				// Trigger your custom event or handle the interaction directly here
				DeviledEggThrowEvent deviledEggThrowEvent = new DeviledEggThrowEvent(player, true);
				plugin.getServer().getPluginManager().callEvent(deviledEggThrowEvent);

				// Remove one egg from the player's inventory
				if (player.getGameMode() != GameMode.CREATIVE) {
					player.getInventory().removeItem(new ItemStack(Material.EGG, 1));
				}
			}
		}
	}







	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		// Check if the spawned entity is a chicken
		if (event.getEntityType() == EntityType.CHICKEN) {
			// Check if the spawn reason is an egg
			if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.EGG) {
				// Cancel the spawn event for chickens spawned from eggs
				event.setCancelled(true);
			}
		}
	}
}