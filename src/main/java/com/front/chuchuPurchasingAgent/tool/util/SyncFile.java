package com.front.chuchuPurchasingAgent.tool.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * 建立日期：2017/3/1
 * 程式摘要：com.mdbs.util<P> 
 * 類別名稱：SyncFile.java<P>
 * 程式內容說明：<P>
 * @author claud
 * @version 1.0 
 * @since 1.0
 *
 */
public class SyncFile {


	/**
	 * 
	 * @param syncPath
	 */
	public static void syncFile(String syncPath){
		Runtime runtime = Runtime.getRuntime();
        Process process;
		try {
			LogUtil.setActionLog("SyncFile", "syncPath:"+syncPath);
			String[] command_ary = { "/bin/sh", "-c", syncPath };
			LogUtil.setActionLog("SyncFile", "START:"+command_ary.toString());
			process = runtime.exec(command_ary);
			BufferedWriter buffOut = new BufferedWriter(
	                                 new OutputStreamWriter(
	                                        process.getOutputStream()));
	        BufferedReader buffIn = new BufferedReader(
	                                new InputStreamReader(
	                                        process.getInputStream()));
	        buffOut.flush(); // Ensure that the output reaches the process
	        String s;
	        if((s=buffIn.readLine())!= null){
	        }
	 		try {
	 			if (process.waitFor() != 0) {
	 			}
	        } catch (InterruptedException e) {
	        	LogUtil.setErrorLog( "SyncFile", e);
			} finally {
				buffOut.close();
		        buffIn.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			LogUtil.setErrorLog("SyncFile", e1);
		}
		LogUtil.setActionLog("SyncFile", "END");
	}
}
