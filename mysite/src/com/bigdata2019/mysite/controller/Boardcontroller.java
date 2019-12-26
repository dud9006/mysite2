package com.bigdata2019.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bigdata2019.mysite.repository.BoardDao;
import com.bigdata2019.mysite.repository.UserDao;
import com.bigdata2019.mysite.vo.BoardVo;
import com.bigdata2019.mysite.vo.UserVo;
import com.bigdata2019.mysite.web.util.WebUtil;

public class Boardcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("a");
		if("write".equals(action)) {
			//접근제어
			HttpSession session = request.getSession();
			if (session == null) {
				WebUtil.redirect(request, response, request.getContextPath());
				return;
			}
			
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			if(authUser == null) {
				WebUtil.redirect(request, response, request.getContextPath());
				return;
			}
			Long no = authUser.getNo();
			UserVo UserVo = new UserDao().find(no);
			
			request.setAttribute("userVo", UserVo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");				
			
		} else if("view".equals(action)) {
			WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");	
		} else if ("submit".equals(action)) {
			
			String title = request.getParameter("title");
			String contents = request.getParameter("content");
			
			HttpSession session = request.getSession();
			if (session == null) {
				WebUtil.redirect(request, response, request.getContextPath());
				return;
			}
			
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			if(authUser == null) {
				WebUtil.redirect(request, response, request.getContextPath());
				return;
			}
			
			
			Long no = authUser.getNo();
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setUserNo(no);
			
			new BoardDao().insert(vo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		}
		
		
		else {			
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
