package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

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
		
		if(!niceService.addNice(nice)) {
			System.out.println("좋아요 실패");
			return;
		}; // 좋아요를 올림
		
		// 좋아요 다시 받아오기
		
		int niceNum = niceService.getNice(boardNo);
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(niceNum);
		
		System.out.println("jsonStr: " + jsonStr);
		
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		
		out.flush();
		out.close(); // 스트림은 가비지 콜렉터 대상이 아니기때문에 close해준다.

		// response.sendRedirect(request.getContextPath() + "/boardOne?boardNo=" + boardNo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
