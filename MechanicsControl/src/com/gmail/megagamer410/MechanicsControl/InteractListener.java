/*
    Copyright (C) 2012  megagamer410
    Email: megagamer410@gmail.com

    This file is part of MechanicsControl.

    MechanicsControl is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MechanicsControl is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with MechanicsControl.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.gmail.megagamer410.MechanicsControl;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;

public class InteractListener implements Listener {
	/**
	 * Necessary to get the server on static methods.
	 */
	
	private MechanicsControl plugin;
	
	/**
	 * Passes the MechanicsControl instance for static methods.
	 * @param plugin MechanicsControl plugin being passed in.
	 */
	
	public InteractListener(MechanicsControl plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		final PlayerInteractEvent Event = event;
		
		//If not right clicking air, do nothing
		if(!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		//If the event doesn't involve an item
		if(!event.hasItem()) {
			return;
		}
		//If the item is null
		if(event.getItem() == null) {
			return;
		}
		//If the item isn't a potion
		if(event.getItem().getType() != Material.POTION) {
			return;
		}
		//Removes the potion effects
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				//If the player currently has potion effects
				if(Event.getPlayer().getActivePotionEffects() == null) {
					return;
				}
				//If the player has less than or eqaul to 0 potion effects
				if(Event.getPlayer().getActivePotionEffects().size() <= 0) {
					return;
				}
				
				for(PotionEffect x : Event.getPlayer().getActivePotionEffects()) {
					if(plugin.getConfig().getBoolean("Potions." + x.getType().getName())) {
						Event.getPlayer().removePotionEffect(x.getType());
					}
				}
			}
		}, 40);
	}
}
