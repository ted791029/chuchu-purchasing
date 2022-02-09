package com.front.chuchuPurchasingAgent.Controllers.ADLoginController;

import com.front.chuchuPurchasingAgent.Controllers.Form;

public class ADLoginForm implements Form{
	
	/**帳號**/
	private String account;
	/**密碼**/
	private String password;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
