package com.glnetcomsolutions.web;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.glnetcomsolutions.report.DBConnection;
import com.glnetcomsolutions.report.LicenseBean;

public class LicenceChecker {

	public static void addFirstTimeRecord(){
		
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(generateKey(decrypt("oIzpgDQqrvoyv24SFyhFcg=="),3));

	}
	public static String getDemoLicense(String keyBase){
		try {
			return generateKey(keyBase,0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static LicenseBean getLicenseBean(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LicenseBean bean = null;
		try{
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM LICENSE");
			rs = ps.executeQuery();
			while(rs.next()){
				bean = new LicenseBean();
				bean.setCustomerName(rs.getString("CUSTOMERNAME"));
				bean.setOrganization(rs.getString("ORGANIZATION"));
				bean.setPhoneno(rs.getString("PHONENO"));
				bean.setEmailId(rs.getString("EMAILID"));
				bean.setKeyBase(rs.getString("KEYBASE"));
				bean.setLicenseKey(rs.getString("LICENSEKEY"));
				bean.setLicenseType(rs.getString("LICENSETYPE"));
				bean.setInvoiceCount(rs.getInt("INVOICECOUNT"));
				bean.setUserCount(rs.getInt("USERCOUNT"));
				bean.setExpirationTime(rs.getString("EXPIRATIONTIME"));
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}
	public static LicenseBean saveLicenseBean(LicenseBean bean){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			if("insert".equals(bean.getOperation())){
				ps = conn.prepareStatement("INSERT INTO  LICENSE(CUSTOMERNAME,ORGANIZATION," +
						"PHONENO,EMAILID,KEYBASE,LICENSEKEY,LICENSETYPE,INVOICECOUNT,USERCOUNT," +
						"EXPIRATIONTIME)VALUES(?,?,?,?,?,?,?,?,?,?)");
			}else{
				ps = conn.prepareStatement("UPDATE LICENSE SET CUSTOMERNAME=?,ORGANIZATION=?," +
						"PHONENO=?,EMAILID=?,KEYBASE=?,LICENSEKEY=?,LICENSETYPE=?,INVOICECOUNT=?,USERCOUNT=?," +
						"EXPIRATIONTIME=?");
			}
			
			
			int i = 0;
				ps.setString(++i, bean.getCustomerName());
				ps.setString(++i, bean.getOrganization());
				ps.setString(++i, bean.getPhoneno());
				ps.setString(++i, bean.getEmailId());
				ps.setString(++i, bean.getKeyBase());
				ps.setString(++i, bean.getLicenseKey());
				ps.setString(++i, bean.getLicenseType());
				ps.setInt(++i, bean.getInvoiceCount());
				ps.setInt(++i,bean.getUserCount());
				ps.setString(++i,bean.getExpirationTime());
				System.out.println(ps);
				ps.execute();
				System.out.println("License Saved Successfully");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}
	private static long getFutureTimeStam(int days){
		
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.DATE, days );	
		return cal.getTime().getTime();
		
	}
	
	public static boolean checkForLicense() {
		
		boolean valid = false;
		LicenseBean bean = getLicenseBean();
		if(bean != null){
			long currentTime = System.currentTimeMillis();
			long installationTime = Long.parseLong(bean.getKeyBase());
			long expirationTime = Long.parseLong(bean.getExpirationTime());
			int totalInvoiceCount = bean.getInvoiceCount();
			int generatedInvoice = InvoiceMaster.getInvoiceCount();
			if(installationTime < currentTime && expirationTime > currentTime && totalInvoiceCount-generatedInvoice > 0){
				valid = true;
			}
		}  
		return valid;
	}
	
	static Cipher cipher;
	static byte[] keyBytes = "mnbvcxzpoiuytrew".getBytes();
	
	public static String decrypt(String encryptedText)
			throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
		cipher = Cipher.getInstance("AES");
		byte[] encryptedTextByte = Base64.decodeBase64(encryptedText.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
	
	public static String encrypt(String plainText)
			throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
		cipher = Cipher.getInstance("AES");
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		encryptedByte =	Base64.encodeBase64(encryptedByte);
		String encryptedText = new String(encryptedByte);
		return encryptedText;
	}
	public static String generateKey(String keyBase, int licenseType) throws Exception{
		
		/*
		 * 0 = DEMO
		 * 1 = Hatching 
		 * 2 = Single User
		 * 3 = 2 User
		 * 4 = Multi-User-
		 */
//		String softwareKey = "SI_"+keyBase+"_1433656098098_"+licenseType+"_0_0";
		StringBuffer sb = new StringBuffer();
		sb.append("SI_");
		sb.append(keyBase+"_");
		
		switch (licenseType) {
		case 0:
			sb.append(getFutureTimeStam(15));
			sb.append("_0_50_2");
			break;
		case 1:
			sb.append(getFutureTimeStam(365));
			sb.append("_1_3000_2");
			break;
		case 2:
			sb.append(getFutureTimeStam(365*20));
			sb.append("_2_50000_2");
			break;
		case 3:
			sb.append(getFutureTimeStam(365*20));
			sb.append("_3_50000_2");
			break;
		case 4:
			sb.append(getFutureTimeStam(365*20));
			sb.append("_4_150000_2");
			break;

		default:
			break;
		}
		  
		
//		SOFTWARE-CODE_KEYBASE_NEWKEYBASE_LICENSETYPE_INVOICECOUNT_USERCOUNT
		
		
		
		String encryptedText = encrypt(sb.toString());
		return encryptedText;
		
	}
public static String getLicenseTypeInString(int licenseType) throws Exception{
		
		/*
		 * 0 = DEMO
		 * 1 = Hatching 
		 * 2 = Single User
		 * 3 = 2 User
		 * 4 = Multi-User-
		 */
//		String softwareKey = "SI_"+keyBase+"_1433656098098_"+licenseType+"_0_0";
		String licenseTypeInString = "Demo";		
		switch (licenseType) {
		case 0:
			licenseTypeInString = "Demo";
			break;
		case 1:
			licenseTypeInString = "Hatching";
			break;
		case 2:
			licenseTypeInString = "Single User";
			break;
		case 3:
			licenseTypeInString = "2 User";
			break;
		case 4:
			licenseTypeInString = "Multi User Unlimited";
			break;
		default:
			break;
		}
		  
		return licenseTypeInString;
		
	}
	public static boolean setLicenseInBean(LicenseBean bean, String keyBase, String key) throws NoSuchAlgorithmException, Exception{
		boolean flag = false;
		
		flag = validateLicence(decrypt(keyBase), key);	
		bean.setKeyBase(decrypt(keyBase));
		bean.setLicenseKey(key);
		if(flag){
			String plainKey = decrypt(key);
			String[] keyParts = plainKey.split("_");
			bean.setExpirationTime(keyParts[2]);
			bean.setLicenseType(keyParts[3]);
			bean.setInvoiceCount(Integer.parseInt(keyParts[4]));
			bean.setUserCount(Integer.parseInt(keyParts[5]));
		}
		return flag;
	}
	
	public static boolean validateLicence(String keyBase, String key) throws NoSuchAlgorithmException{
		boolean flag = false;
		try {
			String plainKey = decrypt(key);
			System.out.println("KeyBase="+keyBase);
			System.out.println("Key="+plainKey);
			String[] keyParts = plainKey.split("_");
			if(keyParts[0].equalsIgnoreCase("SI")){
				if(keyParts[1].equalsIgnoreCase(keyBase)){
					flag = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
}
