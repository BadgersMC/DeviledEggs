package com.gmail.thelagginghippie.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class EggUtils {
	/**
	 * Creates and gives a DeviledEgg with custom metadata
	 *
	 * @param amount
	 * @return
	 */

	public static ItemStack giveDeviledEgg(int amount, Player player) {
		ItemStack deviledEgg = new ItemStack(Material.EGG, amount);
		ItemMeta meta = deviledEgg.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "[DeviledEgg]");
		meta.setLore(Arrays.asList(ChatColor.GRAY + "A throwable explosive egg. Great for houses!"));
		deviledEgg.setItemMeta(meta);

		// No need to set custom metadata here, it's encoded in the display name

		player.getInventory().addItem(deviledEgg);
		return deviledEgg;
	}


	/**
	 * Checks the items display name for "DeviledEgg"
	 *
	 * @param item
	 * @return
	 */
	public static boolean isDeviledEgg(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			ItemMeta meta = item.getItemMeta();
			if (meta.getDisplayName().equals(ChatColor.RED + "[DeviledEgg]")) {
				List<String> lore = meta.getLore();
				if (lore != null && lore.contains(ChatColor.GRAY + "A throwable explosive egg. Great for houses!")) {
					return true;
				}
			}
		}
		return false;
	}
}