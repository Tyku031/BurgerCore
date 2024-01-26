package me.Kevin_031.BurgerCore.Utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bukkit.ChatColor;

public class Utilities {

	//chatcolor
	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	//time
	public static Calendar calendar() {
		Calendar calendar = new GregorianCalendar();
		return calendar;
	}
	
	public static Date date(Calendar cal) {
		Date date = cal.getTime();
		return date;
	}
	
	public static String time(Date date) {
		String time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
		return time;
	}
	
	public static Calendar datePlusTime(Calendar cal, Integer timeInSeconds) {
		cal.add(Calendar.SECOND, timeInSeconds);
		return cal;
	}
}
