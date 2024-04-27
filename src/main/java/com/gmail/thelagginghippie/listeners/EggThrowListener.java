package com.gmail.thelagginghippie.listeners;

import com.gmail.thelagginghippie.DeviledEggs;
import com.gmail.thelagginghippie.api.DeviledEggThrowEvent;
import com.gmail.thelagginghippie.utils.EggUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class EggThrowListener implements Listener {

	private final DeviledEggs plugin;

	public EggThrowListener(DeviledEggs plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (EggUtils.isDeviledEgg(event.getItem())) {
			// Set custom metadata on the egg entity
			if (event.getClickedBlock() != null) {
				// Drop the deviled egg item at the clicked block location
				int amount = 1; // Adjust the amount as needed
				Player player = event.getPlayer();
				event.getClickedBlock().getWorld().dropItem(event.getClickedBlock().getLocation(), EggUtils.giveDeviledEgg(amount, player));

				// Set metadata for the first dropped egg
				Item eggItem = event.getClickedBlock().getWorld().dropItem(event.getClickedBlock().getLocation(), EggUtils.giveDeviledEgg(amount, player));
				eggItem.setMetadata("DeviledEgg", new FixedMetadataValue(plugin, true));
				eggItem.setMetadata("ThrownEgg", new FixedMetadataValue(plugin, true));
			}
			DeviledEggThrowEvent deviledEggThrowEvent = new DeviledEggThrowEvent(event.getPlayer(), true);
			plugin.getServer().getPluginManager().callEvent(deviledEggThrowEvent);
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