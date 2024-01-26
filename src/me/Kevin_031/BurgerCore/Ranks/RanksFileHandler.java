package me.Kevin_031.BurgerCore.Ranks;

import java.io.File;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

public class RanksFileHandler {

	String path = "plugins/BurgerCore";
	
	public void RanksSetup() {
		File MainDirectory = new File(path);
		if (!MainDirectory.exists()) {
			MainDirectory.mkdir();
		}
		
		File PlayerData = new File(path + "/PlayerData");
		if (!PlayerData.exists()) {
			PlayerData.mkdir();
		}
	}
	
	public YamlConfiguration getPlayerData(OfflinePlayer p) {
		File f = new File("plugins/BurgerCore/PlayerData/" + p.getUniqueId() + ".yml");
		if (!f.exists()) return null; 
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		return yml;
	}
}
