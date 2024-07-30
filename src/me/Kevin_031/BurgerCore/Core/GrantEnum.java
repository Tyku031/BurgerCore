package me.Kevin_031.BurgerCore.Core;

/** Used for defining the type of the grant. */
public enum GrantEnum {
	// Empty, only used for development
	NONE,
	
	// Punishments
	WARNING,
	TEMPMUTE,
	PERMMUTE,
	TEMPBAN,
	PERMBAN,
	BLACKLIST,
	
	// Normal ranks
	DEFAULT,
	VIP,
	ELITE,
	ULTRA,
	LEGEND,
	MEDIA,
	
	// Lower staff ranks
	BUILDER,
	TRIALMOD,
	MODERATOR,
	
	// Upper staff ranks
	ADMIN,
	DEVELOPER,
	MANAGER,
	OWNER,
}
