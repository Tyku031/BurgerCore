package me.Kevin_031.BurgerCore.Grants;

public class RanksPrefixHandler {
	
	// This whole class is probably redundant lmao
	
	String pDEFAULT = "&7[Default] &f"; // gray
	String pVIP = "&a[Vip] &f"; // green
	String pELITE = "&6[Elite] &f"; // gold
	String pULTRA = "&d&l[Ultra] &r&f"; // light purple | bold
	String pLEGEND = "&5&l[Legend] &r&f"; // dark purple | bold
	String pMEDIA = "&9[Media] &f"; // blue
	String pBUILDER = "&2[Builder] &f"; // dark green
	String pTRIALMOD = "&e[Trainee] &f"; // yellow
	String pMODERATOR = "&3[Moderator] &f"; // dark aqua
	String pADMIN = "&c[Admin] &f"; // red
	String pDEVELOPER = "&b[Developer] &f"; // aqua
	String pMANAGER = "&c&l[Manager] &r&f"; // red | bold
	String pOWNER = "&6&l[Owner] &r&f"; // gold | bold
	
	/*
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
	*/
}
