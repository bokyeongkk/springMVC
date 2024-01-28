package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //printStartLine(request);
        //printHeader(request);
        //printHeaderUtils(request);
        printEtc(request);
    }

    private void printStartLine(HttpServletRequest request) {
        // 작성한 내용 선택 후 Ctrl+T 하고 method extract method
        System.out.println("--- REQUEST-LINE - start ---");

        System.out.println("request.getMethod() = "+request.getMethod());       // GET
        System.out.println("request.getProtocal() = "+request.getProtocol());   // HTTP/1.1
        System.out.println("request."+request.getScheme());                     // http
        // http://localhost:8080/request-header
        System.out.println("request."+request.getRequestURL());
        // /requset-test
        System.out.println("request."+request.getRequestURI());
        // username=kim
        System.out.println("request."+request.getQueryString());
        System.out.println("request."+request.isSecure());                      // https 사용유무

        System.out.println("--- REQUEST-LINE - end ---");
    }

    private void printHeader(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

        // 첫번째 방법 (예전)
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName +": "+headerName);
        }

        // 두번째 방법 (요즘)
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName +": "+headerName));

        System.out.println("--- Headers - end ---");
    }

    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 - start ---");

        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = "+request.getServerName());
        System.out.println("request.getServerPort() = "+request.getServerPort());
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                        .forEachRemaining(locale -> System.out.println("locale = "+locale));
        System.out.println("request.getLocale()"+request.getLocale());
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null){
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName()+": "+cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        // get방식의 경우 거의 null값이다.
        System.out.println("request.getContentType() = "+request.getContentType());
        System.out.println("request.getContentLength() = "+request.getContentLength());
        System.out.println("request.getCharacterEncoding() = "+request.getCharacterEncoding());

        System.out.println("--- Header 편의 조회 - end ---");
    }

    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = "+request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = "+request.getRemoteAddr());
        System.out.println("request.getRemotePort() = "+request.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = "+request.getLocalName());
        System.out.println("request.getLocalAddr() = "+request.getLocalAddr());
        System.out.println("request.getLocalPort() = "+request.getLocalPort());

        System.out.println("--- 기타 조회 end ---");
    }
}
