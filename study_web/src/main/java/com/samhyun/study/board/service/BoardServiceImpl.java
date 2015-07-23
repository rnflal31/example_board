package com.samhyun.study.board.service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.samhyun.study.board.dao.BoardDao;
import com.samhyun.study.board.domain.Board;
import com.samhyun.study.board.domain.CkEditorFile;
import com.samhyun.study.common.domain.Page;
import com.samhyun.study.common.utils.CommonUtil;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BoardDao boardDao;

	@Override
	public int saveBoard(Board board) throws Exception {
		// Board board = new Board("111111", "normal", title, content, 'n',
		// null, null, "samhyun", 0);
		// return sqlSession.insert("insertBoard", board);
		// Map<String, Object> board = new HashMap<String, Object>();
		// board.put("id", "1");
		// board.put("type", "normal");
		// board.put("title", title);
		// board.put("content", content);
		// board.put("delYn", 'n');
		// board.put("lastModified", "20150225");
		// board.put("writor", "samhyun");
		// board.put("count", 0);
		return boardDao.merge(board);
	}

	@Override
	public Page<Board> getBoardPage(Page<Board> page) throws Exception {
		// TODO Auto-generated method stub
		log.debug("Start list Board");
		// Board board = new Board();
		// boards.add(board);	
		log.debug(CommonUtil.toMap(page).toString());
		page.setCountEntity(boardDao.selectBoardCount(page));		
		page.setEntities(boardDao.selectList(page));		
    	log.debug(CommonUtil.toMap(page).toString());
		return page;
	}

	@Override
	public Board getBoard(String id) throws Exception {	
//		boardDao.updateCount(id);
		return boardDao.selectOne(id);
	}

	@Override
	public CkEditorFile saveFileImage(String root_path, CkEditorFile ckEditorFile)
			throws Exception {
		ckEditorFile.setAttachPath("/images/ckEditor/");
		MultipartFile upload = ckEditorFile.getUpload();
		
		if (upload != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = String.format("%s_%s_%s", dateFormat.format(new Date()), RandomStringUtils.randomAlphanumeric(5), upload.getOriginalFilename());
			ckEditorFile.setFilename(fileName);			
			try {
				File directory = new File(root_path + ckEditorFile.getAttachPath());
				if(!directory.exists()) directory.mkdirs();
				File file = new File(directory, ckEditorFile.getFilename());
				log.debug(file.getAbsolutePath());
				upload.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ckEditorFile;
	}

	@Override
	public int deleteBoard(Board board) throws Exception {		
		return boardDao.delete(board.getId());
	}

	@Override
	public int updateBoardCount(String id) throws Exception {
		return boardDao.updateCount(id);
	}
 
}
