package service;

import java.io.IOException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * int num = Integer.parseInt(request.getParameter("num"));
		 */
		/*
//		 * String pageNum = request.getParameter("pageNum"); String writer =
		 * request.getParameter("writer"); String email = request.getParameter("email");
		 * String subject = request.getParameter("subject"); String passwd =
		 * request.getParameter("passwd"); String content =
		 * request.getParameter("content");
		 */
		
		String pageNum = request.getParameter("pageNum");
		String writer  = request.getParameter("writer");
	try {
		Board board = new Board();
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setWriter(writer);
		board.setEmail( request.getParameter("email"));
		board.setSubject( request.getParameter("subject"));
		board.setPasswd( request.getParameter("passwd"));
		board.setContent(request.getParameter("content"));
		board.setIp(request.getRemoteAddr());
		board.setRef(Integer.parseInt(request.getParameter("ref")));
		board.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		board.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		BoardDao bd = BoardDao.getInstance();
		int result = bd.update(board);
		
		request.setAttribute("result", result);
		request.setAttribute("num", board.getNum());
		request.setAttribute("pageNum", pageNum);
		
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("UpdateProAction e.getMessage : " +e.getMessage());
	}	
		return "updatePro.jsp";
	}
}
