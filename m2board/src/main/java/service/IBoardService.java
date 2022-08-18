package service;

import java.util.Map;

import vo.Board;

public interface IBoardService {
	// List<Board>, int lastPage를 리턴
	Map<String, Object> getBoardListByPage(int rowPerPage, int currentPage);
	
	Map<String, Object> getBoardOne(int boardNo);

}
