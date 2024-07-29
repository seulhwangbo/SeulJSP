package service;

import java.io.IOException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int    num     = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String passwd = request.getParameter("passwd");		
		try {
			Board board = new Board();
			board.setNum(Integer.parseInt(request.getParameter("num")));
			board.setPasswd(request.getParameter("passwd"));
			
			BoardDao bd = BoardDao.getInstance();
			int result = bd.delete(board);

			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			System.out.println("DeleteProAction e.getMessage ==> " + e.getMessage());
		}
		return "deletePro.jsp";
	}

}
