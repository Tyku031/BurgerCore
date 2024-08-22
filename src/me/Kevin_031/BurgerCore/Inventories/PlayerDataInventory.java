package me.Kevin_031.BurgerCore.Inventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.Kevin_031.BurgerCore.Main;
import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class PlayerDataInventory {

	Main plugin;
	
	public PlayerDataInventory(Main plugin) {
		this.plugin = plugin;
	}
	
	public Inventory getInventory(Player p, OfflinePlayer target) {
		YamlConfiguration yml = plugin.playerData.getPlayerData(target);
		Inventory inv = Bukkit.createInventory(p, 27, Utilities.colorChat("&b&l" + target.getName() + "'s data:"));
		
		//skull (slot 4)
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
		skullmeta.setDisplayName(Utilities.colorChat("&3" + target.getName()));
		skullmeta.setOwningPlayer(target);
		skullmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		ArrayList<String> ranklore = new ArrayList<String>();
		ranklore.add(Utilities.colorChat("&9First login: &6" + Utilities.time(Utilities.date(plugin.playerData.getFirstLogin(yml)))));
		ranklore.add(Utilities.colorChat("&9Last login: &6" + Utilities.time(Utilities.date(plugin.playerData.getLastLogin(yml)))));
		ranklore.add(Utilities.colorChat("&9Second to last login: &6" + Utilities.time(Utilities.date(plugin.playerData.getSecondToLastLogin(yml)))));
		ranklore.add(Utilities.colorChat("&9Number of logins: &6" + plugin.playerData.getNumberOfLogins(yml)));
		//TODO: ranklore.add(Utilities.chat("&9Playtime: &6" + plugin.playerData.getPlayTime(yml)));
		skullmeta.setLore(ranklore);
		ranklore.clear();
		skull.setItemMeta(skullmeta);
		inv.setItem(4, skull);
		
		//grants (slot 18)
		ItemStack grants = new ItemStack(Material.CYAN_CONCRETE);
		ItemMeta grantsmeta = grants.getItemMeta();
		grantsmeta.setDisplayName(Utilities.colorChat("&3Go to Grants"));
		grantsmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		grants.setItemMeta(grantsmeta);
		inv.setItem(18, grants);
		
		//history (slot 20)
		ItemStack history = new ItemStack(Material.BLUE_CONCRETE);
		ItemMeta historymeta = history.getItemMeta();
		historymeta.setDisplayName(Utilities.colorChat("&9Go to History"));
		historymeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		history.setItemMeta(historymeta);
		inv.setItem(20, history);
		
		//ranks (slot 24)
		ItemStack ranks = new ItemStack(Material.PURPLE_CONCRETE);
		ItemMeta ranksmeta = ranks.getItemMeta();
		ranksmeta.setDisplayName(Utilities.colorChat("&5Go to Ranks"));
		ranksmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		ranks.setItemMeta(ranksmeta);
		inv.setItem(24, ranks);
		
		//punishments (slot 26)
		ItemStack punishments = new ItemStack(Material.RED_CONCRETE);
		ItemMeta punishmentsmeta = punishments.getItemMeta();
		punishmentsmeta.setDisplayName(Utilities.colorChat("&cGo to Punishments"));
		punishmentsmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		punishments.setItemMeta(punishmentsmeta);
		inv.setItem(26, punishments);
		
		return inv; //playerdata inv color is lime (concrete)
	}
}
