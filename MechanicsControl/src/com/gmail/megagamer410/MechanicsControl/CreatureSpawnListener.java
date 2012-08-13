package com.gmail.megagamer410.MechanicsControl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener 
{
	/**
	 * Necessary to get the server on static methods.
	 */
	
	MechanicsControl Plugin;
	
	/**
	 * Passes the MechanicsControl instance for static methods.
	 * @param plugin MechanicsControl plugin being passed in.
	 */
	
	public CreatureSpawnListener(MechanicsControl plugin)
	{
		Plugin = plugin;
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event)
	{
		if(event.getSpawnReason().toString().equalsIgnoreCase("SPAWNER"))
		{
			Plugin.spawnerMap.put(event.getEntity().getUniqueId(), true);
		}
	}
}
