package com.xtgj.j2ee.chapter03.xtgj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//省略import

public class ValidateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 获取用户输入的日期
		String date = request.getParameter("birthday");
		// 验证日期有效性
		boolean valid = validate(date);
		String msg = "您输入了无效的日期";
		if (valid == true) {
			msg = "您输入的日期有效！";
		}
		// 设置响应格式和字符集
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		// 输出响应数据
		out.println("<response>");
		out.println("<message>" + msg + "</message>");
		out.println("<valid>" + valid + "</valid>");
		out.println("</response>");
		out.close();
	}

	// 验证日期方法
	private boolean validate(String date) {
		boolean valid = true;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			formatter.parse(date);
		} catch (ParseException e) {
			valid = false;
		}
		return valid;
	}
}
