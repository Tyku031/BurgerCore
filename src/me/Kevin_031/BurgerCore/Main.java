package me.Kevin_031.BurgerCore;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.Kevin_031.BurgerCore.Core.PlayerDataFileManager;
import me.Kevin_031.BurgerCore.CoreCommands.CurrentTimeCommand;

public class Main extends JavaPlugin implements Listener {

	public PlayerDataFileManager pdfm = new PlayerDataFileManager();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		
		// Commands:
		new CurrentTimeCommand(this);
	}
	
	@Override
	public void onDisable() {
		
	}
}
