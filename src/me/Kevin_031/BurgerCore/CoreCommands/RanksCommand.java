package me.Kevin_031.BurgerCore.CoreCommands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Kevin_031.BurgerCore.Main;
import me.Kevin_031.BurgerCore.Inventories.RanksInventory;
import me.Kevin_031.BurgerCore.Utilities.OfflinePlayerMap;
import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class RanksCommand implements CommandExecutor {

	Main plugin;
	
	public RanksCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("ranks").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			sender.sendMessage("Only a player can execute this command!");
		Player p = (Player) sender;
		if (!p.hasPermission("BurgerCore.Admin"))
			p.sendMessage(Utilities.chat("&cYou do not have permission to execute this command!"));
		if (args.length != 1)
			p.sendMessage(Utilities.chat("&cIncorrect command usage; use &6/grants [player]"));
		UUID target = OfflinePlayerMap.getInstance().getUUID(args[0]);
		if (target == null)
			p.sendMessage(Utilities.chat("&cThis player either doesn't exist or has never played on this server"));
		p.openInventory((new RanksInventory(plugin)).getInventory(p, Bukkit.getOfflinePlayer(target)));
		return false;
	}
}
