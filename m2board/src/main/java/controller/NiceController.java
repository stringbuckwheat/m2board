package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.INiceService;
import service.NiceService;
import vo.Member;
import vo.Nice;

@WebServlet("/niceController")
public class NiceController extends HttpServlet {
	private INiceService niceService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") == null) {
			// 로그인 안 된 상태
			response.sendRedirect(request.getContextPath() + "/index"); // 에서 로그인폼으로
			return;
		}
		
		Nice nice = new Nice();		
		String id = ((Member) session.getAttribute("loginMember")).getId();
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		nice.setId(id);
		nice.setBoardNo(boardNo);
		
		niceService = new NiceService();
		niceService.getNice(nice);

		response.sendRedirect(request.getContextPath() + "/boardOne?boardNo=" + boardNo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
