package me.Kevin_031.BurgerCore;

import org.bukkit.plugin.java.JavaPlugin;

import me.Kevin_031.BurgerCore.Core.PlayerDataFileManager;
import me.Kevin_031.BurgerCore.CoreCommands.CurrentTimeCommand;
import me.Kevin_031.BurgerCore.Grants.GrantsManager;
import me.Kevin_031.BurgerCore.Listeners.PlayerJoinListener;

public class Main extends JavaPlugin {

	// Managers:
	public PlayerDataFileManager playerData;
	public GrantsManager grants;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		// Managers:
		playerData = new PlayerDataFileManager();
		grants = new GrantsManager(this);
		
		// Listeners:
		new PlayerJoinListener(this);
		
		// Commands:
		new CurrentTimeCommand(this);
		
		saveConfig();
	}
	
	@Override
	public void onDisable() { }
}
