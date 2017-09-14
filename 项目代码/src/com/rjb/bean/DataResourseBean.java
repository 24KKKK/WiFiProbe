package com.rjb.bean;

public class DataResourseBean
{
	private String messageFromByMac;	//信息来源（探针）mac地址
	private String messageFromByAddress;//信息采集地
	private String phoneMac;			//探测到手机的mac
	private String time;				//检测到的时刻
	private String rang;				//与探针的距离
	
	public String getMessageFromByMac()
	{
		return messageFromByMac;
	}
	public String getMessageFromByAddress()
	{
		return messageFromByAddress;
	}
	public String getPhoneMac()
	{
		return phoneMac;
	}
	public String getTime()
	{
		return time;
	}
	public String getRang()
	{
		return rang;
	}
	public void setMessageFromByMac(String messageFromByMac)
	{
		this.messageFromByMac = messageFromByMac;
	}
	public void setMessageFromByAddress(String messageFromByAddress)
	{
		this.messageFromByAddress = messageFromByAddress;
	}
	public void setPhoneMac(String phoneMac)
	{
		this.phoneMac = phoneMac;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public void setRang(String rang)
	{
		this.rang = rang;
	}
	
}
