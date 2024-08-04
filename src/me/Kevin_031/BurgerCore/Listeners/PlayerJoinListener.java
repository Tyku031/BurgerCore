package me.Kevin_031.BurgerCore.Listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Kevin_031.BurgerCore.Main;
import me.Kevin_031.BurgerCore.Grants.GrantEnum;
import me.Kevin_031.BurgerCore.Grants.GrantStruct;
import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class PlayerJoinListener implements Listener {

	private Main plugin;
	
	public PlayerJoinListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	// Called before PlayerJoinEvent
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		
		//if player has never logged on before
		if (!p.hasPlayedBefore()) {
			plugin.playerData.setDefaultPlayerDataFile(p, Utilities.date(Utilities.calendar()));
			plugin.grants.addGrant(p, new GrantStruct(GrantEnum.DEFAULT, null, "Default Rank", Utilities.date(Utilities.calendar()), null, true));
			return;
		}
		
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		
		//if player is blacklisted
		if (yml.getBoolean("IsCurrentlyBlacklisted")) {
			GrantStruct blacklist = yml.getObject("Grant" + yml.getInt("ActiveBlacklist"), GrantStruct.class);
			if (blacklist.EXPIRES_ON == null)
				e.disallow(Result.KICK_OTHER, "You have been blacklisted from this server. Your blacklisting will never expire. You have been blacklisted for '" + blacklist.REASON + "'.");
			else if (blacklist.EXPIRES_ON.after(Utilities.date(Utilities.calendar())))
				e.disallow(Result.KICK_OTHER, "You have been blacklisted from this server. Your blacklisting expires on " + Utilities.time(blacklist.EXPIRES_ON) + ". You have been blacklisted for '" + blacklist.REASON + "'.");
			else {
				plugin.grants.setGrantInactive(p, blacklist, yml.getInt("ActiveBlacklist"));
			}
		}
		
		//if player is banned
		else if (yml.getBoolean("IsCurrentlyBanned")) {
			GrantStruct ban = yml.getObject("Grant" + yml.getInt("ActiveBan"), GrantStruct.class);
			if (ban.EXPIRES_ON == null)
				e.disallow(Result.KICK_OTHER, "You have been banned from this server. Your ban will never expire. You have been banned for '" + ban.REASON + "'.");
			else if (ban.EXPIRES_ON.after(Utilities.date(Utilities.calendar())))
				e.disallow(Result.KICK_OTHER, "You have been banned from this server. Your ban expires on " + Utilities.time(ban.EXPIRES_ON) + ". You have been banned for '" + ban.REASON + "'.");
			else {
				plugin.grants.setGrantInactive(p, ban, yml.getInt("ActiveBan"));
			}
		}
		
		//if player is muted
		else if (yml.getBoolean("IsCurrentlyMuted")) {
			GrantStruct mute = yml.getObject("Grant" + yml.getInt("ActiveMute"), GrantStruct.class);
			if (mute.EXPIRES_ON != null && mute.EXPIRES_ON.before(Utilities.date(Utilities.calendar()))) {
				plugin.grants.setGrantInactive(p, mute, yml.getInt("ActiveMute"));
			}
		}
	}
	
	//called after PlayerLoginEvent
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		YamlConfiguration yml = plugin.playerData.getPlayerData(p);
		if (!yml.getString("Name").equals(p.getName()))
			yml.set("Name", p.getName());
		yml.set("NumberOfLogins", yml.getInt("NumberOfLogins") + 1);
		yml.set("LastLogin", Utilities.date(Utilities.calendar()));
		
		ArrayList<GrantStruct> ranks = plugin.grants.getAllActiveRanks(p);
		for (int i = 0; i < ranks.size(); i++) {
			GrantStruct rank = ranks.get(i);
			if (rank.IS_ACTIVE && rank.EXPIRES_ON != null && rank.EXPIRES_ON.before(Utilities.date(Utilities.calendar()))) {
				rank.IS_ACTIVE = false;
				plugin.grants.setGrantInactive(p, rank, i);
			}
		}
		
		GrantStruct highestRank = plugin.grants.getHighestActiveRank(p);
		plugin.setupPermissions(p, highestRank);
		plugin.setupPrefixes(p, highestRank);
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		plugin.playerPermissions.remove(p.getUniqueId());
	}
}
