package me.Kevin_031.BurgerCore.Core;

/** Used for defining the type of the grant. */
public enum GrantEnum {
	// Development grants (range 0-9)
	EMPTY("None", 0, "&7"), // gray
	
	// Punishments (range 10-49)
	WARNING("Warning", 15, "&e"), // yellow
	TEMPMUTE("TempMute", 20, "&6"), // gold
	PERMMUTE("PermMute", 21, "&6&n"), // gold | underline
	TEMPBAN("TempBan", 30, "&c"), // red
	PERMBAN("PermBan", 31, "&c&n"), // red | underline
	BLACKLIST("Blacklist", 40, "&4&n"), // dark red | underline
	
	// Normal ranks (range 50-799)
	DEFAULT("Default", 55, "&7"), // gray
	VIP("Vip", 65, "&a"), // green
	ELITE("Elite", 75, "&6"), // gold
	ULTRA("Ultra", 85, "&d&l"), // light purple | bold
	LEGEND("Legend", 95, "&5&l"), // dark purple | bold
	MEDIA("Media", 150, "&9"), // blue
	
	// Lower staff ranks (800-899)
	BUILDER("Builder", 820, "&2"), // dark green
	TRIALMOD("TrialMod", 840, "&e"), // yellow
	MODERATOR("Mod", 850, "&3"), // dark aqua
	
	// Upper staff ranks (900-999)
	ADMIN("Admin", 940, "&c"), // red
	DEVELOPER("Developer", 960, "&b"), // aqua
	MANAGER("Manager", 980, "&c&l"), // red | bold
	OWNER("Owner", 990, "&4&l"); // dark red | bold
	
	// Open range, e.g. grants for prefixes (1000-intLimit)
	
	// Grant characteristics
	public String name;
	public int order;
	public String color;
	
	// Constructor
	GrantEnum(String name, int order, String color) {
		this.name = name;
		this.order = order;
		this.color = color;
	}
}
