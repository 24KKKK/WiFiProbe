package com.rjb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AboutDate
{
	public String getAfterDate(String startTime) throws ParseException
	{
		String date2="";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
		Date dt = df.parse(startTime);
		date2 = df.format(new Date(dt.getTime()+ (long)24 * 60 * 60 * 1000));// 一个月后的时间
		return date2;
	}
	public int getMonthDiff(String date1,String date2)
	{
		String strdate1year = date1.substring(0, 4);
		String strdate1month = date1.substring(4, 6);
		int date1year = Integer.parseInt(strdate1year);
		int date1month = Integer.parseInt(strdate1month);

		String strdate2year = date2.substring(0, 4);
		String strdate2month = date2.substring(4, 6);
		int date2year = Integer.parseInt(strdate2year);
		int date2month = Integer.parseInt(strdate2month);

		int monthdiff = 12 * (date2year - date1year)
				+ (date2month - date1month);
		return monthdiff;
	}
	public String getNextMonth(String date)
	{
		String nextMonth = "";
		String strdate1year = date.substring(0, 4);
		String strdate1month = date.substring(4, 6);
		int date1year = Integer.parseInt(strdate1year);
		int date1month = Integer.parseInt(strdate1month);
		if(date1month<12)
		{
			date1month=date1month+1;
			if(date1month<10)
			{
				strdate1year=strdate1year+"0";
			}
			nextMonth=strdate1year+date1month;
		}
		else{
			date1year=date1year+1;
			nextMonth=date1year+"01";
		}
		return nextMonth;
	}
}




