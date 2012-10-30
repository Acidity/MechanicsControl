/*
    Copyright (C) 2012 Tyler O'Meara
    Email: OMeara.Tyler@gmail.com

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

package com.gmail.acidity410.MechanicsControl;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantListener implements Listener {

	/**
	 * Necessary to get the server on static methods.
	 */
	
	private MechanicsControl plugin;
	
	/**
	 * Passes the MechanicsControl instance for static methods.
	 * @param plugin MechanicsControl plugin being passed in.
	 */
	
	public EnchantListener(MechanicsControl plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEnchant(EnchantItemEvent event) {
		//Makes sure that the blocking enchants has been enabled in the config.
		if(!plugin.getConfig().getBoolean("Enchants.BlockEnchants")) {
			return;
		}
		
		//Checks all assigned enchants for being blocked and removes them as necessary
		for(Enchantment x : event.getEnchantsToAdd().keySet()) {
			if(plugin.getConfig().getIntegerList("Enchants." + x.getName()).contains(event.getEnchantsToAdd().get(x))) {
				event.getEnchantsToAdd().remove(x);
				//If there are no allowed enchantments, cancel the event so the palyer doesn't lose levels
				if(event.getEnchantsToAdd().size() == 0) {
					event.setCancelled(true);
				}
			}
		}
	}
}
