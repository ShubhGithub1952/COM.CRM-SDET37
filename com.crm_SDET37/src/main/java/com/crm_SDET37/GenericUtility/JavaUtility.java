package com.crm_SDET37.GenericUtility;

import java.util.Date;
import java.util.Random;

public class JavaUtility {
	/**
	 * to get the Random Number
	 * @return
	 */
	public int getRandomNum() {
		Random random = new Random();
		int randNumb = random.nextInt(100);
		return randNumb;
	}
	/**
	 * to get System Date and Time
	 * @return
	 */
	public String getSystemDate() {
		Date date = new Date();
		String dateTime = date.toString();
		return dateTime;
	}
	public String getSystemDateInFormat() {
		Date date1 = new Date();
		String dateTime1 = date1.toString();
		String[] dateArr = dateTime1.split(" ");
		int month = date1.getMonth()+1;
		String date = dateArr[2];
		String year = dateArr[5];
		int day = date1.getDay();
		String time = dateArr[3];
		String finalFormat = month+" "+date+" "+year+" "+day+" "+time;
		return finalFormat;
	}
}
