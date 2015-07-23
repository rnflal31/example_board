package com.samhyun.study.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samhyun.study.board.domain.Board;
import com.samhyun.study.common.domain.Page;


@Repository
public class BoardDao {

	@Autowired
	SqlSession sqlSession;
	
	Logger log = Logger.getLogger(this.getClass());
	
	public List<Board> selectList(Page<Board> page) {		
		log.debug("Start selectList Board");
		return sqlSession.selectList("selectBoards", page);		
	}
	
	public Board selectOne(String id) {
		log.debug("Start selectOne Board");
		return sqlSession.selectOne("selectBoardsById", id);
	}
	
	public int merge(Board board) {
		log.debug("Start merge Board");
		return sqlSession.insert("insertBoard", board);
	}

	public int merge(Map<String, Object> board) {
		log.debug("Start merge Board");
		return sqlSession.insert("insertBoardToMap", board);
	}

	public int delete(String id) {
		// TODO Auto-generated method stub
		return sqlSession.update("deleteBoard", id);
	}
	
	public int updateCount(String id) {
		return sqlSession.update("updateCount", id);
	}

	public Page<Board> selectBoardCount(Page<Board> page) {
		return sqlSession.selectOne("getCountBoards", page);
	}

}
