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

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.plugin.java.JavaPlugin;

public class MechanicsControl extends JavaPlugin {
	public ConcurrentHashMap<UUID, Boolean> spawnerMap = new ConcurrentHashMap<UUID, Boolean>();
	
	public void onEnable() {
		getLogger().info("Enabling EnchantBlock");
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new EnchantListener(this), this);
		getServer().getPluginManager().registerEvents(new BrewListener(this), this);
		getServer().getPluginManager().registerEvents(new InteractListener(this), this);
		getServer().getPluginManager().registerEvents(new PotionSplashListener(this), this);
		getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
		getServer().getPluginManager().registerEvents(new EntityDeathListener(this), this);
		getServer().getPluginManager().registerEvents(new CreatureSpawnListener(this), this);
	}
	
	public void onDisable() {
		getLogger().info("Disabling EnchantBlock");
	}
}
