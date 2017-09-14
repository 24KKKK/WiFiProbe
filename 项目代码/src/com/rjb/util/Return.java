package com.rjb.util;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Return
{
	public Return(int timeDif,int []personNum, String []allDateStrings)
	{
		super();
		this.timeDif = timeDif;
		this.personNum = personNum;
		this.allDateStrings=allDateStrings;
	}

	private int timeDif;
	private int []personNum = new int[1000];
	private String []allDateStrings=new String[1000];
	
	public int[] getPersonNum()
	{
		return personNum;
	}
	
	public int gettimeDif()
	{
		return timeDif;
	}

	public void settimeDif(int timeDif)
	{
		this.timeDif = timeDif;
	}

	public String[] getAllDateStrings()
	{
		return allDateStrings;
	}

	public void setAllDateStrings(String[] allDateStrings)
	{
		this.allDateStrings = allDateStrings;
	}
	
	
}
