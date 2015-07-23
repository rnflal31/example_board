package com.samhyun.study.board.web;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samhyun.study.board.domain.Board;
import com.samhyun.study.board.domain.CkEditorFile;
import com.samhyun.study.board.service.BoardService;
import com.samhyun.study.common.domain.Page;

@Controller
@RequestMapping("/board")
public class BoardController {

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model, Page<Board> page) throws Exception {
		model.addAttribute("title", "board");
		model.addAttribute("page", boardService.getBoardPage(page));
		return "/board/list";		
	}	
	@RequestMapping(value="/convert/list", method = RequestMethod.GET, produces={"application/xml", "application/json; charset=utf8"})
//	@RequestMapping(value="/convert/list", method = RequestMethod.GET)
	public @ResponseBody List<Board> listToMediaType(HttpServletRequest request,Locale locale, Model model) throws Exception {
		return null;		
//		return boardService.getBoardList();		
	}	
	@RequestMapping(value="/image/upload", method = RequestMethod.POST)
	public String imageUpload(HttpServletRequest request, Model model, CkEditorFile file) throws Exception {
		log.debug("file : " + file.getUpload().getOriginalFilename());
        HttpSession session = request.getSession();
        String root_path = session.getServletContext().getRealPath("/"); // 웹서비스 root 경로
		file = boardService.saveFileImage(root_path, file);
		model.addAttribute("file", file);
		return "/board/image/upload";
	}	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String write(Locale locale, Model model) throws Exception {
		return "/board/write";		
	}
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public String modify(Locale locale, Model model, Board board) throws Exception {
		log.debug("board id  : " + board.getId());		
		model.addAttribute("board", board);
		return "/board/write";		
	}
	@RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
	public String detail(@CookieValue(value = "readBoardIds", defaultValue = "0") String cookieVal, HttpServletResponse response, Model model, @PathVariable("id") String id) throws Exception {
		log.debug("cookie.board ids : " + cookieVal);
		List<String> ids = Arrays.asList(cookieVal.split("\\|"));
		if(!ids.contains(id)) {		
			log.debug("cookie add id");
			Cookie cookie = new Cookie("readBoardIds", cookieVal.equals("0") ? id : cookieVal.concat("|").concat(id));
			log.debug(cookie.getValue());			
			response.addCookie(cookie);	
			boardService.updateBoardCount(id);
		}
		model.addAttribute("board", boardService.getBoard(id));
		return "/board/detail";		
	}
	
	@RequestMapping(value="/merge", method = RequestMethod.POST)
    @ResponseBody
	public int insertBoard(Locale locale, Model model, Board board) throws Exception {
		log.debug(board.getContent());
		return boardService.saveBoard(board);
	}
	@RequestMapping(value="/delete", method = RequestMethod.POST)
    @ResponseBody
	public int deleteBoard(Locale locale, Model model, Board board) throws Exception {
		log.debug(board.getContent());
		return boardService.deleteBoard(board);
	}
}
