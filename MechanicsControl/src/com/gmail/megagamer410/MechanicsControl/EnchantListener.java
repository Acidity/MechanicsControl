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

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantListener implements Listener 
{

	/**
	 * Necessary to get the server on static methods.
	 */
	
	MechanicsControl Plugin;
	
	/**
	 * Passes the MechanicsControl instance for static methods.
	 * @param plugin MechanicsControl plugin being passed in.
	 */
	
	public EnchantListener(MechanicsControl plugin)
	{
		Plugin = plugin;
	}
	
	@EventHandler
	public void onEnchant(EnchantItemEvent event)
	{
		//Holds the enchants that the server assigned
		Enchantment[] enchantArray = new Enchantment[5];
		
		//Makes sure that the blocking enchants has been enabled in the config.
		if(Plugin.getConfig().getBoolean("Enchants.BlockEnchants"))
		{
			//Gets the list of enchant names
			enchantArray = event.getEnchantsToAdd().keySet().toArray(enchantArray);
			
			//Number of enchants assigned
			int size = event.getEnchantsToAdd().size();
			
			//Checks all assigned enchants for being blocked and removes them as necessary
			for(int x = 0; x < size; x++)
			{
				if(Plugin.getConfig().getIntegerList("Enchants." + enchantArray[x].getName()).contains(event.getEnchantsToAdd().get(enchantArray[x])))
				{
					event.getEnchantsToAdd().remove(enchantArray[x]);
					if(event.getEnchantsToAdd().size() == 0)
					{
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
