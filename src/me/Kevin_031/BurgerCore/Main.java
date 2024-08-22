package me.Kevin_031.BurgerCore;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import me.Kevin_031.BurgerCore.Commands.CurrentTimeCommand;
import me.Kevin_031.BurgerCore.Commands.GrantCommand;
import me.Kevin_031.BurgerCore.Commands.GrantsCommand;
import me.Kevin_031.BurgerCore.Commands.HistoryCommand;
import me.Kevin_031.BurgerCore.Commands.PlayerDataCommand;
import me.Kevin_031.BurgerCore.Commands.PunishmentsCommand;
import me.Kevin_031.BurgerCore.Commands.RanksCommand;
import me.Kevin_031.BurgerCore.Core.PlayerDataFileManager;
import me.Kevin_031.BurgerCore.Grants.GrantStruct;
import me.Kevin_031.BurgerCore.Inventories.GrantInventory;
import me.Kevin_031.BurgerCore.Inventories.GrantsInventory;
import me.Kevin_031.BurgerCore.Inventories.HistoryInventory;
import me.Kevin_031.BurgerCore.Inventories.PlayerDataInventory;
import me.Kevin_031.BurgerCore.Inventories.PunishmentsInventory;
import me.Kevin_031.BurgerCore.Inventories.RanksInventory;
import me.Kevin_031.BurgerCore.Listeners.OnInventoryClickListener;
import me.Kevin_031.BurgerCore.Listeners.PlayerJoinListener;
import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class Main extends JavaPlugin {

	// Managers:
	public PlayerDataFileManager playerData;
	public HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<UUID, PermissionAttachment>();
	
	// Inventories:
	public GrantInventory gi;
	public GrantsInventory gsi;
	public HistoryInventory hi;
	public PlayerDataInventory pdi;
	public PunishmentsInventory pi;
	public RanksInventory ri;
	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		// Managers:
		playerData = new PlayerDataFileManager(this);
		
		// Listeners:
		new PlayerJoinListener(this);
		new OnInventoryClickListener(this);
		
		// Commands:
		new GrantCommand(this);
		new GrantsCommand(this);
		new HistoryCommand(this);
		new PlayerDataCommand(this);
		new PunishmentsCommand(this);
		new RanksCommand(this);
		
		new CurrentTimeCommand(this);
		
		// Inventories:
		gi = new GrantInventory(this);
		gsi = new GrantsInventory(this);
		hi = new HistoryInventory(this);
		pdi = new PlayerDataInventory(this);
		pi = new PunishmentsInventory(this);
		ri = new RanksInventory(this);
		
		saveConfig();
	}
	
	@Override
	public void onDisable() {
		playerData = null;
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
		p.setDisplayName(Utilities.colorChat(highestRank.GRANT.color + "[" + highestRank.GRANT.name + "] &r&f" + p.getName()));
		p.setPlayerListName(Utilities.colorChat(highestRank.GRANT.color + "[" + highestRank.GRANT.name + "] " + p.getName()));
	}
}
