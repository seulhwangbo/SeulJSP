package och06;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * Servlet implementation class Fibonazzi
 */
public class Fibonazzi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BigInteger [] arr = new BigInteger[100];
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fibonazzi() {
        super();
        // TODO Auto-generated constructor stub
    }
	 public void init(ServletConfig config) throws ServletException {
	// 피보나치 수열의 초기값
		 arr[0] = new BigInteger("1");
		 arr[1] = new BigInteger("1");
		 for (int i = 2; i < arr.length; i++) {
			 arr[i] = arr[i-2].add(arr[i-1]);
		 }
		 System.out.println("fibonazzi의 add arr에 적용 ...");
	 }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		if (num> 100) num = 100;
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body><h2>피보나치 수열</h2>");
		for (int i = 0; i<num;i++) {
			out.println(arr[i]+"<br>");
		}
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
