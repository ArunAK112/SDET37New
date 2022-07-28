package com.lexnod.genericLib;

import java.util.Date;
import java.util.Random;
/**
 * 
 * @author ARUN
 *
 */

public class JavaUtility {
	/**
	 * To get random number
	 * 
	 * @param number
	 * @return
	 */

	public int getRandonNumber(int number) {
		Random random = new Random();
		return random.nextInt(number);

	}

	/**
	 * to get system date and time
	 * 
	 * @return
	 */
	public String getSystemDate() {
		Date date = new Date();
		return date.toString();

	}

	/**
	 * to get system date and time in order
	 * 
	 * @return
	 */

	public String getSystemDateInFormat() {
		Date date = new Date();
		String dateTime = date.toString();
		String[] dateArr = dateTime.split(" ");

		int month = date.getMonth() + 1;
		String datte = dateArr[2];
		String year = dateArr[5];
		int day = date.getDay();
		String time = dateArr[3];

		return month + " " + date + " " + year + " " + day + " " + time;
	}

}
