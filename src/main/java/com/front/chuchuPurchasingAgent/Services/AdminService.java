package com.front.chuchuPurchasingAgent.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.Repositories.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	/**
	 * 取得單一
	 * @param admin
	 * @return
	 */
	public Optional<Admin> findOne(Admin admin){
		Example<Admin> adminEx = Example.of(admin);
		return adminRepository.findOne(adminEx);
	}
}
