package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.IBoardService;
import vo.Board;

@WebServlet("/boardOne")
public class boardOneController extends HttpServlet {
	private IBoardService boardService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효성 검사
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") == null) {
			// 로그인이 안 된 상태
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
			return;
		}
		
		// 글읽기
		// 1) 요청
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 2) 모델
		this.boardService = new BoardService();
		Map<String, Object> map = boardService.getBoardOne(boardNo);
		System.out.println(map);
		request.setAttribute("map", map);
		
		// 3) 포워딩
		request.getRequestDispatcher("/WEB-INF/view/boardOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
