package service;

import vo.Nice;

public interface INiceService {
	int getNice(int boardNo);
	boolean addNice(Nice nice);
}
