package com.fatalism.board;

public interface BoardDAO {
	
	public int delete(int num) throws Exception;
	
	public int pwCheck(int num,String pw) throws Exception;
	
}
