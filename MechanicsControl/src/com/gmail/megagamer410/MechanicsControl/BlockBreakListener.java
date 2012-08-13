package com.gmail.megagamer410.MechanicsControl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener 
{
	/**
	 * Necessary to get the server on static methods.
	 */
	
	static MechanicsControl Plugin;
	
	/**
	 * Passes the ChaosMod instance for static methods.
	 * @param plugin ChaosMod plugin being passed in.
	 */
	
	public BlockBreakListener(MechanicsControl plugin) 
	{
		Plugin = plugin;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(Plugin.getConfig().getConfigurationSection("XP").getKeys(false).contains(event.getBlock().getType().toString()))
		{
			if(Plugin.getConfig().getInt("XP."+ event.getBlock().getType().toString()) != event.getExpToDrop())
			{
				if(event.getExpToDrop() != 0 || Plugin.getConfig().getBoolean("XP."+event.getBlock().getType().toString()+"FORCE",false))
				{
					event.setExpToDrop(Plugin.getConfig().getInt("XP."+ event.getBlock().getType().toString()));
				}
			}
		}
	}
}
