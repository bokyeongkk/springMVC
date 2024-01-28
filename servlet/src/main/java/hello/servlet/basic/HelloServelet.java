package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServelet extends HttpServlet {

    // ctrl + o : Select Methods to Override/Implement
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
            Servlet Http 요청이오면,
            WAS(Servlet Container)가 Request, Response 객체를 만들어서
            Servlet에 던져준다.
         */

        // soutm
        System.out.println("HelloServelet.service");

        //soutv
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        //request = org.apache.catalina.connector.RequestFacade@5386f81f
        //response = org.apache.catalina.connector.ResponseFacade@3db7bee0

        // http://localhost:8080/hello?username=kim
        // URL에서 요청한 Query Parmeter 받기.
        String username = request.getParameter("username");
        System.out.println("username = "+ username);

        /*
            HttpServletResponse에 값을 넣으면
            웹브라우저에 응답하는 response-http 응답 메세지에 데이터가 담겨서 나가게 된다.
         */
        response.setContentType("text/plain");                  // Header
        response.setCharacterEncoding("utf-8");                 // Header
        response.getWriter().write("hello " + username);     // write(): Body



    }
}
