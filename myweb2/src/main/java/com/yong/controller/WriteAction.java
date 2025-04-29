package com.yong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteAction implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) {
		String result = "이것은 커맨드 패턴을 적용한 write 결과입니다";
		req.setAttribute("result", result);
		return "/write.jsp";
	}

}
