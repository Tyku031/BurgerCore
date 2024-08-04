package me.Kevin_031.BurgerCore;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import me.Kevin_031.BurgerCore.Core.PlayerDataFileManager;
import me.Kevin_031.BurgerCore.CoreCommands.CurrentTimeCommand;
import me.Kevin_031.BurgerCore.Grants.GrantStruct;
import me.Kevin_031.BurgerCore.Grants.GrantsManager;
import me.Kevin_031.BurgerCore.Listeners.PlayerJoinListener;
import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class Main extends JavaPlugin {

	// Managers:
	public PlayerDataFileManager playerData;
	public GrantsManager grants;
	public HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<UUID, PermissionAttachment>();
	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults();
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
	public void onDisable() {
		playerData = null;
		grants = null;
		playerPermissions.clear();
	}
	
	public void setupPermissions(Player p, GrantStruct highestRank) {
		PermissionAttachment attachment = p.addAttachment(this);
		for (String permissions : getConfig().getStringList("Ranks." + highestRank.GRANT.name + ".permissions"))
			attachment.setPermission(permissions, true);
		playerPermissions.put(p.getUniqueId(), attachment);
		p.updateCommands();
	}
	
	public void setupPrefixes(Player p, GrantStruct highestRank) {
		p.setDisplayName(Utilities.chat(highestRank.GRANT.color + "[" + highestRank.GRANT.name + "] &r&f" + p.getName()));
		p.setPlayerListName(Utilities.chat(highestRank.GRANT.color + "[" + highestRank.GRANT.name + "] " + p.getName()));
	}
}
