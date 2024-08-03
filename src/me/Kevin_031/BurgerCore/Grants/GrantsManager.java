package me.Kevin_031.BurgerCore.Grants;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Kevin_031.BurgerCore.Main;
import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class GrantsManager {

	Main plugin;
	
	public GrantsManager(Main plugin) {
		this.plugin = plugin;
	}
	
	public int getNumberOfGrants(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		return yml.getInt("NumberOfGrants");
	}
	
	public int getNumberOfActiveGrants(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		return yml.getInt("NumberOfActiveGrants");
	}
	
	public int getNumberOfExpiredGrants(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		return yml.getInt("NumberOfGrants") - yml.getInt("NumberOfActiveGrants");
	}
	
	public GrantStruct getGrant(OfflinePlayer p, int grantNumber) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		if (yml.getInt("NumberOfGrants") <= grantNumber)
			return null;
		return yml.getObject("Grant" + grantNumber, GrantStruct.class);
	}
	
	public ArrayList<GrantStruct> getAllGrants(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			grants.add(yml.getObject("Grant" + i, GrantStruct.class));
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllActiveGrants(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.EXPIRES_ON.after(Utilities.date(Utilities.calendar())));
				grants.add(grant);
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllInactiveGrants(OfflinePlayer p){
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.IS_ACTIVE);
				grants.add(grant);
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllExpiredGrants(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.EXPIRES_ON.before(Utilities.date(Utilities.calendar())));
				grants.add(grant);
		}
		return grants;
	}
	
	public GrantStruct getHighestActiveRank(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
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
	
	public ArrayList<GrantStruct> getAllRanks(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.GRANT.order >= 50 && grant.GRANT.order < 1000)
				grants.add(grant);
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllActiveRanks(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.GRANT.order >= 50 && grant.GRANT.order < 1000 && grant.EXPIRES_ON.after(Utilities.date(Utilities.calendar())));
				grants.add(grant);
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllInactiveRanks(OfflinePlayer p){
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.GRANT.order >= 50 && grant.GRANT.order < 1000 && grant.IS_ACTIVE);
				grants.add(grant);
		}
		return grants;
	}
	
	public ArrayList<GrantStruct> getAllExpiredRanks(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		int numberOfGrants = yml.getInt("NumberOfGrants");
		ArrayList<GrantStruct> grants = new ArrayList<GrantStruct>();
		for (int i = 0; i < numberOfGrants; i++) {
			GrantStruct grant = yml.getObject("Grant" + i, GrantStruct.class);
			if (grant.GRANT.order >= 50 && grant.GRANT.order < 1000 && grant.EXPIRES_ON.before(Utilities.date(Utilities.calendar())));
				grants.add(grant);
		}
		return grants;
	}
	
	public Boolean getNumberOfWarns(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		return yml.getBoolean("IsCurrentlyMuted");
	}
	
	public Boolean isCurrentlyMuted(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		return yml.getBoolean("IsCurrentlyMuted");
	}
	
	public Boolean isCurrentlyBanned(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		return yml.getBoolean("IsCurrentlyBanned");
	}
	
	public Boolean isCurrentlyBlacklisted(OfflinePlayer p) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		return yml.getBoolean("IsCurrentlyBlacklisted");
	}
	
	public void addGrant(OfflinePlayer p, GrantStruct grant) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
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
		plugin.playerData.savePlayerData(p, yml);
		return;
	}
	
	public void setGrantInactive(OfflinePlayer p, GrantStruct grant, int grantNumber) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
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
		plugin.playerData.savePlayerData(p, yml);
		return;
	}
	
	public void removeGrant(OfflinePlayer p, GrantStruct grant, int grantNumber) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
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
		plugin.playerData.savePlayerData(p, yml);
		return;
	}
}
