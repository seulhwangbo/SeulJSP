package service;

import java.io.IOException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteProAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
	   System.out.println("WriteProAction start...");
      // 1. num , pageNum, writer ,  email , subject , passwd , content   Get
      int num = Integer.parseInt(request.getParameter("num"));
      String pageNum = request.getParameter("pageNum");
      String email = request.getParameter("email");
      String subject = request.getParameter("subject");
      String passwd = request.getParameter("passwd");
      String content = request.getParameter("content");
	   System.out.println("WriteProAction start...");
      
      try {
         // 2. Board board 생성하고 Value Setting
         Board board = new Board();
         board.setNum(num);
         board.setWriter(request.getParameter("writer"));
  	   System.out.println("WriteProAction writer->"+request.getParameter("writer"));
  	   	 board.setEmail(email);
         board.setSubject(subject);
         board.setPasswd(passwd);
        // board.setReadcount(Integer.parseInt(request.getParameter("readcount")));
         board.setRef(Integer.parseInt(request.getParameter("ref")));
         board.setRe_step(Integer.parseInt(request.getParameter("re_step")));
         board.setRe_level(Integer.parseInt(request.getParameter("re_level")));
         board.setContent(content);
         board.setIp(request.getRemoteAddr());
         
         // 3. BoardDao bd Instance
         BoardDao bd = BoardDao.getInstance();
         int result = bd.insert(board);
    	 System.out.println("WriteProAction result->"+result);
        
           // 4. request 객체에 result, num , pageNum
         request.setAttribute("result", result);
         request.setAttribute("num", num);
         request.setAttribute("pageNum", pageNum);
      } catch (Exception e) {
         System.out.println("WriteProAction e.getMessage()=>" + e.getMessage());
      }
      return "writePro.jsp";
   }

}
