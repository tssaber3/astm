package first.demo.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//整合filter方式一
@WebFilter("/user/*")
public class MyFilter implements Filter{

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)throws IOException,ServletException
    {
        HttpServletResponse response = (HttpServletResponse) arg1;
        response.setHeader("Access-Control-Allow-Origin", "*");
        arg2.doFilter(arg0,arg1);
    }
}
