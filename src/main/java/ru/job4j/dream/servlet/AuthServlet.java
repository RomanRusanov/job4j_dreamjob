package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.11.2020
 * email roman9628@gmail.com
 * The class describe servlet that authenticate users.
 */
public class AuthServlet extends HttpServlet {
    /**
     * The postprocess.
     * @param req Request.
     * @param resp Response.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        PsqlStore store = (PsqlStore) PsqlStore.instOf();
        User userFromDB = store.findUserByEmail(email);
        if (userFromDB.getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", userFromDB);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}