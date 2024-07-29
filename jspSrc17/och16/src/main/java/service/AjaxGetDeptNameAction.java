package service;

import java.io.IOException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjaxGetDeptNameAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ajaxgetDeptNameAction start....");
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDao bd = BoardDao.getInstance();
			Board board = bd.select(num);
			request.setAttribute("writer", board.getWriter());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		// ajax 경우 ->더미 return
		return "ajax";
	}

}
