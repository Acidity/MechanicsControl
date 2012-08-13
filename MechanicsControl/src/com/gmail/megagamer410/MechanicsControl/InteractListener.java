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
