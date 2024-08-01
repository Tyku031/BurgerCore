package me.Kevin_031.BurgerCore;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.Kevin_031.BurgerCore.Core.PlayerDataFileManager;
import me.Kevin_031.BurgerCore.CoreCommands.CurrentTimeCommand;
import me.Kevin_031.BurgerCore.Grants.GrantsManager;

public class Main extends JavaPlugin implements Listener {

	// Managers:
	public PlayerDataFileManager playerData;
	public GrantsManager grants;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		
		// Managers:
		playerData = new PlayerDataFileManager();
		grants = new GrantsManager(this);
		
		// Listeners:
		
		// Commands:
		new CurrentTimeCommand(this);
	}
	
	@Override
	public void onDisable() {
		
	}
}
