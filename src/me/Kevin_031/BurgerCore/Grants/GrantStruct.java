package me.Kevin_031.BurgerCore.Grants;

import java.util.Date;
import java.util.UUID;

public class GrantStruct { //add silent grant?
	public GrantEnum GRANT;
	public UUID GIVEN_BY;
	public String REASON;
	public Date DATE_GIVEN;
	public Date EXPIRES_ON;
	public boolean IS_ACTIVE;
	
	public GrantStruct(GrantEnum grant, UUID given_by, String reason, Date date_given, Date expires_on, boolean is_active) {
		this.GRANT = grant;
		this.GIVEN_BY = given_by;
		this.REASON = reason;
		this.DATE_GIVEN = date_given;
		this.EXPIRES_ON = expires_on;
		this.IS_ACTIVE = is_active;
	}
}
