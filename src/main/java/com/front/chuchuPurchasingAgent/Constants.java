package com.front.chuchuPurchasingAgent;

public class Constants {
	//PTT 優惠馬
	public final static String PTT_CODE = "FROM_PTT";
	//session key
	public final static String MEMBER_SESSION_KEY = "pa_member";
	public final static String ADMIN_SESSION_KEY = "pa_admin";
	//路徑
	//前台
	public final static String INDEX_URI = "/front/index";
	public final static String INTRO_INDEX_URI = "/front/member/index";
	public final static String LOGIN_URI = "/front/login/index";
	public final static String QUERY_VIEW_URI = "/front/query/view";
	public final static String ORDER_VIEW_URI = "/front/order/view";
	//後台
	public final static String ADMIN_LOGIN_URI = "/chuAdmin/login/index";
	public final static String ADMIN_INTRO_INDEX_URI = "/chuAdmin/index";
	
	//狀態
	public final static String STATUS_ENABLE = "1"; //開啟
	public final static String STATUS_DISABLE = "0"; //關閉
	
	public final static String[] HAVE_DISCOUNT_COUNTRY = {"001"};
	//001 日本
	
	//顧客來源
	public final static String FROM_PTT = "001"; //PTT
	public final static String FROM_OTHER = "999"; //其他地方
	
	//客戶折扣類型
	public final static String CUSTOMER_TYPE_NORMAL = "001";
	public final static String CUSTOMER_TYPE_OLD = "002";
	public final static String CUSTOMER_TYPE_VIP = "003";
	
	//VIP基準
	public final static int VIP_STANDARD = 30000;
	
	//手續費型別
	public final static String LOCAL_FEE_TYPE_BYPRODUCT = "001";
	public final static String LOCAL_FEE_TYPE_BYWEB = "002";
	
	//手續費基準
	public final static int LOCAL_FEE_TYPE_BYWEB_STANDARD = 180;
	public final static int LOCAL_FEE_TYPE_BYPRODUCT_STANDARD = 180;
	
	//訂單狀態
	public final static String ORDER_STATUS_CREATED =  "001"; //訂單成立
	public final static String ORDER_STATUS_REMITTED_FRIST_STAGE =  "002"; //收到第一階段匯款
	public final static String ORDER_STATUS_PLACE =  "003"; //訂單下訂完成
	public final static String ORDER_STATUS_WEB_BEEN_SHIPPED =  "004"; //網站出貨
	public final static String ORDER_STATUS_ARRIVE_STOREHOUSE =  "005"; //當地倉庫收貨
	public final static String ORDER_STATUS_REMITTED_SECOND_STATE =  "006"; //收到第二階段匯款
	public final static String ORDER_STATUS_TO_HONG_KONG =  "007"; //前往香港
	public final static String ORDER_STATUS_ARRIVE_HONG_KONG =  "008"; //抵達香港
	public final static String ORDER_STATUS_TO_TAIWAN =  "009"; //前往台灣
	public final static String ORDER_STATUS_ARRIVE_TAIWAN = "010"; //抵達台灣
	public final static String ORDER_STATUS_ADMIN_BEEN_SHIPPED =  "011"; //站主出貨
}
