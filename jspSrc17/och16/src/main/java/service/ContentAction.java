package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContentAction implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	// 1. num , pageNum Get
	// DAO Logic
	// 2. BoardDao bd Instance
    // 3. Board board = bd.select(num);

	// 4. request 객체에 num , pageNum , board
	System.out.println("ContentAction Service Start....");
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum =request.getParameter("pageNum");

	try {
		BoardDao bd = BoardDao.getInstance();
		bd.readCount(num);
		Board board = bd.select(num);

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", board);
  } catch (Exception e) {
		e.printStackTrace();
  }	
	
	//View
	return "content.jsp";
	}

}
