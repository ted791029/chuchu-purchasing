package com.front.chuchuPurchasingAgent.Controllers.RegisterController;

import com.front.chuchuPurchasingAgent.Controllers.Form;

/**
 * 登入頁面參數
 *
 */
public class RegisterForm implements Form{
	/**帳號**/
	private String account;
	/**密碼**/
	private String password;
	/**確認密碼**/
	private String checkPassword;
	
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
	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	
}
