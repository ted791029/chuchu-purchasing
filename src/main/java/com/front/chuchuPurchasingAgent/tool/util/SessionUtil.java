package com.front.chuchuPurchasingAgent.tool.util;

import javax.servlet.http.HttpServletRequest;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.Models.Member;

public class SessionUtil {
	
	/**
	 * 將會員資訊放至session
	 * 
	 * @param request
	 * @param headerTasks 
	 * @param user
	 * @throws Exception 
	 */
	public static void setMember(HttpServletRequest request, Member member) throws Exception {
		request.getSession().setAttribute(Constants.MEMBER_SESSION_KEY, member);
	}
	
	/**
	 * 取得會員資訊
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static Member getMember(HttpServletRequest request) throws Exception {
		Member member = (Member)request.getSession().getAttribute(Constants.MEMBER_SESSION_KEY);
		return member;
	}
	
	/**
	 * 移除會員資訊
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static void removeMember(HttpServletRequest request) throws Exception {
		request.getSession().removeAttribute(Constants.MEMBER_SESSION_KEY);
	}
	/**
	 * 將管理者資訊放至session
	 * @param request
	 * @param admin
	 */
	public static void setAdmin(HttpServletRequest request, Admin admin) {
		request.getSession().setAttribute(Constants.ADMIN_SESSION_KEY, admin);
	}
	/**
	 * 取得管理者資訊
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static Admin getAdmin(HttpServletRequest request) throws Exception {
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
		return admin;
	}
	
	/**
	 * 移除管理者資訊
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static void removeAdmin(HttpServletRequest request) throws Exception {
		request.getSession().removeAttribute(Constants.ADMIN_SESSION_KEY);
	}
}
