package me.Kevin_031.BurgerCore.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;

import me.Kevin_031.BurgerCore.Main;
import me.Kevin_031.BurgerCore.Utilities.OfflinePlayerMap;
import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class OnInventoryClickListener implements Listener {

	Main plugin;
	
	public OnInventoryClickListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		if (inv == null || inv instanceof PlayerInventory) return;
		ItemStack is = e.getCurrentItem();
		if (is == null) return;
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(true);
		p.updateInventory();
		
		// PlayerDataInventory
		if(e.getView().getTitle().endsWith("data:")) {
			SkullMeta targetmeta = (SkullMeta) inv.getItem(4).getItemMeta();
			OfflinePlayer target = OfflinePlayerMap.getInstance().getOfflinePlayer(Utilities.stripColor(targetmeta.getDisplayName()));
			
			if (p.hasPermission("BurgerCore.Moderator")) {
				if (is.getType() == Material.RED_CONCRETE) {
					p.openInventory(plugin.pi.getInventory(p, target));
					return;
				}
			}
			
			if (p.hasPermission("BurgerCore.Admin")) {
				if (is.getType() == Material.CYAN_CONCRETE) {
					p.openInventory(plugin.gsi.getInventory(p, target));
					return;
				} else if (is.getType() == Material.BLUE_CONCRETE) {
					p.openInventory(plugin.hi.getInventory(p, target));
					return;
				} else if (is.getType() == Material.PURPLE_CONCRETE) {
					p.openInventory(plugin.ri.getInventory(p, target));
					return;
				}
			}
			
			p.sendMessage(Utilities.colorChat("&cYou don't have permission to open this menu."));
			return;
		}
		return;
	}
}
