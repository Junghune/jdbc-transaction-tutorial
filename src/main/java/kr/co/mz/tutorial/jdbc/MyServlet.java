package kr.co.mz.tutorial.jdbc;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("실행");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 서블릿 로직 구현
        response.getWriter().println("Hello, World!");
        Connection connection = (Connection) getServletContext().getAttribute("dbConnection");
    }

    @Override
    public void destroy() {
        System.out.println("꺼짐");
    }
}
