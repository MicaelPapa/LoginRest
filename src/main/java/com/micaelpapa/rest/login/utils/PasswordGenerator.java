package com.micaelpapa.rest.login.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class PasswordGenerator {
	@Autowired
	ApplicationContext context;

	public String encodePassword(String password) throws UnsupportedEncodingException {
		String salt = "1234";
		int iterations = 10000;
		int keyLength = 512;
		char[] passwordChars = password.toCharArray();
		byte[] saltBytes = salt.getBytes();
		byte[] hashedBytes = hashPassword(passwordChars, saltBytes, iterations, keyLength);
		return Hex.encodeHexString(hashedBytes);
	}

	private byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {

		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
			SecretKey key = skf.generateSecret(spec);
			byte[] res = key.getEncoded();
			return res;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}
}