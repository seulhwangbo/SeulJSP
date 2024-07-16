package och06;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

/**
 * Servlet implementation class Greet
 */
public class greet extends HttpServlet {
   private static final long serialVersionUID = 1L;
    PrintWriter log;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public greet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * 세상에 태어난 날
    */
   public void init(ServletConfig config) throws ServletException {
      try {
         System.out.println("세상에 나를 선보인 날.... 응애");
         //File을 저장하기 위한 용도
         log = new PrintWriter(new FileWriter("c:/log/log.txt",true));
      } catch (IOException e) {
         System.out.println("init 오류... 응애");
      }
   }

   /**
    * 하늘나라
    */
   public void destroy() {
      System.out.println("세상에 유언을 남긴 날.... 안녕");
      if(log != null) log.close();
   }

   /**
    * 사회활동 : 생명 주기적 관점
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("name");
      String msg = name + "님 반가워\r\n";
      System.out.println(name + "의 사회활동");

      GregorianCalendar gc = new GregorianCalendar();
      String date = String.format("%TF %TT \r\n", gc, gc);
      log.print(date + msg);
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out = response.getWriter();
      out.println("<html><body><h2>인사</h2>"+msg);
      out.println("</body></html>");
      out.close();      
      }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
