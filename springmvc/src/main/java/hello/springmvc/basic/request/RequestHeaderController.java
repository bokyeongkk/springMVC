package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String>
                                  headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false)
                          String cookie
    ) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        /*
            request=org.apache.catalina.connector.RequestFacade@4eb50c8b
            response=org.apache.catalina.connector.ResponseFacade@2a6606ca
            httpMethod=GET
            locale=ko_KR
            headerMap={host=[localhost:8080], connection=[keep-alive],...}
            header host=localhost:8080
            myCookie=null
         */

        return "ok";
    }

}
