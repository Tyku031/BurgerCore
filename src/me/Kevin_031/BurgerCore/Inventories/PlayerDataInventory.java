package me.Kevin_031.BurgerCore.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import me.Kevin_031.BurgerCore.Main;

public class PlayerDataInventory implements Listener {

	Main plugin;
	
	public PlayerDataInventory(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public Inventory getInventory(Player p, OfflinePlayer target) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(target);
		return null;
	}
}
