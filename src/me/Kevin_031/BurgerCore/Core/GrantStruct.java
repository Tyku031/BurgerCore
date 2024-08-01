package me.Kevin_031.BurgerCore.Core;

import java.util.Date;
import java.util.UUID;

public class GrantStruct { //add silent grant?
	public GrantEnum GRANT;
	public UUID GIVEN_BY;
	public String REASON;
	public Date DATE_GIVEN;
	public Date EXPIRES_ON;
}
