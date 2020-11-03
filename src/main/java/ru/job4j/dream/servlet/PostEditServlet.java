package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 03.11.2020
 * email roman9628@gmail.com
 * The class describe Post Edit Servlet.
 */
public class PostEditServlet extends HttpServlet {
    /**
     * The preprocess.
     * @param req Request.
     * @param resp Response.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            req.setAttribute(
                    "postById", Store.instOf().findPostById(
                            Integer.parseInt(req.getParameter("id"))));
        }
        req.getRequestDispatcher("/post/edit.jsp").forward(req, resp);
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
        Store.instOf().savePost(
                new Post(
                        Integer.valueOf(req.getParameter("id")),
                        req.getParameter("name")
                )
        );
        resp.sendRedirect(req.getContextPath() + "/posts.do");
    }
}
