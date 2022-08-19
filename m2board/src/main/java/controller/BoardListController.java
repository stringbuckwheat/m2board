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

@WebServlet("/boardList")
public class BoardListController extends HttpServlet {
	private IBoardService boardService;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 역할
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") == null) {
			// 로그인이 안 된 상태
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
			return;
		}
		
		// 1) 요청 분석 - 분기?
		final int ROW_PER_PAGE = 10;
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 2) 서비스 레이어를 요청(메서드 호출) - 모델값(자료구조)을 구하기 위해서
		this.boardService = new BoardService();
		Map<String, Object> map = boardService.getBoardListByPage(ROW_PER_PAGE, currentPage);
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("list", map.get("list"));
		request.setAttribute("currentPage", currentPage);
		
		// 3) view forwarding
		request.getRequestDispatcher("/WEB-INF/view/boardList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
