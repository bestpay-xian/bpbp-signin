package com.bestpay.bpbp.signin.common;

import java.security.MessageDigest;

public class PassTools {
	/**
	 * encryptStrinrgByMD5
	 * 
	 * @param str
	 * @return
	 */
	public static String encryptStrinrgByMD5(String str) {
		String Digest = null;
		byte data[] = str.getBytes();
		try {
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update(data);
			byte digest[] = alg.digest();
			Digest = byte2hex(digest);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return Digest;
	}

	public static String byte2hex(byte b[]) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs;
		}
		return hs.toUpperCase();
	}
	public static void main(String[] arg){
		System.out.println(encryptStrinrgByMD5("123456"));
	}
}
