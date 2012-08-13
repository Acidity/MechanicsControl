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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;

public class InteractListener implements Listener 
{
	/**
	 * Necessary to get the server on static methods.
	 */
	
	static MechanicsControl Plugin;
	
	/**
	 * Passes the MechanicsControl instance for static methods.
	 * @param plugin MechanicsControl plugin being passed in.
	 */
	
	public InteractListener(MechanicsControl plugin) 
	{
		Plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		final PlayerInteractEvent Event = event;
		if(event.getAction().name().equalsIgnoreCase("RIGHT_CLICK_AIR"))
		{
			if(event.hasItem())
			{
				if(event.getItem() != null)
				{
					if(event.getItem().getType() == Material.POTION)
					{
						Plugin.getServer().getScheduler().scheduleSyncDelayedTask(Plugin, new Runnable() 
						{
							public void run() 
							{
								if(Event.getPlayer().getActivePotionEffects() != null)
								{
									PotionEffect[] potions = new PotionEffect[10];
									potions = Event.getPlayer().getActivePotionEffects().toArray(potions);
									if(Event.getPlayer().getActivePotionEffects().size() > 0)
									{
										for(int x = 0; x < Event.getPlayer().getActivePotionEffects().size(); x++)
										{
											if(Plugin.getConfig().getBoolean("Potions."+potions[x].getType().getName()))
											{
												Event.getPlayer().removePotionEffect(potions[x].getType());
											}
										}
									}
								}
							}
						}, 40);
					}
				}
			}
		}
		if(event.getAction().name().equalsIgnoreCase("RIGHT_CLICK_BLOCK"))
		{
			if(event.hasItem())
			{
				if(event.getItem() != null)
				{
					if(event.getItem().getType() == Material.POTION)
					{
						Plugin.getServer().getScheduler().scheduleSyncDelayedTask(Plugin, new Runnable() 
						{
							public void run() 
							{
								if(Event.getPlayer().getActivePotionEffects() != null)
								{
									PotionEffect[] potions = new PotionEffect[10];
									potions = Event.getPlayer().getActivePotionEffects().toArray(potions);
									if(Event.getPlayer().getActivePotionEffects().size() > 0)
									{
										for(int x = 0; x < Event.getPlayer().getActivePotionEffects().size(); x++)
										{
											if(Plugin.getConfig().getBoolean("Potions."+potions[x].getType().getName()))
											{
												Event.getPlayer().removePotionEffect(potions[x].getType());
											}
										}
									}
								}
							}
						}, 40);
					}
				}
			}
		}
	}
}
