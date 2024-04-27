package com.gmail.thelagginghippie.api;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DeviledEggThrowEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	@Getter
	private final Player player;
	private boolean isDeviledEgg;
	private boolean cancelled;

	public DeviledEggThrowEvent(Player player, boolean isDeviledEgg) {
		this.player = player;
		this.isDeviledEgg = isDeviledEgg;
	}

	public boolean isDeviledEgg() {
		return isDeviledEgg;
	}

	public void setDeviledEgg(boolean deviledEgg) {
		isDeviledEgg = deviledEgg;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}