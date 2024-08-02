package me.Kevin_031.BurgerCore.Core;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class PlayerDataFileManager {
	
	// Root directory string.
	private String rootDirectory = "plugins/BurgerCore/PlayerData/";
	// File is the root directory file.
	public File file;
	
	/** Manager for getting and setting playerdata. 
	  * Will create root directory if it doesn't exist. 
	  * All data will be placed in the plugins/Burgercore/PlayerData folder. */
	public PlayerDataFileManager() {
		file = new File(rootDirectory);
		if (!file.exists())
			file.mkdirs();
	}
	
	/** Method to check if a certain player has a player data file */
	public boolean existsPlayerData(OfflinePlayer p) {
		File f = new File(rootDirectory + p.getUniqueId() + ".yml");
		return f.exists();
	}
	
	/** Method to get the .yml file corresponding to a certain player, this contains all player data. */
	public YamlConfiguration getPlayerData(OfflinePlayer p) {
		File f = new File(rootDirectory + p.getUniqueId() + ".yml");
		if (f.exists()) {
			return YamlConfiguration.loadConfiguration(f);
		} else {
			Bukkit.getLogger().severe("Failed to retrieve " + p.getName() + "'s playerdata file, as it does not exist.");
		}
		return null;
	}
	
	/** Method to set or modify a certain player's data */
	public void setPlayerData(OfflinePlayer p, String key, Object data) {
		File f = new File(rootDirectory + p.getUniqueId() + ".yml");
		if (!f.exists()) {
			Bukkit.getLogger().warning("Failed to set '" + key + "' to a value in " + p.getName() + "'s playerdata file, as it does not exist.");
			return;
		}
		
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		if (yml.contains(key))
			yml.set(key, data);
		else {
			yml.addDefault(key, data);
			yml.options().copyDefaults();
			Bukkit.getLogger().warning("Set '" + key + "' to a value in " + p.getName() + "'s playerdata file, but '" + key + "' did not exist yet in that file.");
		}
		
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/** Method to set a certain player's data to a new YamlConfiguration. */
	public void setPlayerDataFile(OfflinePlayer p, YamlConfiguration yml) {
		File f = new File(rootDirectory + p.getUniqueId() + ".yml");
		
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}

		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/** Method to set a certain player's data to default values. */
	public void setDefaultPlayerDataFile(OfflinePlayer p) {
		File f = new File(rootDirectory + p.getUniqueId() + ".yml");
		
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		
		YamlConfiguration yml = defaultPlayerData(p);

		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/** Method to save a player's data file, only use if you know the file exists */
	public void savePlayerData(OfflinePlayer p, YamlConfiguration yml) {
		File f = new File(rootDirectory + p.getUniqueId() + ".yml");
		
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/** Default playerdata file */
	private YamlConfiguration defaultPlayerData(OfflinePlayer p) {
		YamlConfiguration yml = new YamlConfiguration();
		
		// general playerdata
		yml.addDefault("Name", p.getName());
		yml.addDefault("FirstLogin", Utilities.calendar());
		yml.addDefault("NumberOfLogins", 0);
		yml.addDefault("LastLogin", null);
		yml.addDefault("PlayTime", null);
		
		// grants
		yml.addDefault("NumberOfGrants", 0);
		yml.addDefault("NumberOfActiveGrants", 0);
		
		
		yml.addDefault("NumberOfActiveWarnings", 0);
		yml.addDefault("IsCurrentlyMuted", false);
		yml.addDefault("IsCurrentlyBanned", false);
		yml.addDefault("IsCurrentlyBlacklisted", false);
		
		yml.options().copyDefaults(true);
		return yml;
	}
	
	public Calendar getFirstLogin(OfflinePlayer p) {
		YamlConfiguration yml = getPlayerData(p);
		return yml.getObject("FirstLogin", Calendar.class);
	}
	
	public int getNumberOfLogins(OfflinePlayer p) {
		YamlConfiguration yml = getPlayerData(p);
		return yml.getInt("NumberOfLogins");
	}
	
	public Calendar getLastLogin(OfflinePlayer p) {
		YamlConfiguration yml = getPlayerData(p);
		return yml.getObject("LastLogin", Calendar.class);
	}
	
	// public ? getPlayTime(OfflinePlayer p);
}
