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
