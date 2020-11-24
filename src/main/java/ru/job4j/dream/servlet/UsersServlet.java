package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 18.11.2020
 * email roman9628@gmail.com
 * The class describe behavior servlet users page.
 */
public class UsersServlet extends HttpServlet {

    private final Validate logic = (Validate) PsqlStore.instOf();

    /**
     * The preprocess.
     * @param req Request.
     * @param resp Response.
     * @throws javax.servlet.ServletException ServletException.
     * @throws java.io.IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("usersapp", PsqlStore.instOf().findAllUsers());
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("/users.jsp").forward(req, resp);
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
        this.logic.addUser(
                new User(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("email"),
                        req.getParameter("password")
                        )
        );
        resp.sendRedirect(req.getContextPath() + "/users.do");
    }
}