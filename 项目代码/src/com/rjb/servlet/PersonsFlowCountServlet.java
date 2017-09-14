package com.rjb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.rjb.util.AboutDate;
import com.rjb.util.DBBean;
import com.rjb.util.Return;
/**
 * 客流量统计
 * 
 * @author liying
 *
 */
public class PersonsFlowCountServlet extends HttpServlet
{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		if ("countByDay".equals(method)) {
			try
			{
				countByDay(req, resp);
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//按天统计客流量
		} 
		else if ("countByMonth".equals(method)) {
			try
			{
				countByMonth(req, resp);
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 按日统计客流量
	 * @throws ParseException 
	 * 
	 */
	private void countByDay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		//System.out.println(startTime+","+endTime);
		
		//将客户端传来的起止日期进行格式转化并计算时间差
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");  
	    Date d1=sdf.parse(startTime);  
	    Date d2=sdf.parse(endTime);
		int days= (int)((d2.getTime()-d1.getTime())/86400000 );
		System.out.println(days);
		
		//将起止时间转化为与数据库格式相同
		startTime = startTime.replace("/", "");
		endTime = endTime.replace("/", "");
		System.out.println(startTime+","+endTime);
		DBBean dbBean = new DBBean();
		int []personNum = new int[days+1];
		for(int i=0;i<=days;i++)
		{			
			String sql="select count(*) from wifimessage where time > "+startTime+"000000 and time < "+startTime+"999999";
			ResultSet rs = dbBean.executeQuery(sql);
			try
			{
				while(rs.next())
				{
					personNum[i] = rs.getInt(1);
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			System.out.println(personNum[i]);
			AboutDate aboutDate = new AboutDate();
			startTime = aboutDate.getAfterDate(startTime);
			//System.out.println(startTime);
			//System.out.println(sql);
		}
		
		Return result = new Return(days,personNum,null);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(result));
		
		resp.setContentType("text/javascript");
		resp.getWriter().print(mapper.writeValueAsString(result));
		
	}
	
	private void countByMonth(HttpServletRequest req, HttpServletResponse resp) throws ParseException, JsonGenerationException, JsonMappingException, IOException
	{
		System.out.println("22");
		req.setCharacterEncoding("utf-8");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		startTime=startTime.replace("/", "");
		endTime=endTime.replace("/", "");
		System.out.println(startTime+","+endTime);
		
		//根据客户端传来的起止月份计算时间差
		AboutDate aboutDate = new AboutDate(); 
		int months = aboutDate.getMonthDiff(startTime, endTime);
		System.out.println(months);
		DBBean dbBean = new DBBean();
		int []monthPersonNum = new int[months+1];
		String []allDate = new String[months+1];
		for(int i=0;i<=months;i++)
		{		
			allDate[i]=startTime;
			String sql="select count(*) from wifimessage where time > "+startTime+"00000000 and time < "+startTime+"99999999";
			ResultSet rs = dbBean.executeQuery(sql);
			try
			{
				while(rs.next())
				{
					monthPersonNum[i] = rs.getInt(1);
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			System.out.println(monthPersonNum[i]);
			startTime = aboutDate.getNextMonth(startTime);
			System.out.println(startTime);
			System.out.println(sql);
		}
		
		Return result = new Return(months,monthPersonNum,allDate);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(result));
		
		resp.setContentType("text/javascript");
		resp.getWriter().print(mapper.writeValueAsString(result));
	}
	
}
