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

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;

public class BrewListener implements Listener 
{
	/**
	 * Necessary to get the server on static methods.
	 */
	
	MechanicsControl Plugin;
	
	/**
	 * Passes the MechanicsControl instance for static methods.
	 * @param plugin MechanicsControl plugin being passed in.
	 */
	
	public BrewListener(MechanicsControl plugin)
	{
		Plugin = plugin;
	}
	
	@EventHandler
	public void onPotionBrew(BrewEvent event)
	{
		if(Plugin.getConfig().getBoolean("Potions.BlockBrewing"))
		{
			if(Plugin.getConfig().getBoolean("Potions."+event.getContents().getIngredient().getType().name()))
			{
				event.setCancelled(true);
				if(event.getContents().getViewers() != null)
				{
					for(int x = 0; x < event.getContents().getViewers().size(); x++)
					{
						((Player)event.getContents().getViewers().get(x)).sendMessage("This ingredient has been blocked by your server admin.");
					}
					
				}
			}
		}
	}
}
