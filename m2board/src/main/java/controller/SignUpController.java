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

@WebServlet("/signUpController")
public class SignUpController extends HttpServlet {
	private IMemberService memberService;

	// sign up form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") != null) {
			// 로그인이 된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/signUp.jsp");
		rd.forward(request, response);
	}

	// sign up action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("loginMember") != null) {
			// 로그인이 된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		
		this.memberService = new MemberService();
		int result = memberService.addMember(member);
		
		if(result != 1) {
			System.out.println("회원가입 실패");
			response.sendRedirect(request.getContextPath() + "/signUpController");
			return;
		}
		
		session.setAttribute("loginMember", member);
		response.sendRedirect(request.getContextPath() + "/index");
		
	}
}
