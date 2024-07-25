package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	System.out.println("ListAction Service Start....");
	BoardDao bd = BoardDao.getInstance();
	int totCnt =0 ;
	 try {
		totCnt = bd.getTotalCnt();
		
		String pageNum =request.getParameter("pageNum");
		if(pageNum==null || pageNum.equals("")) {pageNum ="1";}
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 10, blockSize = 10;
		int startRow = (currentPage -1) *pageSize +1;
		int endRow   = startRow + pageSize -1;
		int startNum = totCnt - startRow +1;
		
		List<Board> list = bd.boardList(startRow,endRow);
		int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
		int startPage = (int)(currentPage-1)/blockSize*blockSize +1;
		int endPage = startPage + blockSize;
		
		if(endPage> pageCnt) endPage = pageCnt;
		
		request.setAttribute("list",list );
		request.setAttribute("totCnt", totCnt );
		request.setAttribute("pageNum",pageNum );
		request.setAttribute("currentPage", currentPage );
		request.setAttribute("startNum", startNum );
		request.setAttribute("blockSize", blockSize );
		request.setAttribute("pageCnt",pageCnt );
		request.setAttribute("startPage", startPage );
		request.setAttribute("endPage", endPage );
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	return "list.jsp";
	}

}
