package com.front.chuchuPurchasingAgent.tool.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public final static String ERROR_IMAGE_CONTENT_TYPE = "圖檔類型只能上傳jpg、png、gif!";
	public final static String ERROR_IMAGE_LIMIT_SIZE = "圖片不能超過2MB!";
	public final static String ERROR_FILES_CONTENT_TYPE = "檔案類型只能上傳pdf、doc、docx、xls、xlsx、ppt、pptx!";
	public final static String ERROR_FILES_LIMIT_SIZE = "檔案不能超過25MB!";
	public final static String ERROR_ANNEX_CONTENT_TYPE = "檔案類型只能上傳jpg, gif, png, docx, xlsx, pptx, pdf!";
	public final static String ERROR_ANNEX_LIMIT_SIZE = "檔案不能超過2MB!";
	
	/**
	 * 檔案上傳-自動產生檔案名稱
	 * @param file
	 * @param uploadFilePath
	 * @return
	 * @throws Exception
	 */
	public static String getUploadFileName(MultipartFile file, String uploadFilePath) throws Exception {
		return getUploadFileName(file, uploadFilePath, null);
	}

	/**
	 * 檔案上傳-自定檔案名稱
	 * @param file
	 * @param uploadFilePath
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static String getUploadFileName(MultipartFile file, String uploadFilePath, String id) throws Exception {
		String originalFilename = file.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
		String fileName = Id.dateId20() + extName;
		if (id != null)
			fileName = id;

		File toFile = new File(WebUtil.removeControlCharacter(uploadFilePath),
				WebUtil.removeControlCharacter(fileName));
		if ((null != toFile.getParentFile()) && (!toFile.getParentFile().exists())) {
			toFile.getParentFile().mkdirs();
		}
		file.transferTo(toFile);
		return fileName;
	}

	/**
	 * 檔案上傳-原始檔案名稱
	 * @param file
	 * @param uploadFilePath
	 * @return
	 * @throws Exception
	 */
	public static String getUploadFileOriginalName(MultipartFile file, String uploadFilePath) throws Exception {
		String fileName = file.getOriginalFilename();
		File toFile = new File(WebUtil.removeControlCharacter(uploadFilePath),
				WebUtil.removeControlCharacter(fileName));
		if ((null != toFile.getParentFile()) && (!toFile.getParentFile().exists())) {
			toFile.getParentFile().mkdirs();
		}
		file.transferTo(toFile);
		return fileName;
	}

	/**
	 * @param fromFileName
	 * @param toFileName
	 * @throws Exception
	 */
	public static void copy(String fromFileName, String toFileName) throws Exception {
		copy(new File(fromFileName), new File(toFileName));
	}

	/**
	 * @param fromFile
	 * @param toFile
	 * @throws Exception
	 */
	public static void copy(File fromFile, File toFile) throws Exception {
		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(fromFile);
			// create destination directory automatically if necessary
			if ((toFile.getParentFile() != null) && (!toFile.getParentFile().exists())) {
				toFile.getParentFile().mkdirs();
			}
			to = new FileOutputStream(toFile);
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = from.read(buffer)) != -1) {
				to.write(buffer, 0, bytesRead);
			} // write
		} finally {
			if (from != null) {
				try {
					from.close();
				} catch (IOException e) {
				}
			}
			if (to != null) {
				try {
					to.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * @param from
	 * @param toFile
	 * @throws Exception
	 */
	public static void copy(InputStream from, File toFile) throws Exception {
		FileOutputStream to = null;
		try {
			// create destination directory automatically if necessary
			if ((toFile.getParentFile() != null) && (!toFile.getParentFile().exists())) {
				toFile.getParentFile().mkdirs();
			}
			to = new FileOutputStream(toFile);
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = from.read(buffer)) != -1) {
				to.write(buffer, 0, bytesRead);
			} // write
		} finally {
			if (from != null) {
				try {
					from.close();
				} catch (IOException e) {
				}
			}
			if (to != null) {
				try {
					to.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * @param file
	 * @throws Exception
	 */
	public static void createPathIfMissing(File file) throws Exception {
		if ((null != file.getParentFile()) && (!file.getParentFile().exists())) {
			file.getParentFile().mkdirs();
		}
	}

	/**
	 * @param parentPath
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String parentPath, String filePath) {
		File file = new File(parentPath, filePath);
		if (file.exists())
			if (file.delete())
				return true;
		return false;
	}

	/**
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists())
			if (file.delete())
				return true;
		return false;
	}
	/**
	 * 檔案上傳
	 * 
	 * @param file
	 * @param uploadPath
	 * @return
	 */
	public static String uploadFile(MultipartFile file, String uploadPath) {

		String fileName = null;
		try {
			String originalFilename = file.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
			fileName = Id.id20() + extName;
			String realPath = uploadPath;

			File toFile = new File(realPath, fileName);
			if ((null != toFile.getParentFile()) &&
					(!toFile.getParentFile().exists())) {
				toFile.getParentFile().mkdirs();
			}

			file.transferTo(toFile);
		} catch (Exception e) {
			// logger.error("Upload image file error:" + e.getMessage(), e);
			e.printStackTrace();
		} 
		return fileName;
		
	}
	/**
	 * 判斷檔案是否在限制大小內
	 * 
	 * @param file
	 * @return Boolean
	 * @throws Exception
	 */
	public final static boolean inlimitSize(MultipartFile file,long maxMB) throws Exception {
		return (file.getSize() <= (maxMB<<20)) ? true : false;
	}
	

	/**
	 * 判斷圖片ContnetType
	 * 
	 * @param contentType
	 * @return Boolean
	 */
	public static boolean isImage(String contentType) {
		final String[] limitContentType = { "image/png", "image/jpeg","image/gif" };
		for (String allowType : limitContentType) {
			if (contentType.equals(allowType))
				return false;
		}
		return true;
	}
	
	/**
	 * 判斷文件ContnetType
	 * 
	 * @param contentType
	 * @return Boolean
	 */
	public static boolean isDocument(String contentType) {
		final String[] limitContentType = { "application/msword", "application/vnd.ms-excel", "application/vnd.ms-powerpoint", "application/pdf" };
		for (String allowType : limitContentType) {
			if (contentType.equals(allowType))
				return false;
		}
		return true;
	}
	

	/**
	 * 判斷附件ContnetType
	 * 
	 * @param contentType
	 * @return Boolean
	 */
	public static boolean isAnnex(String contentType) {
		final String[] limitContentType = { "image/png", "image/jpeg","image/gif","application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document"
				, "application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation", "application/pdf" };
		for (String allowType : limitContentType) {
			if (contentType.equals(allowType))
				return false;
		}
		return true;
	}
	
	public static byte[] read(File file) throws Exception {
	    ByteArrayOutputStream ous = null;
	    InputStream ios = null;
	    try {
	        byte[] buffer = new byte[4096];
	        ous = new ByteArrayOutputStream();
	        ios = new FileInputStream(file);
	        int read = 0;
	        while ( (read = ios.read(buffer)) != -1 ) {
	            ous.write(buffer, 0, read);
	        }
	    } finally { 
	        try {
	             if ( ous != null ) 
	                 ous.close();
	        } catch ( IOException e) {
	        }

	        try {
	             if ( ios != null ) 
	                  ios.close();
	        } catch ( IOException e) {
	        }
	    }
	    return ous.toByteArray();
	}
}
