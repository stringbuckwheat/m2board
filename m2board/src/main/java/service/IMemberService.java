package service;

import vo.Member;

public interface IMemberService {
	int addMember(Member paramMember);
	
	Member getMemberLogin(Member paramMember);
}
