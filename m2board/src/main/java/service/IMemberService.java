package service;

import vo.Member;

public interface IMemberService {
	boolean addMember(Member paramMember);
	Member getMemberLogin(Member paramMember);
}
