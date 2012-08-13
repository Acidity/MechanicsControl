package com.gmail.megagamer410.MechanicsControl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener 
{
	/**
	 * Necessary to get the server on static methods.
	 */
	
	MechanicsControl Plugin;
	
	/**
	 * Passes the EnchantBlock instance for static methods.
	 * @param plugin EnchantBlock plugin being passed in.
	 */
	
	public EntityDeathListener(MechanicsControl plugin)
	{
		Plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event)
	{
		if(Plugin.getConfig().getConfigurationSection("XP").getKeys(false).contains(event.getEntityType().toString()))
		{
			if(Plugin.getConfig().getInt("XP."+ event.getEntityType().toString()) != event.getDroppedExp())
			{
				if(event.getDroppedExp() != 0 || Plugin.getConfig().getBoolean("XP."+event.getEntityType().toString()+"FORCE",false))
				{
					event.setDroppedExp(Plugin.getConfig().getInt("XP."+ event.getEntityType().toString()));
				}
			}
		}
		if(Plugin.getConfig().getBoolean("XP.MobSpawnerBlock"))
		{
			if(Plugin.spawnerMap.containsKey(event.getEntity().getUniqueId()))
			{
				event.setDroppedExp(0);
				Plugin.spawnerMap.remove(event.getEntity().getUniqueId());
			}
		}
	}
}
