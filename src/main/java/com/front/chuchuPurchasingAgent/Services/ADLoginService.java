package com.front.chuchuPurchasingAgent.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.ModelBuilders.AdminBuilder;
import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.tool.util.DataEncrypt;

@Service
public class ADLoginService {
	@Autowired
	private AdminService adminService;
	
	/***
	 * 登入
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Optional<Admin> login(String account, String password) throws Exception{
		Admin admin = new AdminBuilder()
				.buildAccount(account)
				.buildPassword(DataEncrypt.md5(password))
				.build();
		return adminService.findOne(admin);
	}
}
