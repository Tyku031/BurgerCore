package me.Kevin_031.BurgerCore.Ranks;

import org.bukkit.entity.Player;

import me.Kevin_031.BurgerCore.Utilities.Utilities;

public class RanksPrefixHandler {

	RanksPlayerHandler rph = new RanksPlayerHandler();
	
	String pDEFAULT = "&7[Default] &f";
	String pVIP = "&a[Vip] &f";
	String pELITE = "&6[Elite] &f";
	String pULTRA = "&d&l[Ultra] &r&f";
	String pLEGEND = "&5&l[Legend] &r&f";
	String pMEDIA = "&9[Media] &f";
	String pBUILDER = "&2[Builder] &f";
	String pTRIALMOD = "&e[Trainee] &f";
	String pMODERATOR = "&3[Moderator] &f";
	String pADMIN = "&c[Admin] &f";
	String pDEVELOPER = "&b[Developer] &f";
	String pMANAGER = "&c&l[Manager] &r&f";
	String pOWNER = "&6&l[Owner] &r&f";
	
	public void setupPrefix(Player p) {
		Integer highestRank = rph.getHighestRank(p);
		String prefix = null;
		switch (highestRank) {
		case 0:
			prefix = pDEFAULT;
		case 10:
			prefix = pVIP;
		case 20:
			prefix = pELITE;
		case 30:
			prefix = pULTRA;
		case 40:
			prefix = pLEGEND;
		case 50:
			prefix = pMEDIA;
		case 60:
			prefix = pBUILDER;
		case 65:
			prefix = pTRIALMOD;
		case 70:
			prefix = pMODERATOR;
		case 80:
			prefix = pADMIN;
		case 85:
			prefix = pDEVELOPER;
		case 90:
			prefix = pMANAGER;
		case 100:
			prefix = pOWNER;
		}
		p.setDisplayName(Utilities.chat(prefix + p.getName()));
		p.setPlayerListName(Utilities.chat(prefix + "&7" + p.getName()));
	}
}
