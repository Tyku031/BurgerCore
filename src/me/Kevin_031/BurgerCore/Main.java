package me.Kevin_031.BurgerCore;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.java.JavaPlugin;

import me.Kevin_031.BurgerCore.CoreCommands.CurrentTimeCommand;
import me.Kevin_031.BurgerCore.Ranks.RanksFileHandler;
import me.Kevin_031.BurgerCore.Ranks.RanksPlayerHandler;
import me.Kevin_031.BurgerCore.Ranks.RanksPrefixHandler;
import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class Main extends JavaPlugin implements Listener {

	RanksFileHandler rfh = new RanksFileHandler();
	RanksPlayerHandler rph = new RanksPlayerHandler();
	RanksPrefixHandler rprh = new RanksPrefixHandler();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		//rfh.RanksSetup();
		
		new CurrentTimeCommand(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	//called before PlayerJoinEvent
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		YamlConfiguration yml = rfh.getPlayerData(Bukkit.getOfflinePlayer(p.getUniqueId()));
		File f = new File("plugins/BurgerCore/PlayerData/" + p.getUniqueId() + ".yml");
		if (yml != null) {
			//if player is blacklisted
			if (yml.getBoolean("Blacklist.IsBlacklisted")) {
				Calendar blacklistDate = (Calendar) yml.get("Blacklist.Date");
				if (yml.get("Blacklist.Duration") == null) e.disallow(Result.KICK_OTHER, 
						"You have been permanently blacklisted from this server.");
				Calendar unblacklistDate = Utilities.datePlusTime(blacklistDate, yml.getInt("Blacklist.Duration"));
				if (Utilities.calendar().after(unblacklistDate)) {
					//TODO: save Blacklist
					yml.set("Blacklist.IsBlacklisted", false);
					yml.set("Blacklist.Duration", null);
					yml.set("Blacklist.Reason", null);
					yml.set("Blacklist.From", null);
					yml.set("Blacklist.Date", null);
					try {
						yml.save(f);
					} catch (IOException error) {
						error.printStackTrace();
					}
				} else {
					e.disallow(Result.KICK_OTHER, 
							"You have been blacklisted from this server. The blacklisting expires on " + Utilities.time(unblacklistDate.getTime()));
				}
			}
			
			//if player is banned
			else if (yml.getBoolean("Ban.IsBanned")) {
				Calendar banDate = (Calendar) yml.get("Ban.Date");
				if (yml.get("Ban.Duration") == null) e.disallow(Result.KICK_OTHER, 
						"You have been permanently banned from this server.");
				Calendar unbanDate = Utilities.datePlusTime(banDate, yml.getInt("Ban.Duration"));
				if (Utilities.calendar().after(unbanDate)) {
					//TODO: save Ban
					yml.set("Ban.IsBanned", false);
					yml.set("Ban.Duration", null);
					yml.set("Ban.Reason", null);
					yml.set("Ban.From", null);
					yml.set("Ban.Date", null);
					try {
						yml.save(f);
					} catch (IOException error) {
						error.printStackTrace();
					}
				} else {
					e.disallow(Result.KICK_OTHER, 
							"You have been banned from this server. The ban expires on " + Utilities.time(unbanDate.getTime()));
				}
			}
		}
	}
	
	//called after PlayerLoginEvent
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		YamlConfiguration yml = rfh.getPlayerData(Bukkit.getOfflinePlayer(p.getUniqueId()));
		File f = new File("plugins/BurgerCore/PlayerData/" + p.getUniqueId() + ".yml");
		if (!yml.getString("Name").equals(p.getName())) {
			yml.set("Name", p.getName());
		}
		//TODO: delete expired ranks and mutes
		//rph.SetupPlayer(p);
		//rprh.SetupPrefix(p);
	}
}
