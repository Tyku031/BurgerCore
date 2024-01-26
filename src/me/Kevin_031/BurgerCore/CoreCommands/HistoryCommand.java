package me.Kevin_031.BurgerCore.CoreCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Kevin_031.BurgerCore.Main;

public class HistoryCommand implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	
	public HistoryCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("history").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		return false;
	}
}

