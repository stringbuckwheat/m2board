package vo;

public class Nice {
	private String id;
	private int boardNo;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "Nice [id=" + id + ", boardNo=" + boardNo + "]";
	}

}
