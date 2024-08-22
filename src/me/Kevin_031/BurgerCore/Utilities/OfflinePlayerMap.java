package me.Kevin_031.BurgerCore.Utilities;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

// I took inspiration for this class from ElgarL's GroupManager plugin; OfflinePlayerCache.java
public class OfflinePlayerMap {

	HashMap<String, UUID> offlinePlayerList = new HashMap<String, UUID>();
	static OfflinePlayerMap instance;
	
	public static OfflinePlayerMap getInstance() {
		if (instance == null) {
			synchronized (OfflinePlayerMap.class) {
				if (instance == null) {
					instance = new OfflinePlayerMap();
					
					for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
						instance.putInMap(offlinePlayer);
					}
				}
			}
		}
		return instance;
	}
	
	public void putInMap(OfflinePlayer offlinePlayer) {
		if (offlinePlayer.getUniqueId() != null && offlinePlayer.getName() != null) {
			instance.offlinePlayerList.put(offlinePlayer.getName(), offlinePlayer.getUniqueId());
		}
	}
	
	public UUID getUUID(String offlinePlayer) {
		return offlinePlayerList.get(offlinePlayer);
	}
	
	public OfflinePlayer getOfflinePlayer(String offlinePlayer) {
		return Bukkit.getOfflinePlayer(offlinePlayerList.get(offlinePlayer));
	}
	
	public int getSize() {
		return offlinePlayerList.size();
	}
}
