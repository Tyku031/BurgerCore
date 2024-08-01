package me.Kevin_031.BurgerCore.Grants;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Kevin_031.BurgerCore.Main;
import me.Kevin_031.BurgerCore.Core.GrantStruct;
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
		/*
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		int numberOfGrants = yml.getInt("NumberOfGrants");
		*/
		return;
	}
	
	/*
	public void setupPlayer(Player p) {
		File f = new File("plugins/BurgerCore/PlayerData/" + p.getUniqueId() + ".yml");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		
		//general playerdata
		yml.addDefault("Name", p.getName());
		yml.addDefault("FirstLogin", Utilities.calendar());
		yml.addDefault("NumberOfLogins", 0);
		yml.addDefault("LastLogin", null);
		yml.addDefault("PlayTime", null);
		
		//default rank
		yml.addDefault("Ranks.0.HasRank", true);
		yml.addDefault("Ranks.0.Duration", null);
		yml.addDefault("Ranks.0.Reason", "default rank");
		yml.addDefault("Ranks.0.From", "console");
		yml.addDefault("Ranks.0.Date", Utilities.calendar());
		for (int i = 1; i < ranks.length; i++) {
			yml.addDefault("Ranks." + ranks[i] + ".HasRank", false);
			yml.addDefault("Ranks." + ranks[i] + ".Duration", null);
			yml.addDefault("Ranks." + ranks[i] + ".Reason", null);
			yml.addDefault("Ranks." + ranks[i] + ".From", null);
			yml.addDefault("Ranks." + ranks[i] + ".Date", null);
		}
		
		//punishments
		////yml.addDefault("Warns", null); //todo
		
		//mute
		yml.addDefault("Mute.IsMuted", false); 
		yml.addDefault("Mute.Duration", null);
		yml.addDefault("Mute.Reason", null);
		yml.addDefault("Mute.From", null);
		yml.addDefault("Mute.Date", null);
		
		//ban
		yml.addDefault("Ban.IsBanned", false);
		yml.addDefault("Ban.Duration", null);
		yml.addDefault("Ban.Reason", null);
		yml.addDefault("Ban.From", null);
		yml.addDefault("Ban.Date", null);
		
		//blacklist
		yml.addDefault("Blacklist.IsBlacklisted", false);
		yml.addDefault("Blacklist.Duration", null);
		yml.addDefault("Blacklist.Reason", null);
		yml.addDefault("Blacklist.From", null);
		yml.addDefault("Blacklist.Date", null);
		
		yml.options().copyDefaults(true);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> getRanks(OfflinePlayer p) {
		YamlConfiguration yml = pdfm.getPlayerData(p);
		if (yml == null) return null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ranks.length; i++) {
			if (yml.getBoolean("Ranks." + ranks[i] + ".HasRank")) {
				list.add(ranks[i]);
			}
		}
		return list;
	}
	
	public Integer getHighestRank(OfflinePlayer p) {
		ArrayList<Integer> list = getRanks(p);
		if (list == null) return null;
		return list.get(list.size() - 1);
	}
	
	public void grantRank(OfflinePlayer p, Integer i) {
		ArrayList<Integer> list = getRanks(p);
		if (list == null) return;
		if (!list.contains(i)) {
			list.add(i);
			Collections.sort(list, Collections.reverseOrder());
		}
	}
	
	public void revokeRank(OfflinePlayer p, Integer i) {
		ArrayList<Integer> list = getRanks(p);
		if (list == null) return;
		if (list.contains(i)) {
			list.remove(i);
		}
	}
	*/
}
