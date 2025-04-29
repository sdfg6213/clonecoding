package com.yong.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class YongController extends HttpServlet{
	Map<String, CommandHandler> map;
	public YongController() {
		map = new HashMap<String, CommandHandler>();
	}
	@Override
	public void init() throws ServletException {
		String filePath = this.getServletContext().getRealPath("/WEB-INF/yongCommand.properties");
		Properties pr = new Properties();
		try {
			FileInputStream fis = new FileInputStream(filePath);
			pr.load(fis); //로드는 이닛에서!
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//로드했으면 키랑 밸류(문자열) 추출해야지
		Iterator keys = pr.keySet().iterator();
		while(keys.hasNext()) {
			String key = (String)keys.next();
			System.out.println("key:"+key);
			String value = pr.getProperty(key);
			//문자열 추출하면서 객체 생성해야지
			try {
				Class origin = Class.forName(value);
				CommandHandler temp = (CommandHandler)origin.newInstance();
				map.put(key, temp);
				System.out.println(temp);
				//(문자열이 아닌)객체 생성한 거 맵에 저장해야지
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		userProcess(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		userProcess(req,resp);
	}
	protected void userProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//이제 get이든 post든 여기로 진입함
		//String type= req.getParameter("type");
		//System.out.println(type);
		String urlCmd = req.getRequestURI();
		if(urlCmd.indexOf(req.getContextPath())==0) {
			urlCmd = urlCmd.substring(req.getContextPath().length());
		}
		
		CommandHandler cmd = map.get(urlCmd);
		
		/**
		
		CommandHandler cmd = null;
		if(type.equals("list")) {
			cmd = new ListAction();
		}else if(type.equals("write")) {
			cmd = new WriteAction();
		}else if(type.equals("content")) {
			cmd = new ContentAction();
		}*/
		
		
		String gopage = cmd.process(req, resp);
		RequestDispatcher rd = req.getRequestDispatcher(gopage);
		rd.forward(req, resp);
		
	}
	
}
