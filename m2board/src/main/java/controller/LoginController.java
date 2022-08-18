package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;
import service.MemberService;
import vo.Member;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	private IMemberService memberService;
	
	// login form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") != null) {
			// 로그인이 된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		rd.forward(request, response);
	}

	
	// login action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("loginMember") != null) {
			// 로그인이 된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		
		Member paramMember = new Member();
		paramMember.setId(request.getParameter("id"));
		paramMember.setPw(request.getParameter("pw"));
		
		memberService = new MemberService();
		Member member = memberService.getMemberLogin(paramMember);
		
		if(member == null) {
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath() + "/loginController");
			return;
		}
		
		System.out.println("로그인 성공!");
		System.out.println(member);
		session.setAttribute("loginMember", member);
		response.sendRedirect(request.getContextPath() + "/index");
	}
}
