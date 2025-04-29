package com.yong.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface CommandHandler {
	
	public String process(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException;

}
