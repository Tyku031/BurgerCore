package me.Kevin_031.BurgerCore.Core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Kevin_031.BurgerCore.Main;
import me.Kevin_031.BurgerCore.Grants.GrantStruct;
import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class PlayerDataFileManager {
	
	Main plugin;
	FileManager files;
	
	public PlayerDataFileManager(Main plugin) {
		this.plugin = plugin;
		this.files = new FileManager("PlayerData/");
	}
	
	/** Method to check if a certain player has a player data file */ //likely unnecessary
	/*public boolean existsPlayerData(OfflinePlayer p) {
		File f = plugin.playerData.GetFileFromDir(p.getUniqueId().toString());
		return (f != null);
	}*/
	
	/** Method to get the .yml file corresponding to a certain player, this contains all player data. */
	public YamlConfiguration getPlayerData(OfflinePlayer p) {
		File f = files.getFile(p.getUniqueId().toString());
		if (f != null) {
			return YamlConfiguration.loadConfiguration(f);
		} else {
			Bukkit.getLogger().severe("Failed to retrieve " + p.getName() + "'s playerdata file, as it does not exist.");
		}
		return null;
	}
	
	/** Method to set or modify a certain player's data */ //likely unnecessary
	/*public void setPlayerData(OfflinePlayer p, String key, Object data) {
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
	}*/
	
	/** Method to set a certain player's data to a new YamlConfiguration. */
	public void setPlayerDataFile(OfflinePlayer p, YamlConfiguration yml) {
		File f = files.getOrCreateFile(p.getUniqueId().toString());

		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/** Method to set a certain player's data to default values. */
	public YamlConfiguration setDefaultPlayerDataFile(OfflinePlayer p) {
		File f = files.getOrCreateFile(p.getUniqueId().toString());
		
		YamlConfiguration yml = defaultPlayerData(p);
		yml.set("FirstLogin", Utilities.date(Utilities.calendar()));

		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return yml;
	}
	
	/** Default playerdata file */
	private YamlConfiguration defaultPlayerData(OfflinePlayer p) {
		YamlConfiguration yml = new YamlConfiguration();
		
		// general playerdata
		yml.addDefault("Name", p.getName());
		yml.addDefault("FirstLogin", Utilities.calendar());
		yml.addDefault("NumberOfLogins", 0);
		yml.addDefault("LastLogin", null);
		yml.addDefault("SecondToLastLogin", null);
		yml.addDefault("PlayTime", null);
		
		// grants
		yml.addDefault("NumberOfGrants", 0);
		yml.addDefault("NumberOfActiveGrants", 0);
		
		// punishments
		yml.addDefault("NumberOfActiveWarnings", 0);
		yml.addDefault("IsCurrentlyMuted", false);
		yml.addDefault("ActiveMute", null);
		yml.addDefault("IsCurrentlyBanned", false);
		yml.addDefault("ActiveBan", null);
		yml.addDefault("IsCurrentlyBlacklisted", false);
		yml.addDefault("ActiveBlacklist", null);
		
		yml.options().copyDefaults(true);
		return yml;
	}
	
	public Calendar getFirstLogin(YamlConfiguration yml) {
		return yml.getObject("FirstLogin", Calendar.class);
	}
	
	public int getNumberOfLogins(YamlConfiguration yml) {
		return yml.getInt("NumberOfLogins");
	}
	
	public Calendar getLastLogin(YamlConfiguration yml) {
		return yml.getObject("LastLogin", Calendar.class);
	}
	
	public Calendar getSecondToLastLogin(YamlConfiguration yml) {
		return yml.getObject("SecondToLastLogin", Calendar.class);
	}
	
	//TODO: public ? getPlayTime(YamlConfiguration yml) { }
	
	public int getNumberOfGrants(YamlConfiguration yml) {
		return yml.getInt("NumberOfGrants");
	}
	
	public int getNumberOfActiveGrants(YamlConfiguration yml) {
		return yml.getInt("NumberOfActiveGrants");
	}
	
	public int getNumberOfExpiredGrants(YamlConfiguration yml) {
		return yml.getInt("NumberOfGrants") - yml.getInt("NumberOfActiveGrants");
	}
	
	public GrantStruct getGrant(YamlConfiguration yml, int grantNumber) {
		if (yml.getInt("NumberOfGrants") <= grantNumber)
			return null;
		return yml.getObject("Grant" + grantNumber, GrantStruct.class);
	}
	
	public ArrayList<GrantStruct> getAllGrants(YamlConfiguration yml) {
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			grants.add(yml.getObject("Grant" + i, GrantStruct.class));
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllActiveGrants(YamlConfiguration yml) {
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.EXPIRES_ON.after(Utilities.date(Utilities.calendar())));
				grants.add(grant);
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllInactiveGrants(YamlConfiguration yml){
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.IS_ACTIVE);
				grants.add(grant);
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllExpiredGrants(YamlConfiguration yml) {
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.EXPIRES_ON.before(Utilities.date(Utilities.calendar())));
				grants.add(grant);
		}
		return grants;
	}
	
	public GrantStruct getHighestActiveRank(YamlConfiguration yml) {
		int numberOfGrants = yml.getInt("NumberOfGrants");
		GrantStruct highestGrant = null;
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (highestGrant != null && grant.GRANT.compareTo(highestGrant.GRANT) > 0)
				highestGrant = grant;
			else
				highestGrant = grant;
		}
		return highestGrant;
	}
	
	public ArrayList<GrantStruct> getAllRanks(YamlConfiguration yml) {
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.GRANT.order >= 50 && grant.GRANT.order < 1000)
				grants.add(grant);
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllActiveRanks(YamlConfiguration yml) {
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.GRANT.order >= 50 && grant.GRANT.order < 1000 && grant.EXPIRES_ON.after(Utilities.date(Utilities.calendar())));
				grants.add(grant);
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllInactiveRanks(YamlConfiguration yml){
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.GRANT.order >= 50 && grant.GRANT.order < 1000 && grant.IS_ACTIVE);
				grants.add(grant);
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllExpiredRanks(YamlConfiguration yml) {
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.GRANT.order >= 50 && grant.GRANT.order < 1000 && grant.EXPIRES_ON.before(Utilities.date(Utilities.calendar())));
				grants.add(grant);
		}
		return grants;
	}
	
	public Boolean getNumberOfWarns(YamlConfiguration yml) {
		return yml.getBoolean("IsCurrentlyMuted");
	}
	
	public Boolean isCurrentlyMuted(YamlConfiguration yml) {
		return yml.getBoolean("IsCurrentlyMuted");
	}
	
	public Boolean isCurrentlyBanned(YamlConfiguration yml) {
		return yml.getBoolean("IsCurrentlyBanned");
	}
	
	public Boolean isCurrentlyBlacklisted(YamlConfiguration yml) {
		return yml.getBoolean("IsCurrentlyBlacklisted");
	}
	
	public void addGrant(OfflinePlayer p, YamlConfiguration yml, GrantStruct grant) {
		int numberOfGrants = yml.getInt("NumberOfGrants");
		switch (grant.GRANT.order) {
		case 3:
			yml.set("NumberOfActiveWarnings", yml.getInt("NumberOfActiveWarnings") + 1);
			break;
		case 4:
		case 5:
			yml.set("IsCurrentlyMuted", true);
			yml.set("ActiveMute", numberOfGrants);
			break;
		case 6:
		case 7:
			yml.set("IsCurrentlyBanned", true);
			yml.set("ActiveBan", numberOfGrants);
			break;
		case 8:
		case 9:
			yml.set("IsCurrentlyBlacklisted", true);
			yml.set("ActiveBlacklist", numberOfGrants);
			break;
		default:
			break;
		}
		yml.set("Grant" + numberOfGrants, grant);
		yml.set("NumberOfGrants", numberOfGrants + 1);
		yml.set("NumberOfActiveGrants", yml.getInt("NumberOfActiveGrants") + 1);
		setPlayerDataFile(p, yml);
		return;
	}
	
	public void setGrantInactive(OfflinePlayer p, YamlConfiguration yml, GrantStruct grant, int grantNumber) {
		switch (grant.GRANT.order) {
		case 3:
			yml.set("NumberOfActiveWarnings", yml.getInt("NumberOfActiveWarnings") - 1);
			break;
		case 4:
		case 5:
			yml.set("IsCurrentlyMuted", false);
			yml.set("ActiveMute", null);
			break;
		case 6:
		case 7:
			yml.set("IsCurrentlyBanned", false);
			yml.set("ActiveBan", null);
			break;
		case 8:
		case 9:
			yml.set("IsCurrentlyBlacklisted", false);
			yml.set("ActiveBlacklist", null);
			break;
		default:
			break;
		}
		grant.IS_ACTIVE = false;
		yml.set("Grant" + grantNumber, grant);
		yml.set("NumberOfActiveGrants", yml.getInt("NumberOfActiveGrants") - 1);
		setPlayerDataFile(p, yml);
		return;
	}
	
	public void removeGrant(OfflinePlayer p, YamlConfiguration yml, GrantStruct grant, int grantNumber) {
		int numberOfGrants = yml.getInt("NumberOfGrants");
		if (grant.IS_ACTIVE) {
			switch (grant.GRANT.order) {
			case 3:
				yml.set("NumberOfActiveWarnings", yml.getInt("NumberOfActiveWarnings") - 1);
				break;
			case 4:
			case 5:
				yml.set("IsCurrentlyMuted", false);
				yml.set("ActiveMute", null);
				break;
			case 6:
			case 7:
				yml.set("IsCurrentlyBanned", false);
				yml.set("ActiveBan", null);
				break;
			case 8:
			case 9:
				yml.set("IsCurrentlyBlacklisted", false);
				yml.set("ActiveBlacklist", null);
				break;
			default:
				break;
			}
			yml.set("NumberOfActiveGrants", yml.getInt("NumberOfActiveGrants") - 1);
		}
		for (int i = grantNumber; i < numberOfGrants; i++)	
			yml.set("Grant" + grantNumber, yml.getObject("Grant" + (grantNumber + 1), GrantStruct.class));
		yml.set("Grant" + numberOfGrants, null);
		yml.set("NumberOfGrants", numberOfGrants - 1);
		setPlayerDataFile(p, yml);
		return;
	}
}
