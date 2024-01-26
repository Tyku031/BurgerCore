package me.Kevin_031.BurgerCore.Ranks;

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class RanksPlayerHandler {

	RanksFileHandler rfh = new RanksFileHandler();
	
	int OWNER = 100;
	int MANAGER = 90;
	int DEVELOPER = 85;
	int ADMIN = 80;
	int MODERATOR = 70;
	int TRIALMOD = 65;
	int BUILDER = 60;
	int MEDIA = 50;
	int LEGEND = 40;
	int ULTRA = 30;
	int ELITE = 20;
	int VIP = 10;
	int DEFAULT = 0;
	
	Integer[] ranks = {DEFAULT, VIP, ELITE, ULTRA, LEGEND, MEDIA, BUILDER, TRIALMOD, MODERATOR, ADMIN, DEVELOPER, MANAGER, OWNER};
	
	public void SetupPlayer(Player p) {
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
		YamlConfiguration yml = rfh.getPlayerData(p);
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
}
