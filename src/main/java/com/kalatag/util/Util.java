package com.kalatag.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class Util {

	
	public static String readFile(String path, Charset encoding) throws IOException {
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
	}
	
	public static String generateRandomPassword() {
		String plain = RandomStringUtils.randomAlphabetic(8);
		return plain;
	}


	public static String toSHA256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		return toString(byteData);

	}

	public static String toString(byte[] byteData) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}

	public static String hash(String fileName, int buff)
			throws NoSuchAlgorithmException, IOException {
		try {
			RandomAccessFile file = new RandomAccessFile(fileName, "r");

			MessageDigest hashSum = MessageDigest.getInstance("SHA-256");

			byte[] buffer = new byte[buff];

			long read = 0;

			// calculate the hash of the hole file for the test
			long offset = file.length();
			int unitsize;
			while (read < offset) {
				unitsize = (int) (((offset - read) >= buff) ? buff
						: (offset - read));
				file.read(buffer, 0, unitsize);

				hashSum.update(buffer, 0, unitsize);

				read += unitsize;
			}

			file.close();
			byte[] partialHash = new byte[hashSum.getDigestLength()];
			partialHash = hashSum.digest();

			return toString(partialHash);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getCurrentUserName() {
		return ((User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()).getUsername();
	}

	public static int getRandom(int lowerBound, int upperBound) {
		return lowerBound + (int) (Math.random() * (upperBound - lowerBound));
	}

	public static String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 1 && num <= 12) {
			month = months[num - 1];
		}
		return month;
	}
	
	public static Date getDateWithoutTime(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal.getTime();
	}

	public static Date getTomorrowDate(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DATE, 1);
	    return cal.getTime();
	}

}
