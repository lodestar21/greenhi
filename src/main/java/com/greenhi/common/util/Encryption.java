package com.greenhi.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 암호화 처리 유틸
 * as-is util
 * @since 1.0
 */
public class Encryption {
	public final static String encrypt(String input) throws NoSuchAlgorithmException {
		return Encryption.encrypt(input.concat("amjw502"), "SHA-256");
	}

	public final static String encrypt(String input, String algorithm) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder( );
		MessageDigest md = MessageDigest.getInstance( algorithm );

		byte[] d = md.digest( input.getBytes( ) );

		for ( int i = 0 , len = d.length ; i < len ; i++ ) {
			byte t = d[ i ];
			String s = Integer.toHexString( t & 0xFF );
			while ( s.length( ) < 2 ) {
				s = "0" + s;
			}
			sb.append( s.substring( s.length( ) - 2 ) );
		}
		return sb.toString( );
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(Encryption.encrypt("abcd1234" + "amjw502", "SHA-256"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//eb6c40bfdbccce853b7d6c35ba12ffdb09b22d29ace82c3def724a4342691f31
		}
	}
}
