package ru.job4j.dream.servlet;

import ru.job4j.dream.model.*;
import ru.job4j.dream.store.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 19.11.2020
 * email roman9628@gmail.com
 * The class .
 */
public class RegServlet extends HttpServlet{
    /**
     * The preprocess.
     * @param req Request.
     * @param resp Response.
     * @throws javax.servlet.ServletException ServletException.
     * @throws java.io.IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/reg.jsp").forward(req, resp);
    }
    /**
     * The postprocess.
     * @param req Request.
     * @param resp Response.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PsqlStore.instOf().saveUser(
                new User(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("email"),
                        req.getParameter("password")
                )
        );
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

}