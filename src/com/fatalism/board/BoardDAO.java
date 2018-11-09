package com.fatalism.board;

import java.util.List;

public interface BoardDAO {
	
	
	public int delete(int num) throws Exception;
	
	public int update() throws Exception;
	
	public List<BoardDTO> selectList() throws Exception;
	
	public BoardDTO selectOne() throws Exception;
	
}
 