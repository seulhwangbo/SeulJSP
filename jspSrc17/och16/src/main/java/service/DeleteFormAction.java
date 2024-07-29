package service;

import java.io.IOException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DeleteFormAction Start....");
		// 1. num , pageNum Get
		
		// 2. BoardDao bd Instance
		
		// 3. Board board = bd.select(num);
	
		// 4. request 객체에  num , pageNum ,board
		
		int num = Integer.parseInt(request.getParameter("num")); 
		String pageNum = request.getParameter("pageNum");
		
		try {
			BoardDao bd = BoardDao.getInstance();
			Board board = bd.select(num);
			
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);
		} catch (Exception e) {
			System.out.println("DeleteFormAction e.getmessage ==> " + e.getMessage());
		}
		
		return "deleteForm.jsp";
	}

}
