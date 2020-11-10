package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 8.11.2020
 * email roman9628@gmail.com
 * The class describe servlet when user press download .
 */
public class DownloadServlet extends HttpServlet {
    /**
     * The preprocess.
     * @param req Request.
     * @param resp Response.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.setContentType("name=" + name);
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        File file = new File("images/phote_id" + File.separator + name);
        try (FileInputStream in = new FileInputStream(file)) {
            resp.getOutputStream().write(in.readAllBytes());
        }
    }
}
