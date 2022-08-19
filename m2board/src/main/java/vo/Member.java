package vo;

public class Member {
	private String id;
	private String pw;
	private String name;
	private String addr;
	private String detailAddr;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getDetailAddr() {
		return detailAddr;
	}
	
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", name=" + name + ", addr=" + addr + ", detailAddr=" + detailAddr
				+ "]";
	}
	
}
