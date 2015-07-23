package com.samhyun.study.board.service;

import com.samhyun.study.board.domain.Board;
import com.samhyun.study.board.domain.CkEditorFile;
import com.samhyun.study.common.domain.Page;


public interface BoardService {
	
	public Page<Board> getBoardPage(Page<Board> page) throws Exception;
	public int saveBoard(Board board) throws Exception;
	public Board getBoard(String id) throws Exception;
	public CkEditorFile saveFileImage(String root_path, CkEditorFile file) throws Exception;
	public int deleteBoard(Board board) throws Exception;
	public int updateBoardCount(String id) throws Exception;
//	public void test(List<String> list);
}
