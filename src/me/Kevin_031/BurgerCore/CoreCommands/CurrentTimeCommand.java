package me.Kevin_031.BurgerCore.CoreCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Kevin_031.BurgerCore.Main;
import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class CurrentTimeCommand implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	
	public CurrentTimeCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("currenttime").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage("The current time is " + Utilities.time(Utilities.date(Utilities.calendar())));
		return false;
	}
}
