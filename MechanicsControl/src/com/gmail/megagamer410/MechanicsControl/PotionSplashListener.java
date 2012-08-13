package com.gmail.megagamer410.MechanicsControl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;

public class PotionSplashListener implements Listener 
{
	/**
	 * Necessary to get the server on static methods.
	 */
	
	static MechanicsControl Plugin;
	
	/**
	 * Passes the MechanicsControl instance for static methods.
	 * @param plugin MechanicsControl plugin being passed in.
	 */
	
	public PotionSplashListener(MechanicsControl plugin) 
	{
		Plugin = plugin;
	}
	
	@EventHandler
	public void onPotionSplash(PotionSplashEvent event)
	{
		PotionEffect[] potions = new PotionEffect[10];
		potions = event.getPotion().getEffects().toArray(potions);
		
		if(event.getPotion().getEffects().size() > 0)
		{
			for(int x = 0; x < event.getPotion().getEffects().size(); x++)
			{
				if(Plugin.getConfig().getBoolean("Potions."+potions[x].getType().getName()))
				{
					event.setCancelled(true);
				}
			}
		}
	}
}
