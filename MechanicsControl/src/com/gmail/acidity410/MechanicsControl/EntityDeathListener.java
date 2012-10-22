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

package com.gmail.acidity410.MechanicsControl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {
	/**
	 * Necessary to get the server on static methods.
	 */
	
	private MechanicsControl plugin;
	
	/**
	 * Passes the EnchantBlock instance for static methods.
	 * @param plugin EnchantBlock plugin being passed in.
	 */
	
	public EntityDeathListener(MechanicsControl plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		//If the config doesn't mention this entity, do nothing
		if(!plugin.getConfig().getConfigurationSection("XP").getKeys(false).contains(event.getEntityType().toString())) {
			return;
		}
		//If the config says to drop the same amount of XP as default Bukkit, do nothing
		if(plugin.getConfig().getInt("XP."+ event.getEntityType().toString()) == event.getDroppedExp()) {
			return;
		}
		//If XP doesn't equal 0 or if they specified it to force, then override default XP
		if(event.getDroppedExp() != 0 || plugin.getConfig().getBoolean("XP."+event.getEntityType().toString()+"FORCE",false)) {
			event.setDroppedExp(plugin.getConfig().getInt("XP."+ event.getEntityType().toString()));
		}
		//If the config doesn't say to check for spawned by mob spawner, stop
		if(plugin.getConfig().getBoolean("XP.MobSpawnerBlock")) {
			//If the mob was spawned by mob spawner, don't drop XP
			if(plugin.spawnerMap.containsKey(event.getEntity().getUniqueId())) {
				event.setDroppedExp(0);
				plugin.spawnerMap.remove(event.getEntity().getUniqueId());
			}
		}
	}
}
