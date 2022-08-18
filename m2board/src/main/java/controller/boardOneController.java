package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;
import vo.Board;

@WebServlet("/boardOne")
public class boardOneController extends HttpServlet {
	private IBoardService boardService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 글읽기
		// 1) 요청
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 2) 모델
		this.boardService = new BoardService();
		Board board = boardService.getBoardOne(boardNo);
		System.out.println(board);
		request.setAttribute("board", board);
		
		// 3) 포워딩
		request.getRequestDispatcher("/WEB-INF/view/boardOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
